package com.neo4j.test;


import java.sql.*;

public class msqltest {

    public static void main(String[] args)  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db1?serverTimezone=UTC","root","ROOT");
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, null,
                    new String[] { "TABLE" });
            while (rs.next()) {
                System.out.println("表名：" + rs.getString(3));
                System.out.println("表所属用户名：" + rs.getString(2));
                System.out.println("------------------------------");
            }

        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}
