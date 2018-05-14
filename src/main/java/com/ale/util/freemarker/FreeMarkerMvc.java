package com.ale.util.freemarker;


import com.ale.util.freemarker.bean.MvcEnum;
import com.ale.util.freemarker.bean.SuffixConstants;
import com.ale.util.freemarker.bean.TableMetaData;
import com.ale.util.freemarker.util.FreeMarkerTemplateUtils;
import com.ale.util.freemarker.util.TableUtils;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


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

/**
 * @author alewu
 * @date 2018/4/14
 * @description mvc 模板文件生成
 */
@Slf4j
public class FreeMarkerMvc {
    private static final String AUTHOR = "alewu";
    private static final String PACKAGE_NAME = getPackageName(FreeMarkerMvc.class);
    private static final String FILE_PATH = getAbsolutePackagePath(FreeMarkerMvc.class);

    private static final String CONSTANTS = MvcEnum.CONSTANTS.getName();

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        // 获取当前类的加载目录
        String classPath = clazz.getResource("").getPath();
        return classPath.replaceFirst("/", "")
                        .replace("target/classes", "src/main/java")
                        .replace("/util/freemarker", "");
    }

    public static String getPackageName(Class clazz) {
        String classPath = getAbsolutePackagePath(clazz);
        log.info("classPath[{}]", classPath);
        String[] subPaths = classPath.split("/");
        return subPaths[subPaths.length - 2].concat(".").concat(subPaths[subPaths.length - 1]);
    }

    private void generate() throws Exception {
        init();
        List<TableMetaData> tableMetaDatas;
        try {
            tableMetaDatas = TableUtils.getTableMetaData();
            for (TableMetaData tableMetaData : tableMetaDatas) {
                // 生成Model文件
                generateTemplateFile("entity", tableMetaData, "entity", ".java");
                // 生成Mapper文件
                generateTemplateFile("mapper", tableMetaData, "mapper", "Mapper.xml");
                // 生成服务层接口文件
                generateTemplateFile("service", tableMetaData, "service", "Service.java");
                // 生成服务实现层文件
                generateTemplateFile("serviceImpl", tableMetaData, "service/impl", "ServiceImpl.java");
                // 生成Dao文件
                generateTemplateFile("dao", tableMetaData, "dao", "DAO.java");
                // 生成controller文件
                generateTemplateFile("controller", tableMetaData, "controller", "Controller.java");
            }
            generateRestURIConstantsFile(tableMetaDatas);
            generateBaseTemplateFile("BaseEntity",  "entity");
            generateBaseTemplateFile("BaseService",  "service");
            generateBaseTemplateFile("BaseServiceImpl",  "service/impl");
            generateBaseTemplateFile("BaseDAO", "dao");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private void generateTemplateFile(String templateName, TableMetaData tmd, String packageName, String suffix)
            throws Exception {
        // 路径，包名，文件名
        Path path = Paths.get(FILE_PATH, packageName, tmd.getEntityName() + suffix);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("entityName", tmd.getEntityName());
        dataMap.put("tableMetaData", tmd);
        generateFileByTemplate(templateName, path, dataMap);
    }
    private void generateBaseTemplateFile(String templateName,  String packageName) throws Exception {
        // 路径，包名，文件名
        Path path = Paths.get(FILE_PATH, packageName, templateName.concat(".java"));
        Map<String, Object> dataMap = new HashMap<>(2);
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
