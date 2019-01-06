package com.ale.common.util.freemarker;


import com.ale.util.freemarker.bean.MVCEnum;
import com.ale.util.freemarker.util.TableUtils;
import freemarker.template.Template;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerDemo {
    private static final String AUTHOR = "alewu";
    private static final String PACKAGE_NAME = getBasePackagePath(FreeMarkerDemo.class);
    private static final String FILE_PATH = getAbsolutePackagePath(FreeMarkerDemo.class);

    private static final String CONSTANTS = MVCEnum.CONSTANTS.getName();

    // 模板名称
    private static final String ENTITY = MVCEnum.ENTITY.getName();
    private static final String CONTROLLER = MVCEnum.CONTROLLER.getName();
    private static final String SERVICE = MVCEnum.SERVICE.getName();
    private static final String SERVICE_IMPL = MVCEnum.SERVICE_IMPL.getName();
    private static final String DAO = MVCEnum.DAO.getName();
    private static final String MAPPER = MVCEnum.MAPPER.getName();
    // 文件后缀
    private static final String JAVA_SUFFIX = SuffixConstants.JAVA;
    private static final String TEMPLATE_SUFFIX = SuffixConstants.FREEMARKER;
    private static final String MAPPER_SUFFIX = SuffixConstants.XML;
    // Controller层类级别匹配
    private static final String APP_PREFIX = "/api/v1";
    // 结果集
    private ResultSet resultSet;

    private static String currentDate;


    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        FreeMarkerDemo freeMarkerDemo = new FreeMarkerDemo();
        freeMarkerDemo.generate();
        Long end = System.currentTimeMillis();
        System.out.println("finished!!!" + "\n\rtime >>> " + (end - start) + " ms");
    }

    private void init() throws ClassNotFoundException, SQLException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(new Date());
        // 获取结果集
        resultSet = TableUtils.getResultSet();
        // 创建包
        for (MVCEnum mvcEnum : MVCEnum.values()) {
            Path path = Paths.get(FILE_PATH, mvcEnum.getName());
            Files.createDirectory(path);
        }
    }


    public static String getAbsolutePackagePath(Class clazz) {
        String pathStr = clazz.getResource("").getPath()
                .replaceFirst("/", "")
                .replace("target/classes", "src/main/java")
                .replace("/util/freemarker", "");
        return pathStr;
    }

    public static String getBasePackagePath(Class clazz) {
        String pathStr = getAbsolutePackagePath(clazz);
        String[] subPaths = pathStr.split("/src/main/java/");
        String subPath = subPaths[1];
        return subPath.replace("/", ".").substring(0, subPath.length() - 1);
    }


    public void generate() throws Exception {
        init();
        List<TableMetaData> tableMetaDatas;
        try {
            tableMetaDatas = TableUtils.getTableMetaData();
            for (TableMetaData tableMetaData : tableMetaDatas) {
                // 生成Model文件
                generateEntityFile(tableMetaData);
                // 生成Mapper文件
                generateMapperFile(tableMetaData);
                // 生成服务层接口文件
                generateServiceFile(tableMetaData);
                // 生成服务实现层文件
                generateServiceImplFile(tableMetaData);
                // 生成Dao文  件
                generateDaoFile(tableMetaData);
                // 生成controller文件
                generateControllerFile(tableMetaData);
            }
            generateRestURIConstantsFile(tableMetaDatas);
            generateBaseEntityFile();
            generateBaseServiceImplFile();
            generateBaseServiceFile();
            generateBaseDAOFile();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }


    private void generateEntityFile(TableMetaData tableMetaData) throws Exception {
        // 路径，包名，文件名
        Path path = Paths.get(FILE_PATH, ENTITY,
                tableMetaData.getEntityName() + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(ENTITY, path, dataMap);
    }

    private void generateControllerFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(FILE_PATH, CONTROLLER,
                tableMetaData.getEntityName() + MVCEnum.CONTROLLER.toUpperFirstChar() + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(CONTROLLER, path, dataMap);
    }

    private void generateServiceImplFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(FILE_PATH, "service", "impl",
                tableMetaData.getEntityName() + "ServiceImpl" + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate("serviceImpl", path, dataMap);
    }

    private void generateDaoFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(FILE_PATH, DAO,
                tableMetaData.getEntityName() + DAO.toUpperCase() + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(DAO, path, dataMap);

    }

    private void generateServiceFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(FILE_PATH, SERVICE.toLowerCase(),
                tableMetaData.getEntityName() + MVCEnum.SERVICE.toUpperFirstChar() + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("entityName", tableMetaData.getEntityName());
        generateFileByTemplate(SERVICE, path, dataMap);

    }

    private void generateMapperFile(TableMetaData tableMetaData) throws Exception {
        Path path = Paths.get(FILE_PATH, MAPPER,
                tableMetaData.getEntityName() + MVCEnum.MAPPER.toUpperFirstChar() + MAPPER_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaData", tableMetaData);
        generateFileByTemplate(MAPPER, path, dataMap);

    }


    private void generateBaseEntityFile() throws Exception {
        final String templateName = "BaseEntity";
        Path path = Paths.get(FILE_PATH, ENTITY, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseServiceFile() throws Exception {
        final String templateName = "BaseService";
        Path path = Paths.get(FILE_PATH, SERVICE, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseServiceImplFile() throws Exception {
        final String templateName = "BaseServiceImpl";
        Path path = Paths.get(FILE_PATH, "service/impl", templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateBaseDAOFile() throws Exception {
        final String templateName = "BaseDAO";
        Path path = Paths.get(FILE_PATH, DAO, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateRestURIConstantsFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String templateName = "RestURIConstants";
        Path path = Paths.get(FILE_PATH, CONSTANTS, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("appPrefix", APP_PREFIX);
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, path, dataMap);
    }


    private void generateFileByTemplate(final String templateName, Path path, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName + TEMPLATE_SUFFIX);
        dataMap.put("packageName", PACKAGE_NAME);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", currentDate);
        Writer out = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        template.process(dataMap, out);

    }


}
