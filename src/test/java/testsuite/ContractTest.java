package testsuite;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageActions.ContractPageActions;
import pageActions.DashboardPageActions;
import pageActions.LoginPageActions;
import utils.BaseClass;
import utils.RandomizerUtils;

import java.util.HashMap;
import java.util.List;

public class ContractTest extends BaseClass {
    
    private LoginPageActions loginActions;
    private DashboardPageActions dashboardActions;
    private ContractPageActions contractActions;
    
    @BeforeClass
    public void setupTest() {
        loginActions = new LoginPageActions();
        dashboardActions = new DashboardPageActions();
        contractActions = new ContractPageActions();
    }
    
    /**
     * Test contract creation workflow
     */
    @Test(priority = 1)
    public void testContractCreation() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Fill customer information
        HashMap<String, String> customerData = contractActions.fillCustomerInformationWithRandomData();
        Assert.assertFalse(customerData.isEmpty(), "Customer data should not be empty");
        
        // Fill vehicle information
        HashMap<String, String> vehicleData = contractActions.fillVehicleInformationWithRandomData();
        Assert.assertFalse(vehicleData.isEmpty(), "Vehicle data should not be empty");
        
        System.out.println("Contract creation test completed with customer: " + 
                          customerData.get("firstName") + " " + customerData.get("lastName"));
        
        loginActions.logout();
    }
    
    /**
     * Test contract search functionality
     */
    @Test(priority = 2)
    public void testContractSearch() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to contract search
        contractActions.navigateToContractSearch();
        
        // Test search by VIN
        String testVin = RandomizerUtils.getRandomVIN();
        contractActions.searchContractsByVIN(testVin);
        
        int searchResults = contractActions.getSearchResultsCount();
        System.out.println("Search results for VIN " + testVin + ": " + searchResults);
        
        // Clear search and test by customer name
        contractActions.clearSearchForm();
        contractActions.searchContractsByCustomerName("Test User");
        
        System.out.println("Contract search functionality tested");
        
        loginActions.logout();
    }
    
    /**
     * Test contract quote calculation
     */
    @Test(priority = 3)
    public void testQuoteCalculation() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Fill required information
        contractActions.fillCustomerInformationWithRandomData();
        contractActions.fillVehicleInformationWithRandomData();
        
        // Select program and calculate quote
        if (contractActions.isElementVisible("xpath", "//select[contains(@id,'program')]")) {
            contractActions.selectProgram("Basic Coverage", "36 months", "$500");
            
            // Try to calculate quote
            if (contractActions.isElementVisible("xpath", "//button[contains(text(),'Calculate Quote')]")) {
                String quoteTotal = contractActions.calculateQuote();
                Assert.assertNotNull(quoteTotal, "Quote total should not be null");
                System.out.println("Quote calculated: " + quoteTotal);
            }
        }
        
        loginActions.logout();
    }
    
    /**
     * Test form validation
     */
    @Test(priority = 4)
    public void testFormValidation() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Try to submit empty form or calculate quote without required fields
        if (contractActions.isElementVisible("xpath", "//button[contains(text(),'Calculate Quote')]")) {
            contractActions.clickElement("xpath", "//button[contains(text(),'Calculate Quote')]");
            contractActions.wait(2000);
            
            // Check for validation messages
            List<String> validationMessages = contractActions.getValidationMessages();
            if (!validationMessages.isEmpty()) {
                System.out.println("Validation messages found: " + validationMessages.size());
                for (String message : validationMessages) {
                    System.out.println("Validation message: " + message);
                }
            }
        }
        
        loginActions.logout();
    }
    
    /**
     * Test contract search by date range
     */
    @Test(priority = 5)
    public void testContractSearchByDateRange() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to contract search
        contractActions.navigateToContractSearch();
        
        // Search by date range
        String fromDate = "01/01/2024";
        String toDate = "12/31/2024";
        
        if (contractActions.isElementVisible("css", "input[name='dateFrom']")) {
            contractActions.searchContractsByDateRange(fromDate, toDate);
            
            int searchResults = contractActions.getSearchResultsCount();
            System.out.println("Search results for date range " + fromDate + " to " + toDate + ": " + searchResults);
        }
        
        loginActions.logout();
    }
    
    /**
     * Test contract actions (view, edit)
     */
    @Test(priority = 6)
    public void testContractActions() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to contract search
        contractActions.navigateToContractSearch();
        
        // Perform a search to get some results
        contractActions.searchContractsByCustomerName("Test");
        
        int searchResults = contractActions.getSearchResultsCount();
        if (searchResults > 0) {
            System.out.println("Found " + searchResults + " contracts for testing actions");
            
            // Test view contract
            if (contractActions.isElementVisible("xpath", "//a[contains(text(),'View')]")) {
                contractActions.clickFirstContractInResults();
                contractActions.wait(2000);
                
                // Check if contract details are displayed
                if (contractActions.isElementVisible("xpath", "//h2[contains(text(),'Contract Details')]")) {
                    String contractStatus = contractActions.getContractStatus();
                    System.out.println("Contract status: " + contractStatus);
                }
            }
        } else {
            System.out.println("No contracts found for testing actions");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test surcharge functionality
     */
    @Test(priority = 7)
    public void testSurchargeHandling() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Fill basic information
        contractActions.fillCustomerInformationWithRandomData();
        contractActions.fillVehicleInformationWithRandomData();
        
        // Test surcharge functionality
        if (contractActions.isElementVisible("xpath", "//input[@type='checkbox' and contains(@name,'surcharge')]")) {
            contractActions.addSurcharge("500");
            System.out.println("Surcharge functionality tested");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test draft saving functionality
     */
    @Test(priority = 8)
    public void testDraftSaving() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Fill partial information
        contractActions.fillCustomerInformationWithRandomData();
        
        // Try to save as draft
        if (contractActions.isElementVisible("xpath", "//button[contains(text(),'Save Draft')]")) {
            contractActions.saveDraft();
            System.out.println("Draft saving functionality tested");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test print quote functionality
     */
    @Test(priority = 9)
    public void testPrintQuote() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Fill required information and calculate quote
        contractActions.fillCustomerInformationWithRandomData();
        contractActions.fillVehicleInformationWithRandomData();
        
        // Test print quote if available
        if (contractActions.isElementVisible("xpath", "//button[contains(text(),'Print Quote')]")) {
            contractActions.printQuote();
            System.out.println("Print quote functionality tested");
        }
        
        loginActions.logout();
    }
    
    /**
     * Test tab navigation in contract form
     */
    @Test(priority = 10)
    public void testTabNavigation() throws InterruptedException {
        navigate();
        
        // Login
        loginActions.login(prop.getProperty("adminusername"), prop.getProperty("adminpassword"));
        dashboardActions.waitForDashboardToLoad();
        
        // Navigate to create contract
        contractActions.navigateToCreateContract();
        
        // Test tab navigation if tabs are present
        if (contractActions.isElementVisible("xpath", "//mat-tab[contains(.,'Customer Information')]")) {
            contractActions.clickCustomerInfoTab();
            contractActions.wait(1000);
            
            if (contractActions.isElementVisible("xpath", "//mat-tab[contains(.,'Vehicle Information')]")) {
                contractActions.clickVehicleInfoTab();
                contractActions.wait(1000);
            }
            
            if (contractActions.isElementVisible("xpath", "//mat-tab[contains(.,'Program Information')]")) {
                contractActions.clickProgramInfoTab();
                contractActions.wait(1000);
            }
            
            System.out.println("Tab navigation functionality tested");
        }
        
        loginActions.logout();
    }
}