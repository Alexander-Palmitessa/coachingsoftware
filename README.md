# coachingsoftware
Download Glassfish 5.0 from: https://javaee.github.io/glassfish/download

Intsall Glassfish and run the following commands:

1. Create GlassFish domain:<br/><em>asadmin create-domain --savelogin=true coachingeleven</em>

2. Start GlassFish server:<br/><em>asadmin start-domain --verbose coachingeleven</em>

3. Create JDBC connection pool:<br/><em>asadmin create-jdbc-connection-pool --datasourceclassname org.apache.derby.jdbc.ClientDataSource --restype javax.sql.DataSource --property ServerName=localhost:Port=1527:DatabaseName=coachingeleven:User=app:Password=app:ConnectionAttributes="'create=true'" ConnectionPool</em>

4. Create JDBC datasource:<br/><em>asadmin create-jdbc-resource --connectionpoolid ConnectionPool jdbc/coachingeleven</em>

5. Create JMS queue:<br/><em>asadmin create-jms-resource --restype javax.jms.Queue --property Name=PhysicalQueue jms/orderQueue</em>

6. Create JMS connection factory:<br/><em>asadmin create-jms-resource --restype javax.jms.ConnectionFactory jms/connectionFactory</em>
      
7. Start the database:</br><em>asadmin start-database</em>
      
Download Maven from: https://maven.apache.org/<br/>Install maven

Open the project and run the following commands:
<br/>
<em>mvn clean install -DskipTests</em>
<br/>
<em>asadmin deploy coachingsoftware-app/target/coachingsoftware-app-1.0.ear</em>

If you need to redeploy the application run:</br>
<em>asadmin redeploy coachingsoftware-app/target/coachingsoftware-app-1.0.ear</em></br>
and type: <em>coachingsoftware-app</em>

Open your browser and go to: http://localhost:8080/coachingsoftware/
