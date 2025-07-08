package pageObjects;

import utils.BaseClass;

public class ContractPageObjects extends BaseClass {
    
    // Contract creation form elements
    public static final String CREATE_CONTRACT_TITLE = "//h2[contains(text(),'Create Contract')]";
    public static final String CONTRACT_TYPE_DROPDOWN = "//select[contains(@id,'contractType')]";
    public static final String CUSTOMER_FIRST_NAME = "input[name='firstName']";
    public static final String CUSTOMER_LAST_NAME = "input[name='lastName']";
    public static final String CUSTOMER_EMAIL = "input[name='email']";
    public static final String CUSTOMER_PHONE = "input[name='phone']";
    
    // Vehicle information
    public static final String VIN_NUMBER = "input[name='vin']";
    public static final String VEHICLE_YEAR = "input[name='year']";
    public static final String VEHICLE_MAKE = "input[name='make']";
    public static final String VEHICLE_MODEL = "input[name='model']";
    public static final String VEHICLE_MILEAGE = "input[name='mileage']";
    public static final String VEHICLE_PRICE = "input[name='price']";
    
    // Program selection
    public static final String PROGRAM_DROPDOWN = "//select[contains(@id,'program')]";
    public static final String PROGRAM_TERM = "//select[contains(@id,'term')]";
    public static final String PROGRAM_DEDUCTIBLE = "//select[contains(@id,'deductible')]";
    
    // Additional options
    public static final String SURCHARGE_CHECKBOX = "//input[@type='checkbox' and contains(@name,'surcharge')]";
    public static final String SURCHARGE_AMOUNT = "input[name='surchargeAmount']";
    public static final String ADDITIONAL_COVERAGE = "//div[contains(@class,'additional-coverage')]";
    
    // Lender information
    public static final String LENDER_NAME = "input[name='lenderName']";
    public static final String LENDER_ADDRESS = "textarea[name='lenderAddress']";
    public static final String LENDER_PHONE = "input[name='lenderPhone']";
    
    // Form buttons
    public static final String CALCULATE_QUOTE_BUTTON = "//button[contains(text(),'Calculate Quote')]";
    public static final String GENERATE_CONTRACT_BUTTON = "//button[contains(text(),'Generate Contract')]";
    public static final String SAVE_DRAFT_BUTTON = "//button[contains(text(),'Save Draft')]";
    public static final String CANCEL_BUTTON = "//button[contains(text(),'Cancel')]";
    public static final String PRINT_QUOTE_BUTTON = "//button[contains(text(),'Print Quote')]";
    
    // Quote display
    public static final String QUOTE_TOTAL = "//div[contains(@class,'quote-total')]";
    public static final String QUOTE_BREAKDOWN = "//div[contains(@class,'quote-breakdown')]";
    public static final String QUOTE_TERMS = "//div[contains(@class,'quote-terms')]";
    
    // Contract search elements
    public static final String SEARCH_CONTRACT_TITLE = "//h2[contains(text(),'Search Contract')]";
    public static final String SEARCH_VIN = "input[name='searchVin']";
    public static final String SEARCH_CUSTOMER_NAME = "input[name='searchCustomerName']";
    public static final String SEARCH_CONTRACT_NUMBER = "input[name='searchContractNumber']";
    public static final String SEARCH_DATE_FROM = "input[name='dateFrom']";
    public static final String SEARCH_DATE_TO = "input[name='dateTo']";
    public static final String SEARCH_BUTTON = "//button[contains(text(),'Search')]";
    public static final String CLEAR_SEARCH_BUTTON = "//button[contains(text(),'Clear')]";
    
    // Search results
    public static final String SEARCH_RESULTS_TABLE = "//table[contains(@class,'search-results')]";
    public static final String SEARCH_RESULTS_ROWS = "//table[contains(@class,'search-results')]//tbody//tr";
    public static final String CONTRACT_NUMBER_COLUMN = "//td[contains(@class,'contract-number')]";
    public static final String CUSTOMER_NAME_COLUMN = "//td[contains(@class,'customer-name')]";
    public static final String VIN_COLUMN = "//td[contains(@class,'vin')]";
    public static final String STATUS_COLUMN = "//td[contains(@class,'status')]";
    public static final String ACTION_COLUMN = "//td[contains(@class,'actions')]";
    
    // Contract actions
    public static final String VIEW_CONTRACT_LINK = "//a[contains(text(),'View')]";
    public static final String EDIT_CONTRACT_LINK = "//a[contains(text(),'Edit')]";
    public static final String CANCEL_CONTRACT_LINK = "//a[contains(text(),'Cancel')]";
    public static final String PRINT_CONTRACT_LINK = "//a[contains(text(),'Print')]";
    
    // Contract details page
    public static final String CONTRACT_DETAILS_TITLE = "//h2[contains(text(),'Contract Details')]";
    public static final String CONTRACT_STATUS = "//span[contains(@class,'contract-status')]";
    public static final String CONTRACT_EFFECTIVE_DATE = "//span[contains(@class,'effective-date')]";
    public static final String CONTRACT_EXPIRY_DATE = "//span[contains(@class,'expiry-date')]";
    
    // Validation messages
    public static final String VALIDATION_MESSAGES = "//div[contains(@class,'validation-message')]";
    public static final String SUCCESS_MESSAGE = "//div[contains(@class,'success-message')]";
    public static final String ERROR_MESSAGE = "//div[contains(@class,'error-message')]";
    
    // Loading elements
    public static final String LOADING_SPINNER = "//ngx-spinner";
    public static final String PROGRESS_BAR = "//mat-progress-bar";
    
    // Modal dialogs
    public static final String CONFIRMATION_MODAL = "//div[contains(@class,'confirmation-modal')]";
    public static final String CONFIRM_YES_BUTTON = "//button[contains(text(),'Yes')]";
    public static final String CONFIRM_NO_BUTTON = "//button[contains(text(),'No')]";
    public static final String MODAL_CLOSE_BUTTON = "//button[contains(@class,'modal-close')]";
    
    // File upload
    public static final String FILE_UPLOAD_INPUT = "input[type='file']";
    public static final String UPLOAD_BUTTON = "//button[contains(text(),'Upload')]";
    public static final String UPLOADED_FILES_LIST = "//div[contains(@class,'uploaded-files')]";
    
    // Calendar/Date picker elements
    public static final String DATE_PICKER = "//mat-datepicker";
    public static final String DATE_INPUT = "input[matInput]";
    public static final String CALENDAR_PREV_MONTH = "//button[contains(@aria-label,'Previous month')]";
    public static final String CALENDAR_NEXT_MONTH = "//button[contains(@aria-label,'Next month')]";
    
    // Tabs
    public static final String CUSTOMER_INFO_TAB = "//mat-tab[contains(.,'Customer Information')]";
    public static final String VEHICLE_INFO_TAB = "//mat-tab[contains(.,'Vehicle Information')]";
    public static final String PROGRAM_INFO_TAB = "//mat-tab[contains(.,'Program Information')]";
    public static final String REVIEW_TAB = "//mat-tab[contains(.,'Review')]";
}