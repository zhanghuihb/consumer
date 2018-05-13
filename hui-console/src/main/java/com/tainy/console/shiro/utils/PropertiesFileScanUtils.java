package com.tainy.console.shiro.utils;

import com.tainy.common.util.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * 资源文件扫描器
 * @author yangc
 */
public final class PropertiesFileScanUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesFileScanUtils.class);

	private static final Map<String, String> properties = new HashMap<String, String>();

	/* 初始化 */
	static {
		try {
			LOGGER.info("==========开始搜索满足条件的属性文件=========");
			init(Constants.CLASSPATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 扫描属性文件
	 * @throws IOException
	 */
	private static void init(String filePath) throws IOException {
		/* 从类路径开始扫描 */
		File file = new File(filePath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				if (!f.isDirectory() && f.getName().endsWith("-message.properties")) {
					LOGGER.info("搜索到属性文件:{}", f.getAbsolutePath());
					Properties temp = new Properties();
					temp.load(new FileInputStream(f));
					Set<Object> keys = temp.keySet();
					for (Iterator<Object> it = keys.iterator(); it.hasNext();) {
						String key = (String) it.next();
						if (properties.containsKey(key)) {
							LOGGER.error("属性文件:{}定义的键({})重复", f.getAbsolutePath(), key);
							throw new RuntimeException("属性文件:" + f.getAbsolutePath() + "定义的键(" + key + ")重复");
						} else {
							properties.put(key, temp.getProperty(key));
						}
					}
				} else if (f.isDirectory()) {
					/* 如果是目录的话递归 */
					init(f.getAbsolutePath());
				} else {
					continue;
				}
			}
		}
	}

	public static String getMessage(String key) {
		return properties.get(key);
	}

}
