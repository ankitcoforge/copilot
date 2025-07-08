package pageObjects;

import utils.BaseClass;

public class DashboardPageObjects extends BaseClass {
    
    // Dashboard header elements
    public static final String DASHBOARD_TITLE = "[class='title-bar'] > h3";
    public static final String WELCOME_MESSAGE = "//b[text()='Welcome to your Protective ADL Portal!']";
    
    // Navigation menu elements
    public static final String LATERAL_MENU = "adl-nav-menu";
    public static final String VERTICAL_MENU = "mat-sidenav";
    public static final String MENU_TOGGLE = "button[aria-label='Toggle sidenav']";
    
    // Quick action buttons
    public static final String CREATE_CONTRACT_BUTTON = "//button[contains(text(),'Create Contract')]";
    public static final String SEARCH_CONTRACT_BUTTON = "//button[contains(text(),'Search Contract')]";
    public static final String REPORTS_BUTTON = "//button[contains(text(),'Reports')]";
    
    // Dashboard widgets/cards
    public static final String DASHBOARD_CARDS = ".dashboard-card";
    public static final String CONTRACT_COUNT_CARD = "//div[contains(@class,'contract-count')]";
    public static final String RECENT_CONTRACTS_CARD = "//div[contains(@class,'recent-contracts')]";
    
    // User profile elements
    public static final String USER_PROFILE_DROPDOWN = "//button[contains(@class,'user-profile')]";
    public static final String USER_NAME_DISPLAY = "//span[contains(@class,'user-name')]";
    public static final String LOGOUT_OPTION = "//button[contains(text(),'Logout')]";
    
    // Notification elements
    public static final String NOTIFICATION_BELL = "//button[contains(@class,'notification')]";
    public static final String NOTIFICATION_COUNT = "//span[contains(@class,'notification-count')]";
    public static final String NOTIFICATION_PANEL = "//div[contains(@class,'notification-panel')]";
    
    // Search functionality
    public static final String GLOBAL_SEARCH_BOX = "input[placeholder='Search...']";
    public static final String SEARCH_RESULTS = "//div[contains(@class,'search-results')]";
    
    // Help and support
    public static final String HELP_BUTTON = "//button[contains(@aria-label,'Help')]";
    public static final String SUPPORT_LINK = "//a[contains(text(),'Support')]";
    
    // Footer elements
    public static final String FOOTER = "footer";
    public static final String COPYRIGHT_TEXT = "//div[contains(@class,'copyright')]";
    public static final String VERSION_INFO = "//span[contains(@class,'version')]";
    
    // Loading elements
    public static final String LOADING_SPINNER = "//ngx-spinner";
    public static final String PROGRESS_BAR = "//mat-progress-bar";
    
    // Error/success messages
    public static final String SUCCESS_MESSAGE = "//div[contains(@class,'success-message')]";
    public static final String ERROR_MESSAGE = "//div[contains(@class,'error-message')]";
    public static final String TOAST_CONTAINER = "div[role='alertdialog']";
}