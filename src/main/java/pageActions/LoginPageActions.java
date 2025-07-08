package pageActions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;

import pageObjects.LoginPageObjects;
import utils.PlaywrightUtils;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.SearchCriteria;

import java.io.IOException;

public class LoginPageActions extends PlaywrightUtils {
    
    /**
     * Get page title
     */
    public String getTitle() {
        return getPage().title();
    }
    
    /**
     * Login to ADL application
     */
    public String login(String username, String password) throws InterruptedException {
        Page page = getPage();
        
        // Wait for email field and enter username
        waitForElementVisible("css", LoginPageObjects.USERNAME_FIELD);
        typeText("css", LoginPageObjects.USERNAME_FIELD, username);
        
        // Scroll down and click submit
        scrollByPixels(0, 500);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        // Enter password and login
        typeText("css", LoginPageObjects.PASSWORD_FIELD, password);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        wait(2000);
        
        // Get header text
        waitForElementVisible("css", LoginPageObjects.PAGE_HEADER);
        String header = getText("css", LoginPageObjects.PAGE_HEADER);
        
        // Handle close dialog if present
        if (isElementVisible("css", LoginPageObjects.CLOSE_ICON)) {
            clickElement("css", LoginPageObjects.CLOSE_ICON);
        }
        
        wait(4000);
        return header;
    }
    
    /**
     * Login validation - check error message when no credentials provided
     */
    public String loginValidation() {
        waitForElementVisible("css", LoginPageObjects.USERNAME_FIELD_ALT);
        waitForElementVisible("css", LoginPageObjects.PASSWORD_FIELD_ALT);
        
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        waitForElementVisible("css", LoginPageObjects.ERROR_MESSAGE);
        return getText("css", LoginPageObjects.ERROR_MESSAGE);
    }
    
    /**
     * Login validation with username only
     */
    public String loginValidation(String username) {
        waitForElementVisible("css", LoginPageObjects.USERNAME_FIELD_ALT);
        typeText("css", LoginPageObjects.USERNAME_FIELD_ALT, username);
        
        waitForElementVisible("css", LoginPageObjects.PASSWORD_FIELD_ALT);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        waitForElementVisible("css", LoginPageObjects.ERROR_MESSAGE);
        return getText("css", LoginPageObjects.ERROR_MESSAGE);
    }
    
    /**
     * Login validation with invalid credentials
     */
    public String loginValidation(String username, String password) {
        waitForElementVisible("css", LoginPageObjects.USERNAME_FIELD_ALT);
        typeText("css", LoginPageObjects.USERNAME_FIELD_ALT, username);
        
        waitForElementVisible("css", LoginPageObjects.PASSWORD_FIELD_ALT);
        typeText("css", LoginPageObjects.PASSWORD_FIELD_ALT, password);
        
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        waitForElementVisible("css", LoginPageObjects.ERROR_MESSAGE);
        return getText("css", LoginPageObjects.ERROR_MESSAGE);
    }
    
    /**
     * Login with role selection for impersonation
     */
    public String loginWithRole(String username, String password, String roleType) {
        System.out.println("Starting login with role: " + roleType);
        
        // Basic login steps
        typeText("xpath", LoginPageObjects.INPUT_FIELDS, username, 0);
        typeText("xpath", LoginPageObjects.INPUT_FIELDS, password, 1);
        clickElement("xpath", LoginPageObjects.SUBMIT_BUTTONS, 1);
        
        // Navigate to impersonation
        clickElement("xpath", LoginPageObjects.REPORTS_BUTTON);
        clickElement("xpath", LoginPageObjects.IMPERSONATE_LINK);
        
        // Switch to frame and select role
        Page page = getPage();
        page.frameLocator("iframe").nth(1);
        
        // Select role type from dropdown
        selectDropdownByText("id", LoginPageObjects.ROLE_TYPE_DROPDOWN.replace("#", ""), roleType);
        
        // Enter user ID and get users
        clickElement("css", LoginPageObjects.USER_INPUT_FIELD);
        typeText("css", LoginPageObjects.USER_INPUT_FIELD, "28771");
        clickElement("id", LoginPageObjects.GET_USERS_BUTTON.replace("#", ""));
        clickElement("id", LoginPageObjects.IMPERSONATE_BUTTON.replace("#", ""));
        
        // Return to main content and get header
        String header = getText("xpath", LoginPageObjects.PAGE_HEADER, 0);
        return header;
    }
    
    /**
     * Logout from application
     */
    public void logout() throws InterruptedException {
        wait(11000);
        
        waitForElementVisible("css", LoginPageObjects.LOGOUT_ARROW);
        clickElement("css", LoginPageObjects.LOGOUT_ARROW);
        
        clickElement("xpath", LoginPageObjects.LOGOUT_MENU_ITEM);
        wait(2000);
    }
    
    /**
     * Get Protective logo element
     */
    public boolean isProtectiveLogoDisplayed() {
        return isElementVisible("xpath", LoginPageObjects.PROTECTIVE_LOGO);
    }
    
    /**
     * Get Protective login page text
     */
    public String getProtectiveLoginPageText() {
        return getText("xpath", LoginPageObjects.PROTECTIVE_LOGIN_TEXT1);
    }
    
    /**
     * Get second Protective login page text
     */
    public String getProtectiveLoginPageText2() {
        return getText("xpath", LoginPageObjects.PROTECTIVE_LOGIN_TEXT2);
    }
    
    /**
     * Get privacy policy text
     */
    public String getPrivacyPolicyText() {
        return getText("xpath", LoginPageObjects.PRIVACY_POLICY_TEXT);
    }
    
    /**
     * Click privacy policy link and verify navigation
     */
    public String clickPrivacyPolicyLink() {
        clickElement("xpath", LoginPageObjects.PRIVACY_POLICY_LINK);
        waitForPageLoad();
        return getCurrentUrl();
    }
    
    /**
     * Check if protective logo is displayed in privacy policy page
     */
    public boolean isProtectiveLogoDisplayedInPrivacyPolicy() {
        return isElementVisible("xpath", LoginPageObjects.PROTECTIVE_LOGO_PRIVACY);
    }
    
    /**
     * Check if protective logo is displayed in forgot password page
     */
    public boolean isProtectiveLogoDisplayedInForgotPassword() {
        return isElementVisible("xpath", LoginPageObjects.PROTECTIVE_LOGO_FORGOT_PASSWORD);
    }
    
    /**
     * Check if protective logo is displayed in landing page
     */
    public boolean isProtectiveLogoDisplayedInLanding() {
        return isElementVisible("xpath", LoginPageObjects.PROTECTIVE_LOGO_LANDING);
    }
    
    /**
     * Check if menu bar is displayed in landing page
     */
    public boolean isMenuBarDisplayedInLanding() {
        return isElementVisible("css", LoginPageObjects.MENU_BAR_LANDING);
    }
    
    /**
     * Login through OTP
     */
    public void loginThroughOTP(String username, String password) throws InterruptedException, IOException, MailosaurException {
        typeText("css", LoginPageObjects.USERNAME_FIELD, username);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        typeText("css", LoginPageObjects.PASSWORD_FIELD, password);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        clickElement("css", LoginPageObjects.SEND_VERIFICATION_CODE_BUTTON);
        wait(10000);
        
        String code = getVerificationCode(username);
        typeText("css", LoginPageObjects.VERIFICATION_CODE_FIELD, code);
        clickElement("css", LoginPageObjects.VERIFY_CODE_BUTTON);
        clickElement("css", LoginPageObjects.CONTINUE_BUTTON);
        
        wait(5000);
        Assert.assertEquals(getText("xpath", LoginPageObjects.WELCOME_TITLE), "Welcome to your Protective ADL Portal!");
    }
    
    /**
     * Get verification code from email
     */
    public String getVerificationCode(String username) throws IOException, MailosaurException {
        String apiKey = prop.getProperty("mailosaur.api.key");
        String serverId = prop.getProperty("mailosaur.server.id");
        String serverDomain = prop.getProperty("mailosaur.server.domain");
        
        MailosaurClient mailosaur = new MailosaurClient(apiKey);
        
        String[] userParts = username.split("@");
        String usernameToLogin = userParts[0];
        System.out.println("Checking emails for user: " + usernameToLogin);
        
        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo(usernameToLogin + "@" + serverDomain);
        
        Message message = mailosaur.messages().get(serverId, criteria);
        Assert.assertNotNull(message);
        
        System.out.println("Email subject: " + message.subject());
        System.out.println("Email to: " + message.to().get(0).email());
        System.out.println("Total codes: " + message.html().codes().size());
        
        String code = message.html().codes().get(0).value();
        System.out.println("Verification code: " + code);
        
        return code;
    }
    
    /**
     * Login through mobile OTP
     */
    public void loginThroughMobileOTP(String username, String password) throws InterruptedException, IOException, MailosaurException {
        typeText("css", LoginPageObjects.USERNAME_FIELD, username);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        typeText("css", LoginPageObjects.PASSWORD_FIELD, password);
        clickElement("css", LoginPageObjects.LOGIN_BUTTON);
        
        clickElement("css", LoginPageObjects.SEND_CODE_MOBILE_BUTTON);
        wait(10000);
        
        String code = getVerificationCodeFromMobile("+12186734331");
        typeText("css", LoginPageObjects.VERIFICATION_CODE_MOBILE_FIELD, code);
        clickElement("css", LoginPageObjects.VERIFY_CODE_MOBILE_BUTTON);
        
        wait(10000);
        Assert.assertEquals(getText("xpath", LoginPageObjects.WELCOME_TITLE), "Welcome to your Protective ADL Portal!");
    }
    
    /**
     * Get verification code from mobile
     */
    public String getVerificationCodeFromMobile(String mobileNo) throws IOException, MailosaurException {
        String apiKey = prop.getProperty("mailosaur.mobile.api.key");
        String serverId = prop.getProperty("mailosaur.mobile.server.id");
        
        MailosaurClient mailosaur = new MailosaurClient(apiKey);
        
        SearchCriteria criteria = new SearchCriteria();
        criteria.withSentTo(mobileNo);
        
        Message message = mailosaur.messages().get(serverId, criteria);
        
        String code = message.text().codes().get(0).value();
        System.out.println("Mobile verification code: " + code);
        
        return code;
    }
}