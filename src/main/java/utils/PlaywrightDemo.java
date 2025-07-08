package utils;

import com.microsoft.playwright.*;

/**
 * Simple demo class to test Playwright setup
 */
public class PlaywrightDemo {
    
    public static void main(String[] args) {
        System.out.println("Starting Playwright Demo...");
        
        try (Playwright playwright = Playwright.create()) {
            System.out.println("Playwright created successfully");
            
            // Create browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            System.out.println("Browser launched");
            
            // Create page
            Page page = browser.newPage();
            System.out.println("Page created");
            
            // Navigate to a test URL
            page.navigate("https://example.com");
            System.out.println("Navigated to example.com");
            
            // Get title
            String title = page.title();
            System.out.println("Page title: " + title);
            
            // Take screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("demo-screenshot.png")));
            System.out.println("Screenshot taken: demo-screenshot.png");
            
            // Close
            browser.close();
            System.out.println("Browser closed");
            
            System.out.println("Playwright Demo completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Error in Playwright Demo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}