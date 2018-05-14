package com.ale.optional;

import java.sql.*;
import java.util.Arrays;

public class JDBCDemo {
    public static void generalInsert() throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://test.sinsinet.com:3309/zhongli", "root", "root");
        connection.setAutoCommit(false);
        String sql = "INSERT INTO tb_captcha(number, captcha, create_time, type) VALUES(?,?,?,?)";
        PreparedStatement cmd = connection.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            cmd.setString(1, "1880000000" + i);
            cmd.setString(2, "111111");
            cmd.setDate(3, new Date(System.currentTimeMillis()));
            cmd.setInt(4, 1);
            cmd.executeUpdate();
        }
        connection.commit();
        cmd.close();
        connection.close();

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //generalInsert();
        String[] a = {"a", "b", "c"};
        for(int i = 0; i < a.length; i++){
            a[i] = "c".concat(a[i]);
        }
        System.out.println(Arrays.toString(a));
    }
}
