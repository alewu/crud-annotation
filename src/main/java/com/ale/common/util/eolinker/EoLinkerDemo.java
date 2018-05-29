package com.ale.common.util.eolinker;

import com.ale.common.util.freemarker.FreeMarkerMvc;
import com.ale.common.util.freemarker.MvcEnum;
import com.ale.common.util.freemarker.TableMetaData;
import com.ale.common.util.freemarker.FreeMarkerTemplateUtils;
import com.ale.common.util.mine.TableUtils;
import freemarker.template.Template;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EoLinkerDemo {
    private static final String PACKAGE_NAME = FreeMarkerMvc.getPackageName(EoLinkerDemo.class);
    private static final String FILE_PATH = FreeMarkerMvc.getAbsolutePackagePath(EoLinkerDemo.class);
    private static final String TEMPLATE_SUFFIX = ".ftl";
    private static final String CONSTANTS = MvcEnum.CONSTANTS.getName();
    /** 结果集 */
    private ResultSet resultSet;

    public static void main(String[] args) throws Exception {
        EoLinkerDemo eoLinkerDemo = new EoLinkerDemo();
        eoLinkerDemo.generate();

    }
    public void generate() throws Exception {
        List<TableMetaData> tableMetaDatas;
        try {
            tableMetaDatas = TableUtils.getTableMetaData();
            generateJsonFile(tableMetaDatas);
            generateJsonGetFile(tableMetaDatas);
            System.out.println("finished!!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    private void generateJsonFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String templateName = "EoLinkerJson";
        Path path = Paths.get(FILE_PATH, CONSTANTS, templateName + ".export");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, path, dataMap);
    }
    private void generateJsonGetFile(List<TableMetaData> tableMetaDatas) throws Exception {
        final String templateName = "EoLinkerJson-get";
        Path path = Paths.get(FILE_PATH, CONSTANTS, templateName + ".export");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("tableMetaDatas", tableMetaDatas);
        generateFileByTemplate(templateName, path, dataMap);
    }


    private void generateFileByTemplate(final String templateName, Path path, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName + TEMPLATE_SUFFIX);
        Writer out = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        template.process(dataMap, out);

    }
}
