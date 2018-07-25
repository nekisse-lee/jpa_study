package com.jpa.demo.reserve.main;

import java.sql.*;

public class TestMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String host = "jdbc:mysql://localhost/popidb";
        String user = "popi";
        String pw = "dltjsgh";
        String insert = "INSERT INTO stu values(?,?,?)";
        String sql = "SELECT * FROM stu";
        Connection conn = DriverManager.getConnection(host, user, pw);
        Statement stmt = conn.createStatement();
        PreparedStatement pstmt;
        pstmt = conn.prepareStatement(insert);

        //insert using pstmt
        String sname = "정호영";
        int sno = 7;
        int pid = 1;
        pstmt.setInt(1, sno);
        pstmt.setString(2,sname);
        pstmt.setInt(3,pid);

        //        stmt.execute(insert);
        pstmt.execute();


        //        String insert = "INSERT INTO stu values(" + sno + ",'" + sname + "',1)";
//        String sql = "SELECT * FROM stu";
//        String insert = "INSERT INTO stu values(5,'최해피', 1)";
//        String update = "UPDATE stu set pid=2";


        //update
//        int n = stmt.executeUpdate(update);
//        System.out.println("update result  = " + n);

        ResultSet rs = stmt.executeQuery(sql);
        //rs는 처음에 헤더를 가르킨다.
      /*+----+-----------+------+
 -->    | id | name      | pid  |
        +----+-----------+------+
        |  1 | 강한용    |    1 |
        |  2 | 이뽀삐    |    2 |
        |  3 | 박키티    |    1 |
        |  4 | 김해피    | NULL |
        +----+-----------+------+*/

        //select
        while (rs.next()) {
            int n = rs.getInt(1);
            String name = rs.getString("name");
            pid = rs.getInt("pid");
            //System.out.printf("%d %s %d\n", n, name, pid);
            System.out.println(n + " " + name + " " + pid);
        }
        System.out.println("OK");
        stmt.close();
        pstmt.close();
        conn.close();
    }
}
