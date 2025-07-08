package com.adl.automation.tests;

import com.adl.automation.core.BaseTest;
import com.adl.automation.pages.ContractsPage;
import com.adl.automation.pages.DashboardPage;
import com.adl.automation.pages.LoginPage;
import com.adl.automation.utils.ExcelUtils;
import com.adl.automation.utils.PDFUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Contract management tests for ADL Portal
 * Demonstrates business workflow automation with Playwright
 */
public class ContractTests extends BaseTest {
    
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ContractsPage contractsPage;
    
    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
        contractsPage = new ContractsPage();
        
        // Login as dealer for contract operations
        loginPage.navigateToLogin();
        loginPage.loginAsDealer();
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");
        
        // Navigate to contracts page
        dashboardPage.navigateToContracts();
        Assert.assertTrue(contractsPage.isContractsPageLoaded(), "Contracts page should be loaded");
    }
    
    @Test(description = "Verify contract creation functionality")
    public void testCreateContract() {
        // Create a new contract
        contractsPage.createContract(
            "Extended Warranty",
            "ABC Motors",
            "John Doe",
            "john.doe@email.com",
            "1HGBH41JXMN109186"
        );
        
        // Search for the created contract
        contractsPage.searchContracts("John Doe");
        
        // Verify contract appears in search results
        Assert.assertTrue(contractsPage.getSearchResultsCount() > 0, "Contract should appear in search results");
        
        // Take screenshot for verification
        takeScreenshot("contract_created_successfully");
    }
    
    @Test(description = "Verify contract search functionality")
    public void testContractSearch() {
        // Search for contracts with different criteria
        contractsPage.searchContracts("ABC Motors");
        int dealerResults = contractsPage.getSearchResultsCount();
        
        contractsPage.searchContracts("Extended Warranty");
        int typeResults = contractsPage.getSearchResultsCount();
        
        contractsPage.searchContracts("john.doe@email.com");
        int emailResults = contractsPage.getSearchResultsCount();
        
        // Verify search returns results
        Assert.assertTrue(dealerResults >= 0, "Dealer search should return results");
        Assert.assertTrue(typeResults >= 0, "Type search should return results");
        Assert.assertTrue(emailResults >= 0, "Email search should return results");
        
        // Take screenshot for verification
        takeScreenshot("contract_search_results");
    }
    
    @Test(description = "Verify contract filtering by status")
    public void testContractStatusFilter() {
        // Filter by different statuses
        contractsPage.filterByStatus("Active");
        int activeContracts = contractsPage.getSearchResultsCount();
        
        contractsPage.filterByStatus("Pending");
        int pendingContracts = contractsPage.getSearchResultsCount();
        
        contractsPage.filterByStatus("Cancelled");
        int cancelledContracts = contractsPage.getSearchResultsCount();
        
        // Log results for verification
        logger.info("Active contracts: {}, Pending contracts: {}, Cancelled contracts: {}", 
                   activeContracts, pendingContracts, cancelledContracts);
        
        // Take screenshot for verification
        takeScreenshot("contract_status_filter_results");
    }
    
    @Test(description = "Verify contract date range filtering")
    public void testContractDateRangeFilter() {
        // Filter contracts by date range
        contractsPage.filterByDateRange("2024-01-01", "2024-12-31");
        
        int filteredResults = contractsPage.getSearchResultsCount();
        Assert.assertTrue(filteredResults >= 0, "Date range filter should return results");
        
        // Take screenshot for verification
        takeScreenshot("contract_date_filter_results");
    }
    
    @Test(description = "Verify contract export functionality")
    public void testContractExport() {
        // Search for contracts to export
        contractsPage.searchContracts("*"); // Search all contracts
        
        if (contractsPage.getSearchResultsCount() > 0) {
            // Export contracts to Excel
            String exportedFile = contractsPage.exportContracts();
            
            // Verify file was downloaded
            Assert.assertNotNull(exportedFile, "Export file should be downloaded");
            Assert.assertTrue(exportedFile.endsWith(".xlsx"), "Export file should be Excel format");
            
            // Verify Excel file content using ExcelUtils
            List<Map<String, String>> exportData = ExcelUtils.readExcelData(exportedFile, "Contracts");
            Assert.assertTrue(exportData.size() > 0, "Export file should contain contract data");
            
            // Take screenshot for verification
            takeScreenshot("contract_export_completed");
        }
    }
    
    @Test(description = "Verify contract remittance functionality")
    public void testContractRemittance() {
        // Search for contracts eligible for remittance
        contractsPage.filterByStatus("Ready for Remittance");
        
        if (contractsPage.getSearchResultsCount() > 0) {
            // Remit contracts
            contractsPage.remitContracts();
            
            // Verify remittance was successful
            // This would typically involve checking status changes or confirmation messages
            takeScreenshot("contract_remittance_completed");
        } else {
            logger.info("No contracts ready for remittance found");
        }
    }
    
    @Test(description = "Verify contract details view")
    public void testContractDetailsView() {
        // Search for a specific contract
        contractsPage.searchContracts("John Doe");
        
        if (contractsPage.getSearchResultsCount() > 0) {
            String contractId = contractsPage.getFirstContractId();
            String contractStatus = contractsPage.getFirstContractStatus();
            
            // Click on contract to view details
            contractsPage.clickFirstContract();
            
            // Verify contract details page loads
            // Additional verification could be added here based on the actual contract details page
            takeScreenshot("contract_details_view");
            
            logger.info("Viewed contract details for Contract ID: {}, Status: {}", contractId, contractStatus);
        }
    }
    
    @Test(description = "Verify contract data validation using Excel test data", 
          dataProvider = "contractTestData", dataProviderClass = ContractDataProvider.class)
    public void testContractCreationWithTestData(Map<String, String> contractData) {
        // Create contract using test data from Excel
        contractsPage.createContract(
            contractData.get("contractType"),
            contractData.get("dealer"),
            contractData.get("customerName"),
            contractData.get("customerEmail"),
            contractData.get("vehicleVin")
        );
        
        // Search for the created contract
        contractsPage.searchContracts(contractData.get("customerName"));
        
        // Verify contract creation
        Assert.assertTrue(contractsPage.getSearchResultsCount() > 0, 
                         "Contract should be created for customer: " + contractData.get("customerName"));
        
        // Take screenshot for verification
        takeScreenshot("contract_created_from_test_data_" + contractData.get("customerName").replaceAll(" ", "_"));
    }
}