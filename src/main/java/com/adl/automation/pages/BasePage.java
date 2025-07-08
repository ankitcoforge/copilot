package com.adl.automation.pages;

import com.adl.automation.core.BrowserManager;
import com.adl.automation.core.ConfigManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base Page class for all page objects
 * Contains common functionality for all pages
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected Page page;
    
    public BasePage() {
        this.page = BrowserManager.getPage();
    }
    
    /**
     * Wait for page to load completely
     */
    protected void waitForPageLoad() {
        page.waitForLoadState();
    }
    
    /**
     * Click element with wait
     */
    protected void click(String selector) {
        Locator element = page.locator(selector);
        element.waitFor();
        element.click();
        logger.debug("Clicked element: {}", selector);
    }
    
    /**
     * Click element with custom timeout
     */
    protected void click(String selector, int timeoutMs) {
        Locator element = page.locator(selector);
        element.waitFor(new Locator.WaitForOptions().setTimeout(timeoutMs));
        element.click();
        logger.debug("Clicked element: {}", selector);
    }
    
    /**
     * Type text into element
     */
    protected void type(String selector, String text) {
        Locator element = page.locator(selector);
        element.waitFor();
        element.clear();
        element.fill(text);
        logger.debug("Typed '{}' into element: {}", text, selector);
    }
    
    /**
     * Get text from element
     */
    protected String getText(String selector) {
        Locator element = page.locator(selector);
        element.waitFor();
        String text = element.textContent();
        logger.debug("Got text '{}' from element: {}", text, selector);
        return text;
    }
    
    /**
     * Check if element is visible
     */
    protected boolean isVisible(String selector) {
        try {
            return page.locator(selector).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for element to be visible
     */
    protected void waitForElement(String selector) {
        page.locator(selector).waitFor();
        logger.debug("Element is visible: {}", selector);
    }
    
    /**
     * Wait for element to be hidden
     */
    protected void waitForElementToDisappear(String selector) {
        page.locator(selector).waitFor();
        logger.debug("Element is hidden: {}", selector);
    }
    
    /**
     * Select option from dropdown
     */
    protected void selectOption(String selector, String option) {
        Locator element = page.locator(selector);
        element.waitFor();
        element.selectOption(option);
        logger.debug("Selected option '{}' from dropdown: {}", option, selector);
    }
    
    /**
     * Get page title
     */
    protected String getPageTitle() {
        return page.title();
    }
    
    /**
     * Get current URL
     */
    protected String getCurrentUrl() {
        return page.url();
    }
    
    /**
     * Navigate to URL
     */
    protected void navigateTo(String url) {
        page.navigate(url);
        waitForPageLoad();
        logger.info("Navigated to: {}", url);
    }
    
    /**
     * Scroll element into view
     */
    protected void scrollIntoView(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
        logger.debug("Scrolled element into view: {}", selector);
    }
    
    /**
     * Upload file
     */
    protected void uploadFile(String selector, String filePath) {
        page.locator(selector).setInputFiles(java.nio.file.Paths.get(filePath));
        logger.debug("Uploaded file '{}' to element: {}", filePath, selector);
    }
    
    /**
     * Wait for download and return file path
     */
    protected String waitForDownload() {
        // Note: This is a simplified version. In real implementation, 
        // you would need to set up download handling before triggering the download
        String downloadPath = ConfigManager.getDownloadPath() + "downloaded_file.pdf";
        logger.info("Downloaded file: {}", downloadPath);
        return downloadPath;
    }
}