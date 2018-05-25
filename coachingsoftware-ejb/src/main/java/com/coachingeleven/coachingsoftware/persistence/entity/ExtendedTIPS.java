package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@Entity
@Table(name = "EXTENDED_TIPS")
public class ExtendedTIPS {

    @Id
    @Column(name = "EXTENDED_TIPS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "T_PASSES_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int techPassesGrade;
    @Column(name = "T_PASSES_C")
    private String techPassesComment;
    @Column(name = "T_BALL_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int techBallGrade;
    @Column(name = "T_BALL_C")
    private String techBallComment;
    @Column(name = "T_PRESSURE_G")
    private int techPressureGrade;
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    @Column(name = "T_PRESSURE_C")
    private String techPressureComment;

    @Column(name = "I_ORIENTATION_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellOrientationGrade;
    @Column(name = "I_ORIENTATION_C")
    private String intellOrientationComment;
    @Column(name = "I_OFF_DEF_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellOffToDefGrade;
    @Column(name = "I_OFF_DEF_C")
    private String intellOffToDefComment;
    @Column(name = "I_DEF_OFF_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellDefToOfGrade;
    @Column(name = "I_DEF_OFF_C")
    private String intellDefToOfComment;
    @Column(name = "I_OFF_DUELS_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellOffDuelsGrade;
    @Column(name = "I_OFF_DUELS_C")
    private String intellOffDuelsComment;
    @Column(name = "I_DEF_DUELS_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellDefDuelsGrade;
    @Column(name = "I_DEF_DUELS_C")
    private String intellDefDuelsComment;
    @Column(name = "I_OFF_BEHAVE_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellOffBehaviorGrade;
    @Column(name = "I_OFF_BEHAVE_C")
    private String intellOffBehaviorComment;
    @Column(name = "I_DEF_BEHAVE_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int intellDefBehaviorGrade;
    @Column(name = "I_DEF_BEHAVE_C")
    private String intellDefBehaviorComment;

    @Column(name = "S_SPEED_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int speedSpeedGrade;
    @Column(name = "S_SPEED_C")
    private String speedSpeedComment;
    @Column(name = "S_POWER_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int speedPowerGrade;
    @Column(name = "S_POWER_C")
    private String speedPowerComment;
    @Column(name = "S_ENDURANCE_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int speedEnduranceGrade;
    @Column(name = "S_ENDURANCE_C")
    private String speedEnduranceComment;

    @Column(name = "P_EMOTIONS_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persEmotionsGrade;
    @Column(name = "P_EMOTIONS_C")
    private String persEmotionsComment;
    @Column(name = "P_CONFIDENCE_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persConfidenceGrade;
    @Column(name = "P_CONFIDENCE_C")
    private String persConfidenceComment;
    @Column(name = "P_ENGAGEMENT_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persEngagementGrade;
    @Column(name = "P_ENGAGEMENT_C")
    private String persEngagementComment;
    @Column(name = "P_CONCENTRATION_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persConcentrationGrade;
    @Column(name = "P_CONCENTRATION_C")
    private String persConcentrationComment;
    @Column(name = "P_COMMUNICATION_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persCommunicationGrade;
    @Column(name = "P_COMMUNICATION_C")
    private String persCommunicationComment;
    @Column(name = "P_ADAPT_G")
    @Min(value = 1, message = "{tips.grade}")
    @Max(value = 5, message = "{tips.grade}")
    private int persAdaptGrade;
    @Column(name = "P_ADAPT_C")
    private String persAdaptComment;

    @Column(name = "STRENGTHS")
    private String strenghts;
    @Column(name = "WEAKNESSES")
    private String weaknesses;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    //TODO: PLAYER -> SET OF EXTENDED TIPS

    /**
     * JPA required default constructor
     */
    public ExtendedTIPS() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTechPassesGrade() {
        return techPassesGrade;
    }

    public void setTechPassesGrade(int techPassesGrade) {
        this.techPassesGrade = techPassesGrade;
    }

    public String getTechPassesComment() {
        return techPassesComment;
    }

    public void setTechPassesComment(String techPassesComment) {
        this.techPassesComment = techPassesComment;
    }

    public int getTechBallGrade() {
        return techBallGrade;
    }

    public void setTechBallGrade(int techBallGrade) {
        this.techBallGrade = techBallGrade;
    }

    public String getTechBallComment() {
        return techBallComment;
    }

    public void setTechBallComment(String techBallComment) {
        this.techBallComment = techBallComment;
    }

    public int getTechPressureGrade() {
        return techPressureGrade;
    }

    public void setTechPressureGrade(int techPressureGrade) {
        this.techPressureGrade = techPressureGrade;
    }

    public String getTechPressureComment() {
        return techPressureComment;
    }

    public void setTechPressureComment(String techPressureComment) {
        this.techPressureComment = techPressureComment;
    }

    public int getIntellOrientationGrade() {
        return intellOrientationGrade;
    }

    public void setIntellOrientationGrade(int intellOrientationGrade) {
        this.intellOrientationGrade = intellOrientationGrade;
    }

    public String getIntellOrientationComment() {
        return intellOrientationComment;
    }

    public void setIntellOrientationComment(String intellOrientationComment) {
        this.intellOrientationComment = intellOrientationComment;
    }

    public int getIntellOffToDefGrade() {
        return intellOffToDefGrade;
    }

    public void setIntellOffToDefGrade(int intellOffToDefGrade) {
        this.intellOffToDefGrade = intellOffToDefGrade;
    }

    public String getIntellOffToDefComment() {
        return intellOffToDefComment;
    }

    public void setIntellOffToDefComment(String intellOffToDefComment) {
        this.intellOffToDefComment = intellOffToDefComment;
    }

    public int getIntellDefToOfGrade() {
        return intellDefToOfGrade;
    }

    public void setIntellDefToOfGrade(int intellDefToOfGrade) {
        this.intellDefToOfGrade = intellDefToOfGrade;
    }

    public String getIntellDefToOfComment() {
        return intellDefToOfComment;
    }

    public void setIntellDefToOfComment(String intellDefToOfComment) {
        this.intellDefToOfComment = intellDefToOfComment;
    }

    public int getIntellOffDuelsGrade() {
        return intellOffDuelsGrade;
    }

    public void setIntellOffDuelsGrade(int intellOffDuelsGrade) {
        this.intellOffDuelsGrade = intellOffDuelsGrade;
    }

    public String getIntellOffDuelsComment() {
        return intellOffDuelsComment;
    }

    public void setIntellOffDuelsComment(String intellOffDuelsComment) {
        this.intellOffDuelsComment = intellOffDuelsComment;
    }

    public int getIntellDefDuelsGrade() {
        return intellDefDuelsGrade;
    }

    public void setIntellDefDuelsGrade(int intellDefDuelsGrade) {
        this.intellDefDuelsGrade = intellDefDuelsGrade;
    }

    public String getIntellDefDuelsComment() {
        return intellDefDuelsComment;
    }

    public void setIntellDefDuelsComment(String intellDefDuelsComment) {
        this.intellDefDuelsComment = intellDefDuelsComment;
    }

    public int getIntellOffBehaviorGrade() {
        return intellOffBehaviorGrade;
    }

    public void setIntellOffBehaviorGrade(int intellOffBehaviorGrade) {
        this.intellOffBehaviorGrade = intellOffBehaviorGrade;
    }

    public String getIntellOffBehaviorComment() {
        return intellOffBehaviorComment;
    }

    public void setIntellOffBehaviorComment(String intellOffBehaviorComment) {
        this.intellOffBehaviorComment = intellOffBehaviorComment;
    }

    public int getIntellDefBehaviorGrade() {
        return intellDefBehaviorGrade;
    }

    public void setIntellDefBehaviorGrade(int intellDefBehaviorGrade) {
        this.intellDefBehaviorGrade = intellDefBehaviorGrade;
    }

    public String getIntellDefBehaviorComment() {
        return intellDefBehaviorComment;
    }

    public void setIntellDefBehaviorComment(String intellDefBehaviorComment) {
        this.intellDefBehaviorComment = intellDefBehaviorComment;
    }

    public int getSpeedSpeedGrade() {
        return speedSpeedGrade;
    }

    public void setSpeedSpeedGrade(int speedSpeedGrade) {
        this.speedSpeedGrade = speedSpeedGrade;
    }

    public String getSpeedSpeedComment() {
        return speedSpeedComment;
    }

    public void setSpeedSpeedComment(String speedSpeedComment) {
        this.speedSpeedComment = speedSpeedComment;
    }

    public int getSpeedPowerGrade() {
        return speedPowerGrade;
    }

    public void setSpeedPowerGrade(int speedPowerGrade) {
        this.speedPowerGrade = speedPowerGrade;
    }

    public String getSpeedPowerComment() {
        return speedPowerComment;
    }

    public void setSpeedPowerComment(String speedPowerComment) {
        this.speedPowerComment = speedPowerComment;
    }

    public int getSpeedEnduranceGrade() {
        return speedEnduranceGrade;
    }

    public void setSpeedEnduranceGrade(int speedEnduranceGrade) {
        this.speedEnduranceGrade = speedEnduranceGrade;
    }

    public String getSpeedEnduranceComment() {
        return speedEnduranceComment;
    }

    public void setSpeedEnduranceComment(String speedEnduranceComment) {
        this.speedEnduranceComment = speedEnduranceComment;
    }

    public int getPersEmotionsGrade() {
        return persEmotionsGrade;
    }

    public void setPersEmotionsGrade(int persEmotionsGrade) {
        this.persEmotionsGrade = persEmotionsGrade;
    }

    public String getPersEmotionsComment() {
        return persEmotionsComment;
    }

    public void setPersEmotionsComment(String persEmotionsComment) {
        this.persEmotionsComment = persEmotionsComment;
    }

    public int getPersConfidenceGrade() {
        return persConfidenceGrade;
    }

    public void setPersConfidenceGrade(int persConfidenceGrade) {
        this.persConfidenceGrade = persConfidenceGrade;
    }

    public String getPersConfidenceComment() {
        return persConfidenceComment;
    }

    public void setPersConfidenceComment(String persConfidenceComment) {
        this.persConfidenceComment = persConfidenceComment;
    }

    public int getPersEngagementGrade() {
        return persEngagementGrade;
    }

    public void setPersEngagementGrade(int persEngagementGrade) {
        this.persEngagementGrade = persEngagementGrade;
    }

    public String getPersEngagementComment() {
        return persEngagementComment;
    }

    public void setPersEngagementComment(String persEngagementComment) {
        this.persEngagementComment = persEngagementComment;
    }

    public int getPersConcentrationGrade() {
        return persConcentrationGrade;
    }

    public void setPersConcentrationGrade(int persConcentrationGrade) {
        this.persConcentrationGrade = persConcentrationGrade;
    }

    public String getPersConcentrationComment() {
        return persConcentrationComment;
    }

    public void setPersConcentrationComment(String persConcentrationComment) {
        this.persConcentrationComment = persConcentrationComment;
    }

    public int getPersCommunicationGrade() {
        return persCommunicationGrade;
    }

    public void setPersCommunicationGrade(int persCommunicationGrade) {
        this.persCommunicationGrade = persCommunicationGrade;
    }

    public String getPersCommunicationComment() {
        return persCommunicationComment;
    }

    public void setPersCommunicationComment(String persCommunicationComment) {
        this.persCommunicationComment = persCommunicationComment;
    }

    public int getPersAdaptGrade() {
        return persAdaptGrade;
    }

    public void setPersAdaptGrade(int persAdaptGrade) {
        this.persAdaptGrade = persAdaptGrade;
    }

    public String getPersAdaptComment() {
        return persAdaptComment;
    }

    public void setPersAdaptComment(String persAdaptComment) {
        this.persAdaptComment = persAdaptComment;
    }

    public String getStrenghts() {
        return strenghts;
    }

    public void setStrenghts(String strenghts) {
        this.strenghts = strenghts;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
