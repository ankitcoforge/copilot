package pageActions;

import pageObjects.ContractPageObjects;
import utils.PlaywrightUtils;
import utils.RandomizerUtils;
import utils.CalendarUtils;

import java.util.HashMap;
import java.util.List;

public class ContractPageActions extends PlaywrightUtils {
    
    /**
     * Navigate to create contract page
     */
    public void navigateToCreateContract() {
        clickElement("xpath", "//button[contains(text(),'Create Contract')]");
        waitForElementVisible("xpath", ContractPageObjects.CREATE_CONTRACT_TITLE);
    }
    
    /**
     * Fill customer information
     */
    public void fillCustomerInformation(String firstName, String lastName, String email, String phone) {
        waitForElementVisible("css", ContractPageObjects.CUSTOMER_FIRST_NAME);
        
        typeText("css", ContractPageObjects.CUSTOMER_FIRST_NAME, firstName);
        typeText("css", ContractPageObjects.CUSTOMER_LAST_NAME, lastName);
        typeText("css", ContractPageObjects.CUSTOMER_EMAIL, email);
        typeText("css", ContractPageObjects.CUSTOMER_PHONE, phone);
    }
    
    /**
     * Fill customer information with random data
     */
    public HashMap<String, String> fillCustomerInformationWithRandomData() {
        HashMap<String, String> customerData = new HashMap<>();
        
        String firstName = "Test" + RandomizerUtils.getRandomString(5);
        String lastName = "User" + RandomizerUtils.getRandomString(5);
        String email = RandomizerUtils.getRandomEmail();
        String phone = RandomizerUtils.getRandomPhoneNumber();
        
        fillCustomerInformation(firstName, lastName, email, phone);
        
        customerData.put("firstName", firstName);
        customerData.put("lastName", lastName);
        customerData.put("email", email);
        customerData.put("phone", phone);
        
        return customerData;
    }
    
    /**
     * Fill vehicle information
     */
    public void fillVehicleInformation(String vin, String year, String make, String model, String mileage, String price) {
        typeText("css", ContractPageObjects.VIN_NUMBER, vin);
        typeText("css", ContractPageObjects.VEHICLE_YEAR, year);
        typeText("css", ContractPageObjects.VEHICLE_MAKE, make);
        typeText("css", ContractPageObjects.VEHICLE_MODEL, model);
        typeText("css", ContractPageObjects.VEHICLE_MILEAGE, mileage);
        typeText("css", ContractPageObjects.VEHICLE_PRICE, price);
    }
    
    /**
     * Fill vehicle information with random data
     */
    public HashMap<String, String> fillVehicleInformationWithRandomData() {
        HashMap<String, String> vehicleData = new HashMap<>();
        
        String vin = RandomizerUtils.getRandomVIN();
        String year = String.valueOf(RandomizerUtils.getRandomNumber(2015, 2024));
        String make = "Toyota"; // Could be randomized from a list
        String model = "Camry"; // Could be randomized from a list
        String mileage = RandomizerUtils.getRandomMileage();
        String price = String.valueOf(RandomizerUtils.getRandomNumber(20000, 50000));
        
        fillVehicleInformation(vin, year, make, model, mileage, price);
        
        vehicleData.put("vin", vin);
        vehicleData.put("year", year);
        vehicleData.put("make", make);
        vehicleData.put("model", model);
        vehicleData.put("mileage", mileage);
        vehicleData.put("price", price);
        
        return vehicleData;
    }
    
    /**
     * Select contract program
     */
    public void selectProgram(String programType, String term, String deductible) {
        selectDropdownByText("xpath", ContractPageObjects.PROGRAM_DROPDOWN, programType);
        selectDropdownByText("xpath", ContractPageObjects.PROGRAM_TERM, term);
        selectDropdownByText("xpath", ContractPageObjects.PROGRAM_DEDUCTIBLE, deductible);
    }
    
    /**
     * Add surcharge if required
     */
    public void addSurcharge(String amount) {
        if (isElementVisible("xpath", ContractPageObjects.SURCHARGE_CHECKBOX)) {
            checkCheckbox("xpath", ContractPageObjects.SURCHARGE_CHECKBOX);
            
            if (isElementVisible("css", ContractPageObjects.SURCHARGE_AMOUNT)) {
                typeText("css", ContractPageObjects.SURCHARGE_AMOUNT, amount);
            }
        }
    }
    
    /**
     * Fill lender information
     */
    public void fillLenderInformation(String lenderName, String lenderAddress, String lenderPhone) {
        if (isElementVisible("css", ContractPageObjects.LENDER_NAME)) {
            typeText("css", ContractPageObjects.LENDER_NAME, lenderName);
            typeText("css", ContractPageObjects.LENDER_ADDRESS, lenderAddress);
            typeText("css", ContractPageObjects.LENDER_PHONE, lenderPhone);
        }
    }
    
    /**
     * Calculate quote
     */
    public String calculateQuote() {
        clickElement("xpath", ContractPageObjects.CALCULATE_QUOTE_BUTTON);
        waitForPageLoad();
        waitForElementVisible("xpath", ContractPageObjects.QUOTE_TOTAL);
        return getText("xpath", ContractPageObjects.QUOTE_TOTAL);
    }
    
    /**
     * Generate contract
     */
    public void generateContract() {
        clickElement("xpath", ContractPageObjects.GENERATE_CONTRACT_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Save as draft
     */
    public void saveDraft() {
        clickElement("xpath", ContractPageObjects.SAVE_DRAFT_BUTTON);
        waitForElementVisible("xpath", ContractPageObjects.SUCCESS_MESSAGE);
    }
    
    /**
     * Print quote
     */
    public void printQuote() {
        clickElement("xpath", ContractPageObjects.PRINT_QUOTE_BUTTON);
        // Handle print dialog if needed
        wait(2000);
    }
    
    /**
     * Navigate to contract search
     */
    public void navigateToContractSearch() {
        clickElement("xpath", "//button[contains(text(),'Search Contract')]");
        waitForElementVisible("xpath", ContractPageObjects.SEARCH_CONTRACT_TITLE);
    }
    
    /**
     * Search contracts by VIN
     */
    public void searchContractsByVIN(String vin) {
        waitForElementVisible("css", ContractPageObjects.SEARCH_VIN);
        clearField("css", ContractPageObjects.SEARCH_VIN);
        typeText("css", ContractPageObjects.SEARCH_VIN, vin);
        clickElement("xpath", ContractPageObjects.SEARCH_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Search contracts by customer name
     */
    public void searchContractsByCustomerName(String customerName) {
        waitForElementVisible("css", ContractPageObjects.SEARCH_CUSTOMER_NAME);
        clearField("css", ContractPageObjects.SEARCH_CUSTOMER_NAME);
        typeText("css", ContractPageObjects.SEARCH_CUSTOMER_NAME, customerName);
        clickElement("xpath", ContractPageObjects.SEARCH_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Search contracts by contract number
     */
    public void searchContractsByContractNumber(String contractNumber) {
        waitForElementVisible("css", ContractPageObjects.SEARCH_CONTRACT_NUMBER);
        clearField("css", ContractPageObjects.SEARCH_CONTRACT_NUMBER);
        typeText("css", ContractPageObjects.SEARCH_CONTRACT_NUMBER, contractNumber);
        clickElement("xpath", ContractPageObjects.SEARCH_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Search contracts by date range
     */
    public void searchContractsByDateRange(String fromDate, String toDate) {
        typeText("css", ContractPageObjects.SEARCH_DATE_FROM, fromDate);
        typeText("css", ContractPageObjects.SEARCH_DATE_TO, toDate);
        clickElement("xpath", ContractPageObjects.SEARCH_BUTTON);
        waitForPageLoad();
    }
    
    /**
     * Get search results count
     */
    public int getSearchResultsCount() {
        if (isElementVisible("xpath", ContractPageObjects.SEARCH_RESULTS_ROWS)) {
            return getElementCount("xpath", ContractPageObjects.SEARCH_RESULTS_ROWS);
        }
        return 0;
    }
    
    /**
     * Click on first contract in search results
     */
    public void clickFirstContractInResults() {
        if (getSearchResultsCount() > 0) {
            clickElement("xpath", ContractPageObjects.VIEW_CONTRACT_LINK, 0);
            waitForPageLoad();
        }
    }
    
    /**
     * Edit first contract in search results
     */
    public void editFirstContractInResults() {
        if (getSearchResultsCount() > 0) {
            clickElement("xpath", ContractPageObjects.EDIT_CONTRACT_LINK, 0);
            waitForPageLoad();
        }
    }
    
    /**
     * Get contract status from details page
     */
    public String getContractStatus() {
        if (isElementVisible("xpath", ContractPageObjects.CONTRACT_STATUS)) {
            return getText("xpath", ContractPageObjects.CONTRACT_STATUS);
        }
        return "";
    }
    
    /**
     * Get contract effective date
     */
    public String getContractEffectiveDate() {
        if (isElementVisible("xpath", ContractPageObjects.CONTRACT_EFFECTIVE_DATE)) {
            return getText("xpath", ContractPageObjects.CONTRACT_EFFECTIVE_DATE);
        }
        return "";
    }
    
    /**
     * Clear search form
     */
    public void clearSearchForm() {
        clickElement("xpath", ContractPageObjects.CLEAR_SEARCH_BUTTON);
        wait(1000);
    }
    
    /**
     * Get validation messages
     */
    public List<String> getValidationMessages() {
        return getAllTextValues("xpath", ContractPageObjects.VALIDATION_MESSAGES);
    }
    
    /**
     * Check if contract form is valid
     */
    public boolean isContractFormValid() {
        List<String> validationMessages = getValidationMessages();
        return validationMessages.isEmpty();
    }
    
    /**
     * Upload document
     */
    public void uploadDocument(String filePath) {
        if (isElementVisible("css", ContractPageObjects.FILE_UPLOAD_INPUT)) {
            uploadFile("css", ContractPageObjects.FILE_UPLOAD_INPUT, filePath);
            clickElement("xpath", ContractPageObjects.UPLOAD_BUTTON);
            wait(2000);
        }
    }
    
    /**
     * Confirm action in modal dialog
     */
    public void confirmAction() {
        if (isElementVisible("xpath", ContractPageObjects.CONFIRMATION_MODAL)) {
            clickElement("xpath", ContractPageObjects.CONFIRM_YES_BUTTON);
            waitForElementHidden("xpath", ContractPageObjects.CONFIRMATION_MODAL);
        }
    }
    
    /**
     * Cancel action in modal dialog
     */
    public void cancelAction() {
        if (isElementVisible("xpath", ContractPageObjects.CONFIRMATION_MODAL)) {
            clickElement("xpath", ContractPageObjects.CONFIRM_NO_BUTTON);
            waitForElementHidden("xpath", ContractPageObjects.CONFIRMATION_MODAL);
        }
    }
    
    /**
     * Navigate between tabs
     */
    public void clickCustomerInfoTab() {
        clickElement("xpath", ContractPageObjects.CUSTOMER_INFO_TAB);
        wait(1000);
    }
    
    public void clickVehicleInfoTab() {
        clickElement("xpath", ContractPageObjects.VEHICLE_INFO_TAB);
        wait(1000);
    }
    
    public void clickProgramInfoTab() {
        clickElement("xpath", ContractPageObjects.PROGRAM_INFO_TAB);
        wait(1000);
    }
    
    public void clickReviewTab() {
        clickElement("xpath", ContractPageObjects.REVIEW_TAB);
        wait(1000);
    }
    
    /**
     * Complete full contract creation workflow
     */
    public HashMap<String, String> createCompleteContract(String programType, String term, String deductible) {
        HashMap<String, String> contractData = new HashMap<>();
        
        // Navigate to create contract
        navigateToCreateContract();
        
        // Fill customer information
        HashMap<String, String> customerData = fillCustomerInformationWithRandomData();
        contractData.putAll(customerData);
        
        // Fill vehicle information
        HashMap<String, String> vehicleData = fillVehicleInformationWithRandomData();
        contractData.putAll(vehicleData);
        
        // Select program
        selectProgram(programType, term, deductible);
        contractData.put("program", programType);
        contractData.put("term", term);
        contractData.put("deductible", deductible);
        
        // Calculate quote
        String quoteTotal = calculateQuote();
        contractData.put("quoteTotal", quoteTotal);
        
        // Generate contract
        generateContract();
        
        return contractData;
    }
}