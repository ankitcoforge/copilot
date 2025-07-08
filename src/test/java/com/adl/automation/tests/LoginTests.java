package com.adl.automation.tests;

import com.adl.automation.core.BaseTest;
import com.adl.automation.pages.DashboardPage;
import com.adl.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Login functionality tests for ADL Portal
 * Demonstrates Playwright modernization from Selenium
 */
public class LoginTests extends BaseTest {
    
    @Test(description = "Verify successful login with valid admin credentials")
    public void testAdminLogin() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        
        // Perform login with admin credentials
        loginPage.loginAsAdmin();
        
        // Verify dashboard is loaded
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded after login");
        
        // Verify admin privileges
        Assert.assertTrue(dashboardPage.hasAdminPrivileges(), "Admin user should have admin privileges");
        
        // Take screenshot for verification
        takeScreenshot("admin_login_success");
        
        // Logout
        dashboardPage.logout();
    }
    
    @Test(description = "Verify successful login with valid dealer credentials")
    public void testDealerLogin() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Perform login with dealer credentials
        loginPage.loginAsDealer();
        
        // Verify dashboard is loaded
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded after login");
        
        // Verify dealer privileges
        Assert.assertTrue(dashboardPage.hasDealerPrivileges(), "Dealer user should have dealer privileges");
        
        // Verify dealer does not have admin privileges
        Assert.assertFalse(dashboardPage.hasAdminPrivileges(), "Dealer user should not have admin privileges");
        
        // Take screenshot for verification
        takeScreenshot("dealer_login_success");
        
        // Logout
        dashboardPage.logout();
    }
    
    @Test(description = "Verify successful login with valid agent credentials")
    public void testAgentLogin() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Perform login with agent credentials
        loginPage.loginAsAgent();
        
        // Verify dashboard is loaded
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded after login");
        
        // Take screenshot for verification
        takeScreenshot("agent_login_success");
        
        // Logout
        dashboardPage.logout();
    }
    
    @Test(description = "Verify login failure with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Attempt login with invalid credentials
        loginPage.login("invalid@email.com", "wrongpassword");
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");
        
        // Verify we're still on login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Should remain on login page after failed login");
        
        // Take screenshot for verification
        takeScreenshot("invalid_login_error");
    }
    
    @Test(description = "Verify remember me functionality")
    public void testRememberMeFunctionality() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Check remember me option
        loginPage.checkRememberMe();
        
        // Perform login
        loginPage.loginAsDealer();
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded after login");
        
        // Take screenshot for verification
        takeScreenshot("remember_me_login");
        
        // Logout
        dashboardPage.logout();
    }
    
    @Test(description = "Verify forgot password functionality")
    public void testForgotPasswordLink() {
        LoginPage loginPage = new LoginPage();
        
        // Navigate to login page
        loginPage.navigateToLogin();
        
        // Click forgot password link
        loginPage.clickForgotPassword();
        
        // Take screenshot for verification
        takeScreenshot("forgot_password_clicked");
        
        // Additional verification could be added here based on the actual behavior
    }
}