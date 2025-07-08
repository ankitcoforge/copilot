package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getMethod().getMethodName());
        
        // Take screenshot on failure if configured
        if (BaseClass.prop != null && 
            Boolean.parseBoolean(BaseClass.prop.getProperty("screenshot_on_failure", "true"))) {
            takeScreenshotOnFailure(result);
        }
        
        // Print stack trace
        System.out.println("Failure reason: " + result.getThrowable().getMessage());
        result.getThrowable().printStackTrace();
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped: " + result.getMethod().getMethodName());
    }
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting test suite: " + context.getSuite().getName());
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished test suite: " + context.getSuite().getName());
        System.out.println("Total tests run: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
    }
    
    private void takeScreenshotOnFailure(ITestResult result) {
        try {
            if (BaseClass.getPage() != null) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                String fileName = "test-results/screenshots/" + 
                    result.getMethod().getMethodName() + "_" + timestamp + ".png";
                
                BaseClass.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions()
                    .setPath(Paths.get(fileName)));
                
                System.out.println("Screenshot saved: " + fileName);
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}