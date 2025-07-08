package utils;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseClass {
    
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    
    protected static Properties prop;
    protected static String screenshotPath = "";
    
    @BeforeSuite
    public void beforeSuite() {
        loadProperties();
        createDirectories();
    }
    
    @BeforeClass
    public void setUp() {
        // Initialize Playwright
        playwright.set(Playwright.create());
        
        // Get browser type from properties
        String browserName = prop.getProperty("browser", "chromium");
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless", "false"));
        int slowMo = Integer.parseInt(prop.getProperty("slowMo", "0"));
        
        // Create browser
        BrowserType browserType = getBrowserType(browserName);
        browser.set(browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setSlowMo(slowMo)));
        
        // Create context
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        
        // Set viewport for mobile if needed
        if (Boolean.parseBoolean(prop.getProperty("mobile", "false"))) {
            contextOptions.setViewportSize(375, 667); // iPhone viewport
        }
        
        // Enable video recording if configured
        if (Boolean.parseBoolean(prop.getProperty("video_recording", "false"))) {
            contextOptions.setRecordVideoDir(Paths.get("test-results/videos"));
        }
        
        context.set(browser.get().newContext(contextOptions));
        
        // Enable tracing for debugging
        context.get().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        
        // Create page
        page.set(context.get().newPage());
        
        // Set default timeout
        page.get().setDefaultTimeout(Double.parseDouble(prop.getProperty("timeout", "30000")));
    }
    
    @AfterClass
    public void tearDown() {
        if (context.get() != null) {
            context.get().tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("test-results/traces/trace.zip")));
            context.get().close();
        }
        if (browser.get() != null) {
            browser.get().close();
        }
        if (playwright.get() != null) {
            playwright.get().close();
        }
    }
    
    @AfterSuite
    public void afterSuite() {
        // Cleanup
    }
    
    /**
     * Get the current page instance
     */
    public static Page getPage() {
        return page.get();
    }
    
    /**
     * Get the current browser context
     */
    public static BrowserContext getContext() {
        return context.get();
    }
    
    /**
     * Navigate to the application URL
     */
    public static void navigate() {
        String url = prop.getProperty("url");
        getPage().navigate(url);
        getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    /**
     * Navigate to a specific URL
     */
    public static void navigate(String url) {
        getPage().navigate(url);
        getPage().waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    /**
     * Get page title
     */
    public String getTitle() {
        return getPage().title();
    }
    
    /**
     * Take screenshot
     */
    public byte[] takeScreenshot() {
        return getPage().screenshot();
    }
    
    /**
     * Take screenshot and save to file
     */
    public void takeScreenshot(String fileName) {
        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(fileName)));
    }
    
    /**
     * Wait for element to be visible
     */
    public void waitForElement(String selector) {
        getPage().waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }
    
    /**
     * Wait for element to be hidden
     */
    public void waitForElementToDisappear(String selector) {
        getPage().waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN));
    }
    
    /**
     * Wait for specific time
     */
    public void wait(int milliseconds) {
        getPage().waitForTimeout(milliseconds);
    }
    
    /**
     * Load properties file
     */
    private void loadProperties() {
        if (prop == null) {
            prop = new Properties();
            try {
                FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
                prop.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file");
            }
        }
    }
    
    /**
     * Get browser type based on name
     */
    private BrowserType getBrowserType(String browserName) {
        switch (browserName.toLowerCase()) {
            case "firefox":
                return playwright.get().firefox();
            case "webkit":
            case "safari":
                return playwright.get().webkit();
            case "chromium":
            case "chrome":
            default:
                return playwright.get().chromium();
        }
    }
    
    /**
     * Create necessary directories
     */
    private void createDirectories() {
        try {
            java.nio.file.Files.createDirectories(Paths.get("test-results/screenshots"));
            java.nio.file.Files.createDirectories(Paths.get("test-results/videos"));
            java.nio.file.Files.createDirectories(Paths.get("test-results/traces"));
            screenshotPath = "test-results/screenshots";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Get current date
     */
    public String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }
    
    /**
     * Get current timestamp
     */
    public String getCurrentTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }
}