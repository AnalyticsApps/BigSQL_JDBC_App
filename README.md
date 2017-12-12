# BigSQL_JDBC_App


The compiled jar can be downloaded from https://github.com/AnalyticsApps/BigSQL_JDBC_App/blob/master/target/sample-jdbc-app-1.0-SNAPSHOT.jar


Steps to run the project

    Compile using "mvn clean package"

    Run the application using following syntax

	    java -cp /home/bigsql/sqllib/java/db2jcc.jar:/home/bigsql/sqllib/java/db2jcc4.jar:/home/bigsql/sqllib/java/db2jcc_license_cu.jar:sample-jdbc-app-1.0-SNAPSHOT.jar  com.test.JDBCTest <BigSQLHeadNode> <port> <user> <password> 

     Sample Run :
		
             java -cp /home/bigsql/sqllib/java/db2jcc.jar:/home/bigsql/sqllib/java/db2jcc4.jar:/home/bigsql/sqllib/java/db2jcc_license_cu.jar:sample-jdbc-app-1.0-SNAPSHOT.jar  com.test.JDBCTest test.ibm.com 32051 bigsql test



## Author

*Nisanth Simon* - [NisanthSimon@LinkedIn]


[NisanthSimon@LinkedIn]: https://au.linkedin.com/in/nisanth-simon-03b2149
