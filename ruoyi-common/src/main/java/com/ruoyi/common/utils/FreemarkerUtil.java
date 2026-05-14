package com.ruoyi.common.utils;


import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

public class FreemarkerUtil {

    private static Configuration configuration;

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("utf-8");
        configuration.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtil.class, "/ftl"));
        configuration.setFallbackOnNullLoopVariable(false);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
    }

    /**
     * 使用模板和参数生成内容，写入指定文件地址
     *
     * @param templateName 模板
     * @param param        参数
     * @param filePath     写入文件地址
     */
    public static void process(String templateName, Object param, String filePath) {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            process(templateName, param, fileWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用模板和参数生成内容，返回 string 形式的内容
     *
     * @param templateName 模板
     * @param param        参数
     * @return string 形式的内容
     */
    public static String process(String templateName, Object param) {
        StringWriter stringWriter = new StringWriter();
        process(templateName, param, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 使用模板和参数生成内容
     *
     * @param templateName 模板
     * @param param        参数
     * @param out          输出方式
     */
    public static void process(String templateName, Object param, Writer out) {
        try {
            Template template = configuration.getTemplate(templateName);
            template.process(param, out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 传入String类型的模板，参数和模板名生成信息
     * @param templateName 模板名，如果模板处理过程中出现错误，Freemarker会使用这个"name"属性来指明是哪个模板出了问题，对代码没什么影响。
     * @param templateContent 配置的信息模板
     * @param data 模板里面所需要替换的参数
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String generateMessage(String templateName, String templateContent, Map<String, String> data) throws IOException, TemplateException {

        if (StringUtils.isNotEmpty(templateContent)) {
            // 创建配置实例
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            // 创建Freemarker模板对象
            Template template = new Template(templateName, templateContent, cfg);
            StringWriter writer = new StringWriter();
            // 使用模板引擎生成短信内容
            template.process(data, writer);
            return writer.toString();
        } else {
            return null;
        }
    }

}
