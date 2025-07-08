package testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageActions.LoginPageActions;
import utils.BaseClass;
import utils.DataProviderUtils;

public class LoginTest extends BaseClass {
    
    private LoginPageActions loginActions;
    
    @BeforeClass
    public void setupTest() {
        loginActions = new LoginPageActions();
    }
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {prop.getProperty("adminusername"), prop.getProperty("adminpassword")},
            // Add more login combinations as needed
        };
    }
    
    /**
     * Test to verify application title
     */
    @Test(priority = 1)
    public void verifyApplicationTitle() {
        navigate();
        String title = loginActions.getTitle();
        Assert.assertEquals(title, "AUL Corp.", "Application title mismatch");
    }
    
    /**
     * Test successful login functionality
     */
    @Test(priority = 2, dataProvider = "loginData")
    public void testSuccessfulLogin(String username, String password) throws InterruptedException {
        navigate();
        String header = loginActions.login(username, password);
        Assert.assertEquals(header, "Dashboard", "Login failed - Dashboard not displayed");
        loginActions.logout();
    }
    
    /**
     * Test login validation with empty credentials
     */
    @Test(priority = 3)
    public void testLoginValidationEmpty() {
        navigate();
        String errorMessage = loginActions.loginValidation();
        Assert.assertTrue(errorMessage.contains("required") || errorMessage.contains("field"), 
                         "Error message not displayed for empty credentials");
    }
    
    /**
     * Test login validation with username only
     */
    @Test(priority = 4)
    public void testLoginValidationUsernameOnly() {
        navigate();
        String errorMessage = loginActions.loginValidation("testuser@example.com");
        Assert.assertTrue(errorMessage.contains("password") || errorMessage.contains("required"), 
                         "Error message not displayed for missing password");
    }
    
    /**
     * Test login validation with invalid credentials
     */
    @Test(priority = 5)
    public void testLoginValidationInvalidCredentials() {
        navigate();
        String errorMessage = loginActions.loginValidation("invalid@example.com", "wrongpassword");
        Assert.assertTrue(errorMessage.toLowerCase().contains("invalid") || errorMessage.toLowerCase().contains("incorrect"), 
                         "Error message not displayed for invalid credentials: " + errorMessage);
    }
    
    /**
     * Test Protective logo visibility
     */
    @Test(priority = 6)
    public void testProtectiveLogoVisibility() {
        navigate();
        boolean isLogoDisplayed = loginActions.isProtectiveLogoDisplayed();
        Assert.assertTrue(isLogoDisplayed, "Protective logo is not displayed on login page");
    }
    
    /**
     * Test login page text content
     */
    @Test(priority = 7)
    public void testLoginPageTextContent() {
        navigate();
        String loginText = loginActions.getProtectiveLoginPageText();
        Assert.assertNotNull(loginText, "Login page text is not displayed");
        Assert.assertFalse(loginText.isEmpty(), "Login page text is empty");
    }
    
    /**
     * Test privacy policy text and link
     */
    @Test(priority = 8)
    public void testPrivacyPolicyText() {
        navigate();
        String privacyText = loginActions.getPrivacyPolicyText();
        Assert.assertTrue(privacyText.contains("Privacy Policy"), 
                         "Privacy policy text not found");
    }
    
    /**
     * Test menu bar visibility in landing page
     */
    @Test(priority = 9)
    public void testMenuBarVisibility() throws InterruptedException {
        navigate();
        // First login to access landing page
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        boolean isMenuBarDisplayed = loginActions.isMenuBarDisplayedInLanding();
        Assert.assertTrue(isMenuBarDisplayed, "Menu bar is not displayed in landing page");
        
        loginActions.logout();
    }
    
    /**
     * Test page navigation after login
     */
    @Test(priority = 10)
    public void testPageNavigationAfterLogin() throws InterruptedException {
        navigate();
        String initialUrl = loginActions.getCurrentUrl();
        
        // Perform login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        String postLoginUrl = loginActions.getCurrentUrl();
        Assert.assertNotEquals(initialUrl, postLoginUrl, "URL did not change after login");
        
        loginActions.logout();
    }
    
    /**
     * Test responsive design elements
     */
    @Test(priority = 11)
    public void testResponsiveElements() {
        navigate();
        
        // Test if essential elements are visible
        Assert.assertTrue(loginActions.isElementVisible("css", "input[placeholder='Enter your email']"), 
                         "Email input field not visible");
        Assert.assertTrue(loginActions.isElementVisible("css", "button[type='submit']"), 
                         "Submit button not visible");
    }
    
    /**
     * Test error message display and formatting
     */
    @Test(priority = 12)
    public void testErrorMessageFormatting() {
        navigate();
        
        // Trigger error by submitting empty form
        loginActions.clickElement("css", "button[type='submit']");
        
        // Check if error message is properly formatted and visible
        if (loginActions.isElementVisible("css", "adl-form-error > div > span")) {
            String errorMessage = loginActions.getText("css", "adl-form-error > div > span");
            Assert.assertNotNull(errorMessage, "Error message is null");
            Assert.assertFalse(errorMessage.trim().isEmpty(), "Error message is empty");
        }
    }
}