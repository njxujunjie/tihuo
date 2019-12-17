package com.michael.saas.tenant.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ResourceUtils;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DBUtils {

    private static final String PACK = "com.michael.saas.tenant.domain";

    public static void createDataBase(String url, String name, String user, String pwd){
        Connection conn = null;
        Statement stat = null;
        try {
            url += "?user=" + user + "&password=" + pwd + "&characterEncoding=UTF8";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();

            String sql = "drop database if exists " + name;
            stat.execute(sql);

            sql = "create database " + name + " CHARACTER SET UTF8";
            stat.execute(sql);

            sql = "use".concat(" ").concat(name);
            stat.execute(sql);

            //DBUtils.createTables(PACK, stat);
            //执行sql脚本，生成表
            String filePath = ResourceUtils.getFile("classpath:tenant.sql").getPath();
            List<String> list = DBUtils.loadSql(filePath);
            String sqls = list.get(0).toString();
            String[] s = sqls.split(";");
            for (String st : s) {
                System.out.println(st);
                stat.execute(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != stat){
                    stat.close();
                }
                if (null != conn){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTables(String pack,Statement stat) throws Exception{

        Set<Class<?>> domians = DBUtils.getClasss(pack);

        for (Class<?> domian : domians) {

            DBUtils.createTable(domian, stat);

        }

    }

    public static void createTable(Class<?> clazz, Statement stat) throws Exception{
        String tableName = "";
        Annotation [] annos = clazz.getAnnotations();
        boolean isEntity = false;
        for (Annotation anno : annos){
            if ("Entity".equals(anno.annotationType().getSimpleName())){
                isEntity = true;
            }else if ("Table".equals(anno.annotationType().getSimpleName())){
                Table table = clazz.getAnnotation(Table.class);
                if (StringUtils.isNotBlank(table.name())){
                    tableName = table.name();
                }else {
                    tableName = clazz.getSimpleName();
                }
            }
        }

        if (isEntity){

            StringBuffer dropSql = new StringBuffer("DROP TABLE IF EXISTS ");
            dropSql.append("`".concat(tableName).concat("`"));
            stat.execute(dropSql.toString());

            StringBuffer tableStr = new StringBuffer("CREATE TABLE");
            tableStr.append(" `".concat(tableName).concat("` ("));
            tableStr.append(DBUtils.getColumns(clazz));
            tableStr.append(")".concat(" ").concat("ENGINE=InnoDB DEFAULT CHARSET=utf8"));
            System.out.println(tableStr.toString());
            stat.execute(tableStr.toString());

        }

    }

    private static String getColumns(Class<?> clazz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                columns.append("`");
                if (StringUtils.isNotBlank(column.name())){
                    columns.append(column.name());
                }else {
                    columns.append(field.getName());
                }
                columns.append("`");
                if(column.name().equals("id")){
                    columns.append(" ".concat(getSqlType(field, column)));
                    columns.replace(13,26,"NOT NULL AUTO_INCREMENT");
                    System.out.println(columns);
                    //columns.append("NOT NULL AUTO_INCREMENT");
                }else{
                    columns.append(" ".concat(getSqlType(field, column)));
                }
                //columns.append(" ".concat(getSqlType(field, column)));
                columns.append(",");
            }
        }
        columns.append("PRIMARY KEY (`id`)");
        return columns.toString();
    }

    public static String getSqlType(Field field, Column column){
        StringBuffer columnType = new StringBuffer();

        Class<?> type = field.getType();

        switch (type.getSimpleName()){
            case "String" :
                columnType.append("varchar");
                if (column.length() != 0){
                    columnType.append("(".concat(String.valueOf(column.length())).concat(")"));
                }
                break;
            case "BigDecimal" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "Date" :
                if(field.isAnnotationPresent(Temporal.class)){
                    Temporal temporal = field.getAnnotation(Temporal.class);
                    if (TemporalType.TIMESTAMP.equals(temporal.value())){
                        columnType.append("datetime");
                    }else {
                        columnType.append("date");
                    }
                }else {
                    columnType.append("datetime");
                }
                break;
            case "Integer" :
                columnType.append("int");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(")"));
                }else {
                    columnType.append("(".concat(String.valueOf(11)).concat(")"));
                }
                break;
            case "Double" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "double" :
                columnType.append("decimal");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(","));
                }else {
                    columnType.append("(".concat(String.valueOf(18)).concat(","));
                }
                if (column.scale() != 0){
                    columnType.append(String.valueOf(column.scale()).concat(")"));
                }else {
                    columnType.append(String.valueOf(2).concat(")"));
                }
                break;
            case "int" :
                columnType.append("int");
                if (column.precision() != 0){
                    columnType.append("(".concat(String.valueOf(column.precision())).concat(")"));
                }else {
                    columnType.append("(".concat(String.valueOf(11)).concat(")"));
                }
                break;
        }

        if(StringUtils.isNotBlank(column.columnDefinition())){
            columnType.append(" ".concat("DEFAULT").concat(column.columnDefinition()));
        }else {
            columnType.append(" ".concat("DEFAULT NULL"));
        }

        return columnType.toString();
    }

    private static Set<Class<?>> getClasss(String pack){
        return ClassTools.getClasses(pack);
    }


    public static void main(String[] args) {
        DBUtils.createDataBase("jdbc:mysql://127.0.0.1:3306","saas_tenant1","root", "root");
        //DBUtils.createTables(PACK, null);
    }

    private static List<String> loadSql(String sqlFile) throws Exception {
        List<String> sqlList = new ArrayList<String>();

        try {
            //System.out.println(sqlFile);
            File file = new File(sqlFile);
            InputStream sqlFileIn = new FileInputStream(file);
            StringBuffer sqlSb = new StringBuffer();
            byte[] buff = new byte[1024];
            int byteRead = 0;
            while ((byteRead = sqlFileIn.read(buff)) != -1) {
                sqlSb.append(new String(buff, 0, byteRead));
            }

            // Windows 下换行是 /r/n, Linux 下是 /n
            String[] sqlArr = sqlSb.toString().split("(;//s*//r//n)|(;//s*//n)");
            for (int i = 0; i < sqlArr.length; i++) {
                String sql = sqlArr[i].replaceAll("--.*", "").trim();
                if (!sql.equals("")) {
                    sqlList.add(sql);
                }
            }
            return sqlList;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 传入连接来执行 SQL 脚本文件，这样可与其外的数据库操作同处一个事物中
     * @param stmt
     * @param sqlFile SQL 脚本文件路径
     * @throws Exception
     */
    public static void execute(Statement stmt, String sqlFile) throws Exception {
        List<String> sqlList = loadSql(sqlFile);
        for (String sql : sqlList) {
            stmt.execute(sql);
        }
        //int[] rows = stmt.executeBatch();
        System.out.println("Success");
    }

}
