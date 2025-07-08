package com.adl.automation.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;

/**
 * Base Test class for all Playwright tests
 * Handles test setup, teardown, and common functionality
 */
public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    
    @BeforeSuite
    public void beforeSuite() {
        logger.info("Starting ADL Automation Test Suite with Playwright");
        
        // Set environment from system property or default to dev
        String environment = System.getProperty("env", "dev");
        ConfigManager.setEnvironment(environment);
        logger.info("Running tests in {} environment", environment);
        
        // Create necessary directories
        createDirectories();
        
        // Initialize Playwright
        BrowserManager.initializePlaywright();
        BrowserManager.setConfig(ConfigManager.getAllProperties());
    }
    
    @BeforeMethod
    public void beforeMethod() {
        logger.info("Starting test method");
        BrowserManager.launchBrowser();
    }
    
    @AfterMethod
    public void afterMethod() {
        logger.info("Ending test method");
        BrowserManager.closeBrowser();
    }
    
    @AfterSuite
    public void afterSuite() {
        logger.info("Ending ADL Automation Test Suite");
        BrowserManager.closePlaywright();
    }
    
    private void createDirectories() {
        String[] directories = {
            "target/downloads",
            "target/screenshots",
            "target/videos",
            "target/traces",
            "target/reports"
        };
        
        for (String dir : directories) {
            File directory = new File(dir);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (created) {
                    logger.debug("Created directory: {}", dir);
                }
            }
        }
    }
    
    /**
     * Navigate to base URL
     */
    protected void navigateToHome() {
        String baseUrl = ConfigManager.getBaseUrl();
        BrowserManager.getPage().navigate(baseUrl);
        logger.info("Navigated to: {}", baseUrl);
    }
    
    /**
     * Navigate to login page
     */
    protected void navigateToLogin() {
        String loginUrl = ConfigManager.getLoginUrl();
        BrowserManager.getPage().navigate(loginUrl);
        logger.info("Navigated to login page: {}", loginUrl);
    }
    
    /**
     * Take screenshot with given name
     */
    protected void takeScreenshot(String name) {
        if (ConfigManager.getBooleanProperty("reports.screenshot.enabled", true)) {
            String screenshotPath = "target/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";
            BrowserManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions().setPath(java.nio.file.Paths.get(screenshotPath)));
            logger.info("Screenshot saved: {}", screenshotPath);
        }
    }
}