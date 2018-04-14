package com.ale.util.freemarker;


import com.ale.util.freemarker.bean.MvcEnum;
import com.ale.util.freemarker.bean.SuffixConstants;
import com.ale.util.freemarker.bean.TableMetaData;
import com.ale.util.freemarker.util.FreeMarkerTemplateUtils;
import com.ale.util.freemarker.util.TableUtils;
import freemarker.template.Template;
import jodd.util.StringUtil;


import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerMvc {
    private static final String AUTHOR = "alewu";
    private static final String PACKAGE_NAME = getBasePackagePath(FreeMarkerMvc.class);
    private static final String FILE_PATH = getAbsolutePackagePath(FreeMarkerMvc.class);

    private static final String CONSTANTS = MvcEnum.CONSTANTS.getName();
    /**
     * 模板名称
     */
    private static final String ENTITY = MvcEnum.ENTITY.getName();
    private static final String CONTROLLER = MvcEnum.CONTROLLER.getName();
    private static final String SERVICE = MvcEnum.SERVICE.getName();
    private static final String SERVICE_IMPL = "serviceImpl";
    private static final String DAO = MvcEnum.DAO.getName();
    private static final String MAPPER = MvcEnum.MAPPER.getName();
    /**
     * 文件后缀
     */
    private static final String JAVA_SUFFIX = SuffixConstants.JAVA;
    private static final String TEMPLATE_SUFFIX = SuffixConstants.FREEMARKER;

    /**
     * Controller层类级别匹配
     */
    private static final String APP_PREFIX = "/api/v1";
    /**
     * 结果集
     */
    private ResultSet resultSet;

    private static String currentDate;


    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        FreeMarkerMvc freeMarkerMvc = new FreeMarkerMvc();
        freeMarkerMvc.generate();
        Long end = System.currentTimeMillis();
        System.out.println("finished!!!" + "\n\rtime >>> " + (end - start) + " ms");
    }

    private void init() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = sdf.format(new Date());
        // 获取结果集
        resultSet = TableUtils.getResultSet();
        // 创建包
        for (MvcEnum mvcEnum : MvcEnum.values()) {
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
                generateJavaFile("entity", "entity", tableMetaData, ".java");
                // 生成Mapper文件
                generateJavaFile("mapper", "mapper", tableMetaData, "Mapper.xml");
                // 生成服务层接口文件
                generateJavaFile("service", "service", tableMetaData, "Service.java");
                // 生成服务实现层文件
                generateJavaFile("service/impl", "serviceImpl", tableMetaData, "ServiceImpl.java");
                // 生成Dao文件
                generateJavaFile("dao", "dao", tableMetaData, "DAO.java");
                // 生成controller文件
                generateJavaFile("controller", "controller", tableMetaData, "Controller.java");
            }
            generateRestURIConstantsFile(tableMetaDatas);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private void generateJavaFile(String packageName, String templateName, TableMetaData tmd, String suffix) throws Exception {
        // 路径，包名，文件名
        Path path = Paths.get(FILE_PATH, packageName, tmd.getEntityName() + suffix);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("entityName", tmd.getEntityName());
        dataMap.put("tableMetaData", tmd);
        generateFileByTemplate(templateName, path, dataMap);
    }


    private void generateRestURIConstantsFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String templateName = "RestURIConstants";
        Path path = Paths.get(FILE_PATH, CONSTANTS, templateName + JAVA_SUFFIX);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("appPrefix", APP_PREFIX);
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, path, dataMap);
    }

    private void generateFileByTemplate(final String templateName, Path path, Map<String, Object> dataMap) throws
            Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName + TEMPLATE_SUFFIX);
        dataMap.put("packageName", PACKAGE_NAME);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", currentDate);
        Writer out = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        template.process(dataMap, out);
    }


}