package com.adl.automation.pages;

/**
 * Dashboard Page object for ADL Portal
 * Common dashboard functionality for all user types
 */
public class DashboardPage extends BasePage {
    
    // Common navigation elements
    private static final String MAIN_MENU = "#mainMenu";
    private static final String USER_PROFILE_DROPDOWN = "#userProfileDropdown";
    private static final String LOGOUT_LINK = "#logoutLink";
    private static final String WELCOME_MESSAGE = ".welcome-message";
    
    // Menu items
    private static final String CONTRACTS_MENU = "a[href*='contracts']";
    private static final String REPORTS_MENU = "a[href*='reports']";
    private static final String MY_SETTINGS_MENU = "a[href*='settings']";
    private static final String USER_MANAGEMENT_MENU = "a[href*='users']";
    
    // Dashboard widgets
    private static final String CONTRACT_SUMMARY_WIDGET = "#contractSummaryWidget";
    private static final String RECENT_ACTIVITY_WIDGET = "#recentActivityWidget";
    private static final String NOTIFICATIONS_WIDGET = "#notificationsWidget";
    
    /**
     * Verify dashboard is loaded
     */
    public boolean isDashboardLoaded() {
        return isVisible(MAIN_MENU) && isVisible(USER_PROFILE_DROPDOWN);
    }
    
    /**
     * Get welcome message
     */
    public String getWelcomeMessage() {
        if (isVisible(WELCOME_MESSAGE)) {
            return getText(WELCOME_MESSAGE);
        }
        return "";
    }
    
    /**
     * Navigate to Contracts section
     */
    public void navigateToContracts() {
        click(CONTRACTS_MENU);
        waitForPageLoad();
        logger.info("Navigated to Contracts section");
    }
    
    /**
     * Navigate to Reports section
     */
    public void navigateToReports() {
        click(REPORTS_MENU);
        waitForPageLoad();
        logger.info("Navigated to Reports section");
    }
    
    /**
     * Navigate to My Settings
     */
    public void navigateToMySettings() {
        click(MY_SETTINGS_MENU);
        waitForPageLoad();
        logger.info("Navigated to My Settings");
    }
    
    /**
     * Navigate to User Management (Admin only)
     */
    public void navigateToUserManagement() {
        if (isVisible(USER_MANAGEMENT_MENU)) {
            click(USER_MANAGEMENT_MENU);
            waitForPageLoad();
            logger.info("Navigated to User Management");
        } else {
            logger.warn("User Management menu not available for current user");
        }
    }
    
    /**
     * Logout from application
     */
    public void logout() {
        click(USER_PROFILE_DROPDOWN);
        waitForElement(LOGOUT_LINK);
        click(LOGOUT_LINK);
        waitForPageLoad();
        logger.info("Logged out from application");
    }
    
    /**
     * Check if contract summary widget is displayed
     */
    public boolean isContractSummaryWidgetDisplayed() {
        return isVisible(CONTRACT_SUMMARY_WIDGET);
    }
    
    /**
     * Check if recent activity widget is displayed
     */
    public boolean isRecentActivityWidgetDisplayed() {
        return isVisible(RECENT_ACTIVITY_WIDGET);
    }
    
    /**
     * Check if notifications widget is displayed
     */
    public boolean isNotificationsWidgetDisplayed() {
        return isVisible(NOTIFICATIONS_WIDGET);
    }
    
    /**
     * Get contract summary data
     */
    public String getContractSummaryData() {
        if (isContractSummaryWidgetDisplayed()) {
            return getText(CONTRACT_SUMMARY_WIDGET);
        }
        return "";
    }
    
    /**
     * Verify user has admin privileges
     */
    public boolean hasAdminPrivileges() {
        return isVisible(USER_MANAGEMENT_MENU);
    }
    
    /**
     * Verify user has dealer privileges
     */
    public boolean hasDealerPrivileges() {
        return isVisible(CONTRACTS_MENU) && isVisible(REPORTS_MENU);
    }
}