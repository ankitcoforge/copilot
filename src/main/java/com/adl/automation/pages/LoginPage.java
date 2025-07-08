package com.adl.automation.pages;

import com.adl.automation.core.ConfigManager;

/**
 * Login Page object for ADL Portal
 */
public class LoginPage extends BasePage {
    
    // Locators
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#loginButton";
    private static final String ERROR_MESSAGE = ".error-message";
    private static final String FORGOT_PASSWORD_LINK = "#forgotPasswordLink";
    private static final String REMEMBER_ME_CHECKBOX = "#rememberMe";
    
    /**
     * Navigate to login page
     */
    public void navigateToLogin() {
        navigateTo(ConfigManager.getLoginUrl());
        waitForPageLoad();
        logger.info("Navigated to login page");
    }
    
    /**
     * Enter username
     */
    public void enterUsername(String username) {
        type(USERNAME_INPUT, username);
        logger.info("Entered username: {}", username);
    }
    
    /**
     * Enter password
     */
    public void enterPassword(String password) {
        type(PASSWORD_INPUT, password);
        logger.info("Entered password");
    }
    
    /**
     * Click login button
     */
    public void clickLogin() {
        click(LOGIN_BUTTON);
        logger.info("Clicked login button");
    }
    
    /**
     * Perform complete login
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        logger.info("Performed login for user: {}", username);
    }
    
    /**
     * Login with test admin user
     */
    public void loginAsAdmin() {
        String username = ConfigManager.getProperty("test.admin.username");
        String password = ConfigManager.getProperty("test.admin.password");
        login(username, password);
    }
    
    /**
     * Login with test dealer user
     */
    public void loginAsDealer() {
        String username = ConfigManager.getProperty("test.dealer.username");
        String password = ConfigManager.getProperty("test.dealer.password");
        login(username, password);
    }
    
    /**
     * Login with test agent user
     */
    public void loginAsAgent() {
        String username = ConfigManager.getProperty("test.agent.username");
        String password = ConfigManager.getProperty("test.agent.password");
        login(username, password);
    }
    
    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isVisible(ERROR_MESSAGE);
    }
    
    /**
     * Get error message text
     */
    public String getErrorMessage() {
        if (isErrorMessageDisplayed()) {
            return getText(ERROR_MESSAGE);
        }
        return "";
    }
    
    /**
     * Click forgot password link
     */
    public void clickForgotPassword() {
        click(FORGOT_PASSWORD_LINK);
        logger.info("Clicked forgot password link");
    }
    
    /**
     * Check remember me checkbox
     */
    public void checkRememberMe() {
        click(REMEMBER_ME_CHECKBOX);
        logger.info("Checked remember me checkbox");
    }
    
    /**
     * Verify login page is loaded
     */
    public boolean isLoginPageLoaded() {
        return isVisible(USERNAME_INPUT) && 
               isVisible(PASSWORD_INPUT) && 
               isVisible(LOGIN_BUTTON);
    }
}