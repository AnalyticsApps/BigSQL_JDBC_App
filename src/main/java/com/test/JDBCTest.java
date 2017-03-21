package com.test;

import java.sql.*;
public class JDBCTest {

       public static void main(String[] args) {
            
             if (null != args && args.length != 4) {
                    throw new IllegalArgumentException(
                                 "Invalid arguments. " +
                                 "Specify headnode hostname/ip " +
                                 "& database port");
             }

             String ServerName = args[0];
             int PortNumber = Integer.parseInt(args[1]);
	     String user = args[2];
	     String password = args[3];
             String DatabaseName = "bigsql";

            
 
             String url = String.format("jdbc:db2://%s:%d/%s", ServerName,
                           PortNumber, DatabaseName);

             java.sql.Connection con = null;
             try {
                    Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
             } catch (Exception e) {
                    System.out.println("Error: " +
                                 "failed to load Db2 jcc driver.");
             }

             try {
                   
                    System.out.println("url: " + url);
                   
                    con = java.sql.DriverManager.
                                 getConnection(url, user , password);
                   
                    java.sql.Statement s2 = con.createStatement();

                    try {
                           s2.executeUpdate("drop table t1");
                           s2.executeUpdate("drop table tbint");

                           System.out.println("Drop Hadoop & DB2 " +
                                        "tables successfull!!!");
                          
                    } catch (Exception e) {
                           System.out.println("drop is failing");
                    }

                    try {
                           // Create DB2 Table
                           s2.executeUpdate("create table t1 (c1 int)");
                          
                           // Create BigSQL Table
                           s2.executeUpdate(
                                        "create hadoop table " +
                                        "if not exists "+
                                        "tbint " +
                                        "(col1 INT, col2 INT, col3 INT)");

                           System.out.println(
                                        "Created Hadoop & DB2 tables " +
                                        "successfully!!!");
                          
                    } catch (Exception e) {
                           System.out.println("create is failing");
                    }

                    // Insert to DB2 Table
                    String str = "insert into t1 values (100)";
                    s2.executeUpdate(str);

                    // Query the DB2 Table
                    java.sql.PreparedStatement ps = con
                                 .prepareStatement("select * from t1");
                    java.sql.ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                           System.out.println(rs.getString(1));
                    }

                    // Insert to BIGSQL Table
                    str = "insert into tbint values(1,2,3),(1,2,3),(1,2,3)";
                    s2.executeUpdate(str);

                    // Query the BIGSQL Table
                    ps = con.prepareStatement("select * from tbint");
                    rs = ps.executeQuery();

                    while (rs.next()) {
                           System.out.printf("%s,%s,%s", rs.getString(1),
                                        rs.getString(2), rs.getString(3));
                           System.out.println();
                    }

                    con.close();
             } catch (Exception e) {
                    e.printStackTrace();
             }
       }
}
