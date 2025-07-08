package com.adl.automation.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Manager for loading environment-specific properties
 */
public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static Properties properties = new Properties();
    private static String currentEnvironment = "dev";
    
    static {
        loadConfiguration();
    }
    
    public static void setEnvironment(String environment) {
        currentEnvironment = environment;
        loadConfiguration();
    }
    
    private static void loadConfiguration() {
        String configFile = "config_" + currentEnvironment + ".properties";
        
        try (InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream(configFile)) {
            
            if (inputStream == null) {
                logger.error("Configuration file {} not found", configFile);
                throw new RuntimeException("Configuration file " + configFile + " not found");
            }
            
            properties.load(inputStream);
            logger.info("Loaded configuration from {}", configFile);
            
        } catch (IOException e) {
            logger.error("Error loading configuration from {}", configFile, e);
            throw new RuntimeException("Error loading configuration", e);
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                logger.warn("Invalid integer value for property {}: {}", key, value);
            }
        }
        return defaultValue;
    }
    
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }
    
    public static Properties getAllProperties() {
        return new Properties(properties);
    }
    
    // Convenience methods for commonly used properties
    public static String getBaseUrl() {
        return getProperty("app.base.url");
    }
    
    public static String getLoginUrl() {
        return getProperty("app.login.url");
    }
    
    public static String getAdminUrl() {
        return getProperty("app.admin.url");
    }
    
    public static int getPageLoadTimeout() {
        return getIntProperty("timeout.page.load", 30000);
    }
    
    public static int getElementWaitTimeout() {
        return getIntProperty("timeout.element.wait", 10000);
    }
    
    public static String getTestDataPath() {
        return getProperty("test.data.excel.path", "src/test/resources/testdata/");
    }
    
    public static String getDownloadPath() {
        return getProperty("test.data.downloads.path", "target/downloads/");
    }
}