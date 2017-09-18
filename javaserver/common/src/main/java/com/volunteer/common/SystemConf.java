package com.volunteer.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemConf {
	private static Logger logger = LoggerFactory.getLogger(SystemConf.class);
	private static Map<String, String> confMap = new HashMap<>();
	public static boolean isWindos = "windows".equals(System.getProperties().get("sun.desktop"));
	public static String confFileDir;// 配置文件加载目录
	private static String filePath;
	private static Thread monitorThread;// 配置文件修改监控线程
	private static boolean isStopMonitor;// 是否停止监控
	static {
		filePath = Thread.currentThread().getContextClassLoader()
				.getResource("config.properties").getPath()
				.replaceAll("%20", " ");
		if (isWindos) {
			filePath = filePath.substring(1);// Paths.get(confFileDir)如果是windos下面不去掉斜杠会导致异常
		}
		confFileDir = filePath.substring(0, filePath.lastIndexOf('/') + 1);
		loadProperties();
	}

	/**
	 * @description
	 * @author huit
	 * @date 2014年12月8日 下午3:53:37
	 */
	public static void stopMonitor() {
		isStopMonitor = true;
	}

	/**
	 * @description 加载配置文件
	 * @author huit
	 * @date 2014年10月23日 上午10:58:58
	 */
	private static void loadProperties() {
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);// 属性文件输入流
			prop.load(fis);// 将属性文件流装载到Properties对象中
			fis.close();// 关闭流
			logger.debug("加载配置文件：" + filePath);
			confMap.clear();
			Enumeration<?> enumeration = prop.propertyNames();
			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				confMap.put(name, prop.getProperty(name).trim());
			}
			logger.debug("confMap：" + confMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static String getFilePath() {
		return filePath;
	}

	/**
	 * 根据操作系统类型得到相应的配置值
	 * 
	 * @param key
	 * @return
	 */
	public static String getBySystem(String key) {
		if (isWindos) {
			key += "Windows";
		} else {
			key += "Linux";
		}
		return confMap.get(key);
	}

	public static String get(String key) {
		return confMap.get(key);
	}

	public static void main(String[] args) throws IOException {
		long begin = System.currentTimeMillis();
		System.out.println(get("FILE_MAX_SIZE"));
		System.out.println("query use time:"
				+ (System.currentTimeMillis() - begin));
	}

	public static Map<String, String> getConfMap() {
		return confMap;
	}
}
