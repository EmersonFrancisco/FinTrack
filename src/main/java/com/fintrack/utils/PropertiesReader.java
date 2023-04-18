package com.fintrack.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertiesReader {

	private static Properties initProperties() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./src/main/resources/application.properties");
		props.load(file);
		return props;
	}
	
	public static String getPropertiesByKey(String key) {
		try {
			Properties props = initProperties();
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return StringUtils.EMPTY;
		}
	}
}