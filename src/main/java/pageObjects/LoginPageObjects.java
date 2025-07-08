package pageObjects;

import utils.BaseClass;

public class LoginPageObjects extends BaseClass {
    
    // Login page elements - converted from Selenium locators to Playwright locators
    
    // Header elements
    public static final String PAGE_HEADER = "[class='title-bar'] > h3";
    public static final String ARROW_BUTTON = "header > div > div > button > mat-icon";
    public static final String LOGOUT_BUTTON = "//button[contains(text(),'Logout')]";
    
    // Login form elements
    public static final String USERNAME_FIELD = "input[placeholder='Enter your email']";
    public static final String PASSWORD_FIELD = "input[placeholder='Password']";
    public static final String LOGIN_BUTTON = "button[type='submit']";
    
    // Alternative login fields (for different login types)
    public static final String USERNAME_FIELD_ALT = "input[placeholder='Enter your username']";
    public static final String PASSWORD_FIELD_ALT = "input[placeholder='Enter your password']";
    
    // Error messages
    public static final String ERROR_MESSAGE = "adl-form-error > div > span";
    
    // Branding elements
    public static final String PROTECTIVE_LOGO = "//img[contains(@src,'img/Protective-logo')]";
    public static final String PROTECTIVE_LOGIN_TEXT1 = "//adl-login/h2/span/b";
    public static final String PROTECTIVE_LOGIN_TEXT2 = "//adl-login/h2/h1/span";
    public static final String PRIVACY_POLICY_TEXT = "//p[contains(text(),'you agree to Protective')]";
    public static final String PRIVACY_POLICY_LINK = "//p[contains(text(),'you agree to Protective')]/a";
    public static final String PROTECTIVE_LOGO_PRIVACY = "//a[@title='Protective Life']/img";
    public static final String PROTECTIVE_LOGO_FORGOT_PASSWORD = "//header/a/img";
    public static final String PROTECTIVE_LOGO_LANDING = "//img[contains(@src,'img/Protective')]";
    
    // Navigation elements
    public static final String MENU_BAR_LANDING = "adl-nav-menu>div";
    public static final String PHONE_NUMBER_LANDING = "(//b[text()='Protective Asset Protection']/../b)[3]";
    
    // OTP related elements
    public static final String EMAIL_ADDRESS_FIELD = "input[placeholder='Email Address']";
    public static final String SEND_VERIFICATION_CODE_BUTTON = "button[aria-label='Send verification code']";
    public static final String SEND_CODE_MOBILE_BUTTON = "button[id='sendCode']";
    public static final String VERIFICATION_CODE_FIELD = "input[aria-label='Verification code']";
    public static final String VERIFICATION_CODE_MOBILE_FIELD = "input[id='verificationCode']";
    public static final String VERIFY_CODE_BUTTON = "button[aria-label='Verify code']";
    public static final String VERIFY_CODE_MOBILE_BUTTON = "button[id='verifyCode']";
    public static final String CONTINUE_BUTTON = "button[aria-label='Continue']";
    
    // Welcome/Dashboard elements
    public static final String WELCOME_TITLE = "//b[text()='Welcome to your Protective ADL Portal!']";
    public static final String DASHBOARD_TITLE = "Dashboard";
    
    // Logout elements  
    public static final String LOGOUT_ARROW = "header > div > div > button > mat-icon";
    public static final String LOGOUT_MENU_ITEM = "//button[contains(text(),'Logout')]";
    
    // Navigation menu items (converted from original xpath patterns)
    public static final String REPORTS_BUTTON = "//button[contains(text(),'Reports')]";
    public static final String IMPERSONATE_LINK = "//a[contains(text(),'Impersonate')]";
    
    // Additional form elements that might be needed
    public static final String INPUT_FIELDS = "//input";
    public static final String SUBMIT_BUTTONS = "//button";
    
    // Frame elements (for older pages that might use frames)
    public static final String MAIN_CONTENT_FRAME = "1"; // Frame index
    
    // Close dialog/modal elements
    public static final String CLOSE_ICON = "mat-icon[contains(text(), 'close')]";
    public static final String CLOSE_BUTTON = "button[aria-label='Close']";
    
    // Loading/spinner elements
    public static final String LOADING_SPINNER = "//ngx-spinner/div";
    
    // Toast/notification elements
    public static final String TOAST_MESSAGE = "div[role='alertdialog']";
    
    // Dropdown role selection (for impersonation)
    public static final String ROLE_TYPE_DROPDOWN = "#ctl00_mainContent_DropDownListRoleType";
    public static final String USER_INPUT_FIELD = "#cnt table #container table tbody tr input";
    public static final String GET_USERS_BUTTON = "#ctl00_mainContent_ButtonGetUsers";
    public static final String IMPERSONATE_BUTTON = "#ctl00_mainContent_ASPxGridViewUsers_cell0_11_ASPxButtonImpersonate";
}