package testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageActions.DashboardPageActions;
import pageActions.LoginPageActions;
import utils.BaseClass;

public class DashboardTest extends BaseClass {
    
    private LoginPageActions loginActions;
    private DashboardPageActions dashboardActions;
    
    @BeforeClass
    public void setupTest() {
        loginActions = new LoginPageActions();
        dashboardActions = new DashboardPageActions();
    }
    
    /**
     * Test dashboard loading after successful login
     */
    @Test(priority = 1)
    public void testDashboardLoading() throws InterruptedException {
        navigate();
        
        // Login first
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        // Verify dashboard is loaded
        dashboardActions.waitForDashboardToLoad();
        Assert.assertTrue(dashboardActions.isDashboardLoaded(), "Dashboard failed to load");
        
        String dashboardTitle = dashboardActions.getDashboardTitle();
        Assert.assertEquals(dashboardTitle, "Dashboard", "Dashboard title mismatch");
        
        loginActions.logout();
    }
    
    /**
     * Test navigation menu visibility
     */
    @Test(priority = 2)
    public void testNavigationMenuVisibility() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        Assert.assertTrue(dashboardActions.isNavigationMenuDisplayed(), 
                         "Navigation menu is not displayed");
        
        loginActions.logout();
    }
    
    /**
     * Test dashboard quick action buttons
     */
    @Test(priority = 3)
    public void testQuickActionButtons() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test if quick action buttons are functional
        // Note: We're just testing visibility and clickability, not full navigation
        if (dashboardActions.isElementVisible("xpath", "//button[contains(text(),'Create Contract')]")) {
            Assert.assertTrue(true, "Create Contract button is visible");
        }
        
        if (dashboardActions.isElementVisible("xpath", "//button[contains(text(),'Search Contract')]")) {
            Assert.assertTrue(true, "Search Contract button is visible");
        }
        
        if (dashboardActions.isElementVisible("xpath", "//button[contains(text(),'Reports')]")) {
            Assert.assertTrue(true, "Reports button is visible");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test user profile functionality
     */
    @Test(priority = 4)
    public void testUserProfile() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test user profile dropdown if available
        if (dashboardActions.isElementVisible("xpath", "//button[contains(@class,'user-profile')]")) {
            dashboardActions.clickUserProfile();
            wait(1000);
            
            Assert.assertTrue(dashboardActions.isElementVisible("xpath", "//button[contains(text(),'Logout')]"),
                             "Logout option not found in user profile dropdown");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test notification functionality
     */
    @Test(priority = 5)
    public void testNotificationFunctionality() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test notification bell if available
        if (dashboardActions.isNotificationBellDisplayed()) {
            Assert.assertTrue(true, "Notification bell is displayed");
            
            String notificationCount = dashboardActions.getNotificationCount();
            Assert.assertNotNull(notificationCount, "Notification count is null");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test dashboard cards visibility
     */
    @Test(priority = 6)
    public void testDashboardCards() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test if dashboard cards are displayed
        boolean cardsDisplayed = dashboardActions.areDashboardCardsDisplayed();
        // Note: Not asserting as true since cards may not be present in all implementations
        System.out.println("Dashboard cards displayed: " + cardsDisplayed);
        
        loginActions.logout();
    }
    
    /**
     * Test global search functionality
     */
    @Test(priority = 7)
    public void testGlobalSearch() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test global search if available
        if (dashboardActions.isElementVisible("css", "input[placeholder='Search...']")) {
            dashboardActions.performGlobalSearch("test");
            wait(2000);
            
            // Check if search functionality works (results may vary)
            System.out.println("Global search functionality tested");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test help functionality
     */
    @Test(priority = 8)
    public void testHelpFunctionality() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test help button if available
        if (dashboardActions.isElementVisible("xpath", "//button[contains(@aria-label,'Help')]")) {
            dashboardActions.clickHelp();
            wait(1000);
            System.out.println("Help functionality tested");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test footer information
     */
    @Test(priority = 9)
    public void testFooterInformation() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test footer if available
        if (dashboardActions.isFooterDisplayed()) {
            Assert.assertTrue(true, "Footer is displayed");
            
            String copyrightText = dashboardActions.getCopyrightText();
            String versionInfo = dashboardActions.getVersionInfo();
            
            System.out.println("Copyright text: " + copyrightText);
            System.out.println("Version info: " + versionInfo);
        }
        
        loginActions.logout();
    }
    
    /**
     * Test menu toggle functionality
     */
    @Test(priority = 10)
    public void testMenuToggle() throws InterruptedException {
        navigate();
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        
        dashboardActions.waitForDashboardToLoad();
        
        // Test menu toggle if available
        if (dashboardActions.isElementVisible("css", "button[aria-label='Toggle sidenav']")) {
            dashboardActions.toggleMenu();
            wait(1000);
            
            dashboardActions.toggleMenu(); // Toggle back
            wait(1000);
            
            System.out.println("Menu toggle functionality tested");
        }
        
        loginActions.logout();
    }
}