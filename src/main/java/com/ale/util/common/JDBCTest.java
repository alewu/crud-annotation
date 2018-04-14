package com.ale.util.common;


import com.ale.util.freemarker.bean.TableMetaData;
import com.ale.util.freemarker.util.TableUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class JDBCTest {
    @Test
    public void test01() throws SQLException {
        List<TableMetaData> tableMetaDatas = TableUtils.getTableMetaData();
        for (TableMetaData tableMetaData : tableMetaDatas) {
            System.out.println(tableMetaData);
        }

    }

    @Test
    public void test02() throws SQLException {
        Connection connection = TableUtils.getConnection();
        Statement statement = Objects.requireNonNull(connection).createStatement();
        String sql = "CREATE TABLE tb_user (user_id BIGINT(16), user_name varchar(20), phone_num VARCHAR(11), email VARCHAR(20), gmt_create DATETIME, gmt_modified DATETIME)";
        int result = statement.executeUpdate(sql);
        if (result != -1) {
            System.out.println("Create table successfully ...");
        }
    }
}
