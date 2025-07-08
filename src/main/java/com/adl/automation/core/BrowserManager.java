package com.adl.automation.core;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.Properties;

/**
 * Browser Manager for Playwright automation
 * Handles browser lifecycle and configuration
 */
public class BrowserManager {
    private static final Logger logger = LoggerFactory.getLogger(BrowserManager.class);
    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static Playwright playwright;
    
    private static Properties config;
    
    public static void setConfig(Properties properties) {
        config = properties;
    }
    
    public static void initializePlaywright() {
        if (playwright == null) {
            playwright = Playwright.create();
            logger.info("Playwright initialized");
        }
    }
    
    public static void launchBrowser() {
        if (config == null) {
            throw new RuntimeException("Configuration not set. Call setConfig() first.");
        }
        
        String browserType = config.getProperty("browser.type", "chromium");
        boolean headless = Boolean.parseBoolean(config.getProperty("browser.headless", "false"));
        int slowMo = Integer.parseInt(config.getProperty("browser.slow_mo", "0"));
        
        Browser browser = getBrowserType(browserType).launch(new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo));
        
        browserThreadLocal.set(browser);
        
        // Create context
        BrowserContext context = browser.newContext();
        contextThreadLocal.set(context);
        
        Page page = context.newPage();
        pageThreadLocal.set(page);
        
        logger.info("Browser launched: {}, headless: {}", browserType, headless);
    }
    
    private static BrowserType getBrowserType(String browserType) {
        switch (browserType.toLowerCase()) {
            case "firefox":
                return playwright.firefox();
            case "webkit":
                return playwright.webkit();
            case "chromium":
            default:
                return playwright.chromium();
        }
    }
    
    public static Page getPage() {
        return pageThreadLocal.get();
    }
    
    public static BrowserContext getContext() {
        return contextThreadLocal.get();
    }
    
    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }
    
    public static void closeBrowser() {
        Page page = pageThreadLocal.get();
        if (page != null) {
            page.close();
            pageThreadLocal.remove();
        }
        
        BrowserContext context = contextThreadLocal.get();
        if (context != null) {
            context.close();
            contextThreadLocal.remove();
        }
        
        Browser browser = browserThreadLocal.get();
        if (browser != null) {
            browser.close();
            browserThreadLocal.remove();
        }
        
        logger.info("Browser closed");
    }
    
    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
            logger.info("Playwright closed");
        }
    }
}