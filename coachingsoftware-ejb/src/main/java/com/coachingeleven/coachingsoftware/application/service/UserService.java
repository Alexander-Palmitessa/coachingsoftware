package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;
import com.coachingeleven.coachingsoftware.persistence.repository.UserAccountRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateful(name = "UserService")
@TransactionAttribute(REQUIRED)
public class UserService implements UserServiceRemote {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    
    /**
     * Each token produced by this class uses this identifier as a prefix.
     */
    public static final String ID = "$58$";

    /**
     * The minimum recommended cost, used by default
     */
    public static final int DEFAULT_COST = 16;

    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    private static final int SIZE = 128;

    private static final Pattern layout = Pattern.compile("\\$58\\$(\\d\\d?)\\$(.{43})");

    private final SecureRandom random = new SecureRandom();

    private final int cost = 20;

    @EJB
    private UserAccountRepository userRepository;

    @Override
    public UserAccount createUser(UserAccount userAccount) throws UserAlreadyExistsException {
        logger.log(Level.INFO, "Creating userAccount with username " + userAccount.getUsername() + " and email " + userAccount.getEmail());
        if (userRepository.find(userAccount.getUsername()) != null) {
            logger.log(Level.INFO, "UserAccount with username " + userAccount.getUsername() + " already exists");
            throw new UserAlreadyExistsException();
        } else if (userRepository.findByMail(userAccount.getEmail()) != null) {
            logger.log(Level.INFO, "UserAccount with email " + userAccount.getEmail() + " already exists");
            throw new UserAlreadyExistsException();
        }
        userAccount.setPassword(hashPassword(userAccount.getPassword()));
        return userRepository.persist(userAccount);
    }

    @Override
    public UserAccount findUser(String username) throws UserNotFoundException {
        logger.log(Level.INFO, "Finding userAccount with username " + username);
        UserAccount userAccount = userRepository.find(username);
        if (userAccount == null) {
            logger.log(Level.INFO, "UserAccount not found");
            throw new UserNotFoundException();
        }
        return userAccount;
    }

    @Override
    public UserAccount changePassword(String username, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public String hashPassword(String plainPassword) {
    	byte[] salt = new byte[SIZE / 8];
        random.nextBytes(salt);
        byte[] dk = pbkdf2(plainPassword.toCharArray(), salt, 1 << cost);
        byte[] hash = new byte[salt.length + dk.length];
        System.arraycopy(salt, 0, hash, 0, salt.length);
        System.arraycopy(dk, 0, hash, salt.length, dk.length);
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
        return ID + cost + '$' + enc.encodeToString(hash);
    }
    
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations)
    {
      KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
      try {
        SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
        return f.generateSecret(spec).getEncoded();
      }
      catch (NoSuchAlgorithmException ex) {
        throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
      }
      catch (InvalidKeySpecException ex) {
        throw new IllegalStateException("Invalid SecretKeyFactory", ex);
      }
    }
    
    /**
     * Authenticate with a password and a stored password token.
     * 
     * @return true if the password and token match
     */
    @Override
    public boolean authenticate(String plainPassword, String token)
    {
      Matcher m = layout.matcher(token);
      if (!m.matches())
        throw new IllegalArgumentException("Invalid token format");
      int iterations = iterations(Integer.parseInt(m.group(1)));
      byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
      byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
      byte[] check = pbkdf2(plainPassword.toCharArray(), salt, iterations);
      int zero = 0;
      for (int idx = 0; idx < check.length; ++idx)
        zero |= hash[salt.length + idx] ^ check[idx];
      return zero == 0;
    }
    
    private static int iterations(int cost)
    {
      if ((cost < 0) || (cost > 30))
        throw new IllegalArgumentException("cost: " + cost);
      return 1 << cost;
    }

	@Override
	public void updateUser(UserAccount user) {
		userRepository.update(user);
	}
}
