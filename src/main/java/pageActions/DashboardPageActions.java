package pageActions;

import pageObjects.DashboardPageObjects;
import utils.PlaywrightUtils;

public class DashboardPageActions extends PlaywrightUtils {
    
    /**
     * Verify dashboard is loaded
     */
    public boolean isDashboardLoaded() {
        waitForElementVisible("css", DashboardPageObjects.DASHBOARD_TITLE);
        return isElementVisible("css", DashboardPageObjects.DASHBOARD_TITLE);
    }
    
    /**
     * Get dashboard title text
     */
    public String getDashboardTitle() {
        return getText("css", DashboardPageObjects.DASHBOARD_TITLE);
    }
    
    /**
     * Get welcome message
     */
    public String getWelcomeMessage() {
        if (isElementVisible("xpath", DashboardPageObjects.WELCOME_MESSAGE)) {
            return getText("xpath", DashboardPageObjects.WELCOME_MESSAGE);
        }
        return "";
    }
    
    /**
     * Click on Create Contract button
     */
    public void clickCreateContract() {
        waitForElementVisible("xpath", DashboardPageObjects.CREATE_CONTRACT_BUTTON);
        clickElement("xpath", DashboardPageObjects.CREATE_CONTRACT_BUTTON);
    }
    
    /**
     * Click on Search Contract button
     */
    public void clickSearchContract() {
        waitForElementVisible("xpath", DashboardPageObjects.SEARCH_CONTRACT_BUTTON);
        clickElement("xpath", DashboardPageObjects.SEARCH_CONTRACT_BUTTON);
    }
    
    /**
     * Click on Reports button
     */
    public void clickReports() {
        waitForElementVisible("xpath", DashboardPageObjects.REPORTS_BUTTON);
        clickElement("xpath", DashboardPageObjects.REPORTS_BUTTON);
    }
    
    /**
     * Toggle navigation menu
     */
    public void toggleMenu() {
        if (isElementVisible("css", DashboardPageObjects.MENU_TOGGLE)) {
            clickElement("css", DashboardPageObjects.MENU_TOGGLE);
        }
    }
    
    /**
     * Check if navigation menu is displayed
     */
    public boolean isNavigationMenuDisplayed() {
        return isElementVisible("css", DashboardPageObjects.LATERAL_MENU);
    }
    
    /**
     * Get user name from profile display
     */
    public String getUserName() {
        if (isElementVisible("xpath", DashboardPageObjects.USER_NAME_DISPLAY)) {
            return getText("xpath", DashboardPageObjects.USER_NAME_DISPLAY);
        }
        return "";
    }
    
    /**
     * Click on user profile dropdown
     */
    public void clickUserProfile() {
        clickElement("xpath", DashboardPageObjects.USER_PROFILE_DROPDOWN);
    }
    
    /**
     * Click logout from user profile dropdown
     */
    public void clickLogoutFromProfile() {
        clickUserProfile();
        wait(1000);
        clickElement("xpath", DashboardPageObjects.LOGOUT_OPTION);
    }
    
    /**
     * Check if notification bell is displayed
     */
    public boolean isNotificationBellDisplayed() {
        return isElementVisible("xpath", DashboardPageObjects.NOTIFICATION_BELL);
    }
    
    /**
     * Click on notification bell
     */
    public void clickNotificationBell() {
        clickElement("xpath", DashboardPageObjects.NOTIFICATION_BELL);
    }
    
    /**
     * Get notification count
     */
    public String getNotificationCount() {
        if (isElementVisible("xpath", DashboardPageObjects.NOTIFICATION_COUNT)) {
            return getText("xpath", DashboardPageObjects.NOTIFICATION_COUNT);
        }
        return "0";
    }
    
    /**
     * Perform global search
     */
    public void performGlobalSearch(String searchText) {
        if (isElementVisible("css", DashboardPageObjects.GLOBAL_SEARCH_BOX)) {
            typeText("css", DashboardPageObjects.GLOBAL_SEARCH_BOX, searchText);
            pressKey("Enter");
        }
    }
    
    /**
     * Check if search results are displayed
     */
    public boolean areSearchResultsDisplayed() {
        waitForPageLoad();
        return isElementVisible("xpath", DashboardPageObjects.SEARCH_RESULTS);
    }
    
    /**
     * Click help button
     */
    public void clickHelp() {
        if (isElementVisible("xpath", DashboardPageObjects.HELP_BUTTON)) {
            clickElement("xpath", DashboardPageObjects.HELP_BUTTON);
        }
    }
    
    /**
     * Check if dashboard cards are displayed
     */
    public boolean areDashboardCardsDisplayed() {
        return getElementCount("css", DashboardPageObjects.DASHBOARD_CARDS) > 0;
    }
    
    /**
     * Get contract count from dashboard card
     */
    public String getContractCount() {
        if (isElementVisible("xpath", DashboardPageObjects.CONTRACT_COUNT_CARD)) {
            return getText("xpath", DashboardPageObjects.CONTRACT_COUNT_CARD);
        }
        return "0";
    }
    
    /**
     * Check if recent contracts card is displayed
     */
    public boolean isRecentContractsCardDisplayed() {
        return isElementVisible("xpath", DashboardPageObjects.RECENT_CONTRACTS_CARD);
    }
    
    /**
     * Wait for dashboard to load completely
     */
    public void waitForDashboardToLoad() {
        waitForPageLoad();
        waitForElementHidden("xpath", DashboardPageObjects.LOADING_SPINNER);
        waitForElementVisible("css", DashboardPageObjects.DASHBOARD_TITLE);
    }
    
    /**
     * Get success message if displayed
     */
    public String getSuccessMessage() {
        if (isElementVisible("xpath", DashboardPageObjects.SUCCESS_MESSAGE)) {
            return getText("xpath", DashboardPageObjects.SUCCESS_MESSAGE);
        }
        return "";
    }
    
    /**
     * Get error message if displayed
     */
    public String getErrorMessage() {
        if (isElementVisible("xpath", DashboardPageObjects.ERROR_MESSAGE)) {
            return getText("xpath", DashboardPageObjects.ERROR_MESSAGE);
        }
        return "";
    }
    
    /**
     * Get toast message
     */
    public String getToastMessage() {
        if (isElementVisible("css", DashboardPageObjects.TOAST_CONTAINER)) {
            return getText("css", DashboardPageObjects.TOAST_CONTAINER);
        }
        return "";
    }
    
    /**
     * Check if footer is displayed
     */
    public boolean isFooterDisplayed() {
        return isElementVisible("css", DashboardPageObjects.FOOTER);
    }
    
    /**
     * Get copyright text from footer
     */
    public String getCopyrightText() {
        if (isElementVisible("xpath", DashboardPageObjects.COPYRIGHT_TEXT)) {
            return getText("xpath", DashboardPageObjects.COPYRIGHT_TEXT);
        }
        return "";
    }
    
    /**
     * Get version information
     */
    public String getVersionInfo() {
        if (isElementVisible("xpath", DashboardPageObjects.VERSION_INFO)) {
            return getText("xpath", DashboardPageObjects.VERSION_INFO);
        }
        return "";
    }
}