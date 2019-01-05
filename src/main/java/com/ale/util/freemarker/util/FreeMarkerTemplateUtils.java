package com.ale.util.freemarker.util;

import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;

/**
 * @author alewu
 * @date 2017/11/4 8:23
 * @description FreeMarker模板工具类：主要用于配置模板，获取模板
 */
public class FreeMarkerTemplateUtils {

    /**
     * 通过Freemarker的Configuration读取相应的ftl
     **/
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_26);

    static {
        CONFIGURATION.setClassForTemplateLoading(FreeMarkerTemplateUtils.class, "/template");
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }


    public static Template getTemplate(String template) throws IOException {
        return CONFIGURATION.getTemplate(template);
    }

    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}
