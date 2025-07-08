package com.adl.automation.tests;

import com.adl.automation.core.ConfigManager;
import com.adl.automation.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Data Provider class for Contract tests
 * Provides test data from Excel files
 */
public class ContractDataProvider {
    
    @DataProvider(name = "contractTestData")
    public static Iterator<Object[]> getContractTestData() {
        String testDataPath = ConfigManager.getTestDataPath();
        String excelFile = testDataPath + "contract_test_data.xlsx";
        
        List<Map<String, String>> testData = ExcelUtils.readExcelData(excelFile, "ContractData");
        
        return testData.stream()
                .map(data -> new Object[]{data})
                .iterator();
    }
    
    @DataProvider(name = "loginTestData")
    public static Iterator<Object[]> getLoginTestData() {
        String testDataPath = ConfigManager.getTestDataPath();
        String excelFile = testDataPath + "login_test_data.xlsx";
        
        List<Map<String, String>> testData = ExcelUtils.readExcelData(excelFile, "LoginData");
        
        return testData.stream()
                .map(data -> new Object[]{data})
                .iterator();
    }
    
    @DataProvider(name = "userManagementTestData")
    public static Iterator<Object[]> getUserManagementTestData() {
        String testDataPath = ConfigManager.getTestDataPath();
        String excelFile = testDataPath + "user_management_test_data.xlsx";
        
        List<Map<String, String>> testData = ExcelUtils.readExcelData(excelFile, "UserData");
        
        return testData.stream()
                .map(data -> new Object[]{data})
                .iterator();
    }
}