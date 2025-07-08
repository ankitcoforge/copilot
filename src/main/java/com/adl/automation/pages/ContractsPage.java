package com.adl.automation.pages;

/**
 * Contracts Page object for ADL Portal
 * Handles contract creation, search, and management
 */
public class ContractsPage extends BasePage {
    
    // Contract search elements
    private static final String SEARCH_INPUT = "#contractSearchInput";
    private static final String SEARCH_BUTTON = "#searchButton";
    private static final String SEARCH_RESULTS_TABLE = "#contractsTable";
    private static final String NO_RESULTS_MESSAGE = ".no-results";
    
    // Contract creation elements
    private static final String CREATE_CONTRACT_BUTTON = "#createContractButton";
    private static final String CONTRACT_TYPE_DROPDOWN = "#contractType";
    private static final String DEALER_DROPDOWN = "#dealerSelect";
    private static final String CUSTOMER_NAME_INPUT = "#customerName";
    private static final String CUSTOMER_EMAIL_INPUT = "#customerEmail";
    private static final String VEHICLE_VIN_INPUT = "#vehicleVin";
    private static final String SAVE_CONTRACT_BUTTON = "#saveContractButton";
    
    // Contract management elements
    private static final String REMIT_CONTRACTS_BUTTON = "#remitContractsButton";
    private static final String EXPORT_CONTRACTS_BUTTON = "#exportContractsButton";
    private static final String FILTER_STATUS_DROPDOWN = "#statusFilter";
    private static final String FILTER_DATE_FROM = "#dateFrom";
    private static final String FILTER_DATE_TO = "#dateTo";
    
    // Table elements
    private static final String CONTRACT_ROW = "tr[data-contract-id]";
    private static final String CONTRACT_ID_COLUMN = "td:nth-child(1)";
    private static final String CONTRACT_STATUS_COLUMN = "td:nth-child(2)";
    private static final String CUSTOMER_NAME_COLUMN = "td:nth-child(3)";
    private static final String CONTRACT_ACTIONS_COLUMN = "td:nth-child(-1)";
    
    /**
     * Verify contracts page is loaded
     */
    public boolean isContractsPageLoaded() {
        return isVisible(SEARCH_INPUT) && isVisible(CREATE_CONTRACT_BUTTON);
    }
    
    /**
     * Search for contracts
     */
    public void searchContracts(String searchTerm) {
        type(SEARCH_INPUT, searchTerm);
        click(SEARCH_BUTTON);
        waitForPageLoad();
        logger.info("Searched for contracts with term: {}", searchTerm);
    }
    
    /**
     * Start creating a new contract
     */
    public void startCreateContract() {
        click(CREATE_CONTRACT_BUTTON);
        waitForPageLoad();
        logger.info("Started creating new contract");
    }
    
    /**
     * Create a new contract with basic information
     */
    public void createContract(String contractType, String dealer, String customerName, 
                              String customerEmail, String vehicleVin) {
        startCreateContract();
        
        selectOption(CONTRACT_TYPE_DROPDOWN, contractType);
        selectOption(DEALER_DROPDOWN, dealer);
        type(CUSTOMER_NAME_INPUT, customerName);
        type(CUSTOMER_EMAIL_INPUT, customerEmail);
        type(VEHICLE_VIN_INPUT, vehicleVin);
        
        click(SAVE_CONTRACT_BUTTON);
        waitForPageLoad();
        
        logger.info("Created contract for customer: {}", customerName);
    }
    
    /**
     * Filter contracts by status
     */
    public void filterByStatus(String status) {
        selectOption(FILTER_STATUS_DROPDOWN, status);
        waitForPageLoad();
        logger.info("Filtered contracts by status: {}", status);
    }
    
    /**
     * Filter contracts by date range
     */
    public void filterByDateRange(String fromDate, String toDate) {
        type(FILTER_DATE_FROM, fromDate);
        type(FILTER_DATE_TO, toDate);
        click(SEARCH_BUTTON);
        waitForPageLoad();
        logger.info("Filtered contracts by date range: {} to {}", fromDate, toDate);
    }
    
    /**
     * Remit selected contracts
     */
    public void remitContracts() {
        click(REMIT_CONTRACTS_BUTTON);
        waitForPageLoad();
        logger.info("Remitted selected contracts");
    }
    
    /**
     * Export contracts to Excel
     */
    public String exportContracts() {
        click(EXPORT_CONTRACTS_BUTTON);
        String filePath = waitForDownload();
        logger.info("Exported contracts to: {}", filePath);
        return filePath;
    }
    
    /**
     * Get number of search results
     */
    public int getSearchResultsCount() {
        if (isVisible(NO_RESULTS_MESSAGE)) {
            return 0;
        }
        return page.locator(CONTRACT_ROW).count();
    }
    
    /**
     * Get contract ID from first result
     */
    public String getFirstContractId() {
        if (getSearchResultsCount() > 0) {
            return getText(CONTRACT_ROW + ":first-child " + CONTRACT_ID_COLUMN);
        }
        return "";
    }
    
    /**
     * Get contract status from first result
     */
    public String getFirstContractStatus() {
        if (getSearchResultsCount() > 0) {
            return getText(CONTRACT_ROW + ":first-child " + CONTRACT_STATUS_COLUMN);
        }
        return "";
    }
    
    /**
     * Click on first contract in results
     */
    public void clickFirstContract() {
        if (getSearchResultsCount() > 0) {
            click(CONTRACT_ROW + ":first-child " + CONTRACT_ID_COLUMN);
            waitForPageLoad();
            logger.info("Clicked on first contract in results");
        }
    }
    
    /**
     * Verify contract exists in results
     */
    public boolean isContractInResults(String contractId) {
        return isVisible("tr[data-contract-id='" + contractId + "']");
    }
}