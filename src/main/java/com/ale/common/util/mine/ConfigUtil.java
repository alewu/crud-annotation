package com.ale.common.util.mine;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author alewu
 * @date 2017/10/17 19:12
 * @description 获取属性文件配置的参数
 */
public class ConfigUtil {
    private ConfigUtil(){
    }

	public static String getParameter(String key){
		Properties prop = new Properties();
		InputStream in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key).trim();
	}
	public static void main(String[] args) {
		System.out.println(getParameter("appID"));
	}
}
