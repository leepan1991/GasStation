package code.generator;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerate {

    public static final String url = "jdbc:mysql://123.56.220.41:3306/volunteer?useUnicode=true&characterEncoding=utf-8";
    public static final String driverclass = "com.mysql.jdbc.Driver";
    public static final String username = "volunteer";
    public static final String password = "Wqnmlgb!1024";
    public static final String tableNamePrefix = "t_";// 数据库表名的前缀，比如前缀设置为t，那么t_user将会生成User.java
    public static final String tableName = "t_school";


    private static final String SAVE_PATH = "D:/codegenerate/";//文件保存路径
    private static final String abstractPackagePath = "com.volunteer.dao.abs";//抽象类的package，如com.bbbx.dao.abs.AbstractMapper、com.bbbx.dao.abs.AbstractEntity
    public static final String entityPackage = "com.volunteer.pojo";// 实体类的package，如com.bbbx.bao.model.User
    public static final String mapperPackage= "com.volunteer.dao.mapper";// Mapper实体类的package，如com.bbbx.dao.model.UserMapper
    public static final String entityName;//实体类类名，如User
    public static final String mapperName;//Mapper类类名，如UserMapper
    public static final String mapperNameFullPath;//Mapper类全路径，即带package路径。如com.test.mapper.UserMapper
    public static final String entityNameFullPath;//实体类全路径，即带package路径。如com.test.mapper.UserMapper
    public static final List<String> columnList = new ArrayList<String>();

    public static boolean handleColumnUnderLine = true;//是否处理字段的下划线，如create_time要不要处理成createTime,实体类和Mapper.xml都会处理
    public static boolean isOverWrite = true;//如果该文件已经存在，是否重新生成并覆盖原来的

    static {
        entityName = firstCharToUpperCase(handleUnderLineAndPrefix(tableName));
        mapperName = entityName + "Mapper";
        mapperNameFullPath = mapperPackage + "." + mapperName;
        entityNameFullPath = entityPackage + "." + entityName;
    }

    public static void main(String[] args) {
        String entityFilepath = SAVE_PATH;
        String mapperJaveFilepath = SAVE_PATH;
        String mapperXMLFilepath = SAVE_PATH;
        String serviceFilepath = SAVE_PATH;

        File file = new File(entityFilepath);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        // 生成实体类，如User.java
        String s1 = generateEntity();
        generateFile(s1, entityFilepath, entityName + ".java");
        //
        //		// 生存Mapper.java，如UserMapper.java
        String s2 = generateMapperJava();
        generateFile(s2, mapperJaveFilepath, mapperName + ".java");
        //		//
        //		// 生存Mapper.xml，如UserMapper.xml
        String s3 = generateMapperXML();
        generateFile(s3, mapperXMLFilepath, mapperName + ".xml");
        //
        //		//		// 生存Service.java，如UserService.java
        String s4 = generateService();
        generateFile(s4, serviceFilepath, entityName + "Service.java");
    }

    public static void generateFile(String data, String filePath, String fileName) {
        try {
            File path = new File(filePath);
            if (!path.exists() || !path.isDirectory()) {
                boolean isCreate = path.mkdir();
                if (isCreate) {
                    System.out.println("crate dir error:" + path);
                }
            }
            File out = new File(filePath + "\\" + fileName);
            if (out.exists() && !isOverWrite) {
                System.err.println(filePath + "\\" + fileName + "已经存在！创建文件失败！");
                return;
            } else {
                System.out.println(filePath + "\\" + fileName + "创建成功！请刷新查看");
            }
            FileOutputStream os = new FileOutputStream(out);
            FileChannel fos = os.getChannel();
            ByteBuffer bytedata = ByteBuffer.wrap(data.getBytes());

            // bytedata.flip();
            fos.write(bytedata);
            bytedata.clear();
            fos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<ColumnInfo> list = new ArrayList<ColumnInfo>();

    public static String generateEntity() {
        try {
            list = new ArrayList<ColumnInfo>();
            Class.forName(driverclass).newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                String name = resultSet.getString("TABLE_NAME");
                if (tableName.equals(name)) {
                    // ResultSet rs
                    // =getConnection.getMetaData().getColumns(null,
                    // getXMLConfig.getSchema(),tableName.toUpperCase(),
                    // "%");//其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
                    ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                    while (rs.next()) {
                        if (!StringUtils.equals(rs.getString("COLUMN_NAME"), "id")) {
                            ColumnInfo entity = new ColumnInfo();
                            String colName = rs.getString("COLUMN_NAME");
                            if ("extends".equals(colName)) {
                                colName = colName + "_";
                            }
                            entity.setColumnName(colName);
                            entity.setTypeName(rs.getString("TYPE_NAME"));
                            entity.setRemarks(rs.getString("REMARKS"));
                            list.add(entity);
                            columnList.add(colName);
                        }
                    }
                }
            }

            StringBuffer sb = new StringBuffer();
            if (StringUtils.isNotBlank(entityPackage)) {
                sb.append("package ").append(entityPackage).append(";\n\n");
            }
            //			sb.append("import java.io.Serializable;\n");
//            sb.append("import " + abstractPackagePath + ".AbstractEntity;\n");
            for (ColumnInfo c : list) {
                String typeName = sqlType2JavaType(c.getTypeName());
                String importStr = "";
                if(typeName == null ){
                    System.out.println(c.getTypeName());
                    System.out.println(c.getColumnName());
                }
                switch (typeName) {
                    case "Timestamp":
                        importStr = "import java.sql.Timestamp;";
                        break;
                    case "Time":
                        importStr = "import java.sql.Time;";
                        break;
                    case "Date":
                        importStr = "import java.sql.Date;";
                        break;
                    default:
                        break;
                }
                if (!sb.toString().contains(importStr)) {
                    sb.append(importStr + " \n");
                }
            }
            sb.append("public class " + entityName + " extends AbstractEntity { \n");
            //			sb.append("public class " + className + " implements Serializable {\n");
            //			sb.append("\tprivate static final long serialVersionUID = 1L;\n");

            for (ColumnInfo c : list) {
                String typeName = c.getTypeName();
                String columnName = c.getColumnName();
                String remarks = c.getRemarks();
                if (StringUtils.isNotBlank(remarks)) {
                    sb.append("\t/** " + remarks + " **/\n");
                }
                if(handleColumnUnderLine){
                    columnName = handleColumnUnderLine(columnName);//处理下划线
                }
                sb.append("\tprivate " + sqlType2JavaType(typeName) + " " + columnName + ";\n\n");
            }
            for (ColumnInfo c : list) {
                String typeName = c.getTypeName();
                String columnName = c.getColumnName();
                if(handleColumnUnderLine){
                    columnName = handleColumnUnderLine(columnName);//处理下划线
                }
                sb.append("\tpublic " + sqlType2JavaType(typeName) + " get" + firstCharToUpperCase(columnName)
                        + "(){\n");
                sb.append("\t\treturn this." + columnName + ";\n\t}\n");

                sb.append("\tpublic void set" + firstCharToUpperCase(columnName) + "(" + sqlType2JavaType(typeName)
                        + " " + columnName + "){\n");
                sb.append("\t\tthis." + columnName + " = " + columnName + ";\n\t}\n");
            }
            sb.append("}");
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateMapperJava() {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(mapperPackage)) {
            sb.append("package ").append(mapperPackage).append(";\n\n");
        }
        sb.append("import " + abstractPackagePath + ".AbstractMapper;\n");
        sb.append("public interface ").append(entityName).append("Mapper extends AbstractMapper {\n\n}");
        return sb.toString();
    }

    public static String generateService() {
        StringBuffer sb = new StringBuffer();
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.stereotype.Service;\n\n")
                .append("import ")
                .append(mapperNameFullPath)
                .append(";\n")
                .append("import ")
                .append(entityPackage)
                .append(".")
                .append(entityName)
                .append(";\n")
                        //				.append("import ").append(servicePath).append(".").append("AbstractService;\n")

                .append("import " + abstractPackagePath + ".AbstractMapper;\n")
                .append("import " + abstractPackagePath + ".AbstractService;\n")

                        //				.append("import ").append(mapperJavaBeanPath).append(".").append("AbstractMapper;\n\n")
                .append("@Service\n").append("public class ").append(entityName)
                .append("Service extends AbstractService<").append(entityName).append(">{\n\n").append("\t@Autowired\n")
                .append("\tprivate ").append(mapperName).append(" ").append(entityName.toLowerCase())
                .append("Mapper;\n\n").append("\tpublic ").append(entityName).append("Service() {\n")
                .append("\t\tsuper(").append(entityName).append(".class);\n\t}\n\n").append("\t@Override\n")
                .append("\tpublic AbstractMapper getAbstractMapper() {\n").append("\t\treturn this.")
                .append(entityName.toLowerCase()).append("Mapper;\n").append("\t}\n").append("}");
        return sb.toString();
    }

    public static String generateMapperXML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n")
                .append("<mapper namespace=\"").append(mapperNameFullPath).append("\">\n");
        //baseSql
        sb.append("\t<sql id=\"Base_Column_List\" >\n");
        if (columnList.size() > 0) {
            sb.append("\t");
        }
        for (String columnName : columnList) {
            sb.append(columnName).append(",");
        }
        if (columnList.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        sb.append("\t</sql>\n");

        // insert
        sb.append("\t<insert id=\"insert\" parameterType=\"").append(entityNameFullPath).append("\"")
                .append(" useGeneratedKeys=\"true\" keyProperty=\"id\">\n").append("\t\tinsert into ")
                .append(tableName).append("\n\t\t(");
        for (int i = 0; i < columnList.size(); i++) {
            sb.append(columnList.get(i));
            if (i != columnList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") \n").append("\t\tvalues \n").append("\t\t(");
        for (int i = 0; i < columnList.size(); i++) {
            String s = columnList.get(i);
            if(handleColumnUnderLine){
                s = handleColumnUnderLine(s);
            }
            sb.append("#{").append(s).append("}");
            if (i != columnList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") \n");
        sb.append("\t</insert>");

        // deleteById
        sb.append("\n\t<delete id=\"deleteById\" parameterType=\"int\">\n").append("\t\tdelete from ")
                .append(tableName).append(" where id=#{id}").append("\n\t</delete>");

        // selectById
        sb.append("\n\t<select id=\"selectById\" resultType=\"").append(entityNameFullPath).append("\">\n")
                .append("\t\tselect * from ").append(tableName).append(" where id=#{id}").append("\n\t</select>");

        // deleteByIds
        sb.append("\n\t<delete id=\"deleteByIds\" parameterType=\"int\">\n")
                .append("\t\tdelete from ")
                .append(tableName)
                .append("\n")
                .append("\t\t<where>\n")
                .append("\t\t<choose>\n")
                .append("\t\t\t<when test=\"null != ids\">\n")
                .append("\t\t\t\t<foreach collection=\"ids\" item=\"id\" separator=\",\" open=\"id in (\" close=\")\">\n")
                .append("\t\t\t\t#{id}\n").append("\t\t\t\t</foreach>\n").append("\t\t\t</when>\n")
                .append("\t\t\t<otherwise>\n").append("\t\t\t0=1\n").append("\t\t\t</otherwise>\n")
                .append("\t\t</choose>\n").append("\t\t</where>\n").append("\t</delete>");

        // update
        sb.append("\n\t<update id=\"update\" parameterType=\"").append(entityNameFullPath).append("\">\n")
                .append("\t\tupdate ").append(tableName).append("\n").append("\t\t<set>\n");
        for (int i = 0; i < columnList.size(); i++) {
            String columnName = columnList.get(i);
            String columnNameNoUnderline = columnName;
            if(handleColumnUnderLine){
                columnNameNoUnderline = handleColumnUnderLine(columnNameNoUnderline);
            }
            sb.append("\t\t\t<if test=\"").append(columnName).append(" != null\">\n");
            sb.append("\t\t\t\t").append(columnName).append(" = ").append("#{").append(columnNameNoUnderline).append("}");
            if (i != columnList.size() - 1) {
                sb.append(",");
            }
            sb.append("\n\t\t\t</if>\n");
        }
        sb.append("\t\t</set>\n").append("\t\twhere id=#{id}\n").append("\t</update>");

        // listPaged
        sb.append("\n\t<select id=\"listPaged\" resultType=\"").append(entityNameFullPath).append("\">\n")
                .append("\t\tselect * from ").append(tableName).append("\n")
                .append("\t\t<if test=\"tableParam.length > -1\">\n")
                .append("\t\tlimit #{tableParam.start},#{tableParam.length}\n").append("\t\t</if>")
                .append("\n\t</select>");

        //listAll
        sb.append("\n\t<select id=\"listAll\" resultType=\"").append(entityNameFullPath).append("\">\n")
                .append("\t\tselect * from ").append(tableName).append("\n\t</select>");

        // count
        sb.append("\n\t<select id=\"count\" resultType=\"int\">\n").append("\t\tselect count(*) from ")
                .append(tableName).append("\n").append("\t</select>").append("\n</mapper>");
        return sb.toString();
    }

    public static String handleUnderLineAndPrefix(String tableName) {
        tableName = tableName.substring(tableNamePrefix.length(), tableName.length());
        int length = tableName.length();
        List<String> list = new ArrayList<String>();
        String preCs = "";
        for (int i = 0; i < length; i++) {
            String cs = String.valueOf(tableName.charAt(i));
            if ("_".equals(cs)) {

            } else {
                if ("_".equals(preCs)) {
                    cs = cs.toUpperCase();
                }
                list.add(cs);
            }
            preCs = cs;
        }
        StringBuffer sb = new StringBuffer();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("tinyint") || sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("bit")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("double")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("LONGTEXT")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Timestamp";
        } else if (sqlType.equalsIgnoreCase("DATE")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("TIME")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("Timestamp")) {
            return "Timestamp";
        }
        return null;
    }

    /**
     * 首字母大写
     * @param s
     * @return
     */
    public static String firstCharToUpperCase(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 处理字段下划线，例如：把create_time变成createTime
     * @param s
     * @return
     */
    public static String handleColumnUnderLine(String s){
        StringBuffer sb = new StringBuffer();
        if(s.contains("_")){
            String array[] = s.split("_");
            sb.append(array[0]);
            for(int i=1;i<array.length;i++){
                sb.append(firstCharToUpperCase(array[i]));
            }
        }else{
            sb.append(s);
        }
        return sb.toString();
    }
}
