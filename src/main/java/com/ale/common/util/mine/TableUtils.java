package com.ale.common.util.mine;


import com.ale.common.util.freemarker.CustomField;
import com.ale.common.util.freemarker.TableMetaData;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;


public class TableUtils {
    private static final String TABLE = "table";
    private static final String FILED = "filed";

    private static final String REGEX = "_";
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String DRIVER;

    static {
        DRIVER = getParameter("jdbc.driverClassName");
        URL = getParameter("jdbc.url");
        USER = getParameter("jdbc.username");
        PASSWORD = getParameter("jdbc.password");
    }


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static ResultSet getResultSet() {
        try (Connection connection = TableUtils.getConnection()) {
            DatabaseMetaData databaseMetaData = Objects.requireNonNull(connection).getMetaData();
            return databaseMetaData.getColumns(null, "%", "%", "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<TableMetaData> getTableMetaData() throws SQLException {
        TableMetaData tableMetaData;
        CustomField customField;
        DatabaseMetaData dbMetData;
        try (Connection connection = TableUtils.getConnection()) {
            dbMetData = Objects.requireNonNull(connection).getMetaData();
            try (ResultSet rs = dbMetData.getTables(null, "%", "%", new String[]{"TABLE"})) {
                List<TableMetaData> tableMetaDatas = new LinkedList<>();
                while (rs.next()) {
                    tableMetaData = new TableMetaData();
                    // 获取表名
                    String tableName = rs.getString("TABLE_NAME");
                    if (tableName.startsWith("j")){
                        continue;
                    }
                    tableMetaData.setTableName(tableName);
                    String tableRemarks = rs.getString("REMARKS");
                    tableMetaData.setTableRemarks(tableRemarks);
                    tableMetaData.setEntityName(TableUtils.processName(tableName, TableUtils.TABLE));
                    // 根据表名提取表里面信息
                    ResultSet colRS = dbMetData.getColumns(null, "%", tableName, "%");
                    List<CustomField> customFields = new LinkedList<>();
                    while (colRS.next()) {
                        customField = new CustomField();
                        String columnName = colRS.getString("COLUMN_NAME");
                        String typeName = colRS.getString("TYPE_NAME");
                        String remarks = colRS.getString("REMARKS");
                        customField.setColumnName(columnName);
                        customField.setMemberVariable(TableUtils.processName(columnName, FILED));
                        customField.setTypeName(typeName);
                        customField.setRemarks(remarks);
                        customFields.add(customField);
                    }
                    tableMetaData.setCustomFields(customFields);
                    tableMetaDatas.add(tableMetaData);
                }
                return tableMetaDatas;
            }
        }
    }

    private static String processName(String str, String target) {
        String[] words = str.split(REGEX);
        StringBuilder sb = new StringBuilder(words.length);
        String firstWord = StringUtils.uncapitalize(words[0].trim());
        sb.append(firstWord);
        for (int i = 1; i < words.length; i++) {
            String otherWord = StringUtils.capitalize(words[i].trim());
            sb.append(otherWord);
        }
        if (TABLE.equals(target)) {
            return sb.toString().replace(firstWord, "");
        }
        if (FILED.equals(target)) {
            return sb.toString();
        }
        return null;
    }

    private static String getParameter(String key) {
        Properties prop = new Properties();
        InputStream in = TableUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key).trim();
    }
}
