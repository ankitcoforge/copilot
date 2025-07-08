package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class DataProviderUtils {
    
    /**
     * Generic data provider for Excel files
     */
    @DataProvider(name = "excelData")
    public Object[][] getExcelData() {
        return readExcelData("src/test/resources/testdata.xlsx", "Test");
    }
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return readExcelData("src/test/resources/testdata.xlsx", "Login");
    }
    
    @DataProvider(name = "contractData")
    public Object[][] getContractData() {
        return readExcelData("src/test/resources/testdata.xlsx", "Contract");
    }
    
    @DataProvider(name = "lenderData")
    public Object[][] getLenderData() {
        return readExcelData("src/test/resources/testdata.xlsx", "Lender");
    }
    
    @DataProvider(name = "twoProgramData")
    public Object[][] getTwoProgramData() {
        return readExcelData("src/test/resources/testdata.xlsx", "Two_program");
    }
    
    /**
     * Read data from Excel file
     */
    private Object[][] readExcelData(String filePath, String sheetName) {
        Object[][] excelData = null;
        
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in " + filePath);
            }
            
            int totalRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
            excelData = new Object[totalRows][1];
            
            int totalColumns = sheet.getRow(0).getLastCellNum();
            
            for (int i = 1; i <= totalRows; i++) {
                String[] rowData = new String[totalColumns];
                Row row = sheet.getRow(i);
                
                if (row == null) continue;
                
                for (int j = 0; j < totalColumns; j++) {
                    Cell cell = row.getCell(j);
                    String cellValue = getCellValue(cell);
                    rowData[j] = cellValue;
                }
                
                excelData[i - 1][0] = rowData;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }
        
        return excelData;
    }
    
    /**
     * Get cell value as string regardless of cell type
     */
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
    
    /**
     * Convert contract data array to HashMap for easier access
     */
    public HashMap<String, String> convertToContractData(String[] inputArray) {
        HashMap<String, String> contractData = new HashMap<>();
        
        if (inputArray.length > 0) contractData.put("Firstname", inputArray[0]);
        if (inputArray.length > 1) contractData.put("Lastname", inputArray[1]);
        if (inputArray.length > 2) contractData.put("Vin", inputArray[2]);
        if (inputArray.length > 3) contractData.put("Mileage", inputArray[3]);
        if (inputArray.length > 4) contractData.put("program", inputArray[4]);
        if (inputArray.length > 5) contractData.put("Surcharge", inputArray[5]);
        if (inputArray.length > 6) contractData.put("GenerateContract", inputArray[6]);
        
        return contractData;
    }
    
    /**
     * Convert two program contract data array to HashMap
     */
    public HashMap<String, String> convertToTwoProgramData(String[] inputArray) {
        HashMap<String, String> contractData = new HashMap<>();
        
        if (inputArray.length > 0) contractData.put("Firstname", inputArray[0]);
        if (inputArray.length > 1) contractData.put("Lastname", inputArray[1]);
        if (inputArray.length > 2) contractData.put("Vin", inputArray[2]);
        if (inputArray.length > 3) contractData.put("Mileage", inputArray[3]);
        if (inputArray.length > 4) contractData.put("programs", inputArray[4]);
        if (inputArray.length > 5) contractData.put("program2", inputArray[5]);
        if (inputArray.length > 6) contractData.put("Surcharge", inputArray[6]);
        if (inputArray.length > 7) contractData.put("GenerateContract", inputArray[7]);
        
        return contractData;
    }
    
    /**
     * Simple login data provider using properties
     */
    @DataProvider(name = "simpleLoginData")
    public Object[][] getSimpleLoginData() {
        return new Object[][] {
            {BaseClass.prop.getProperty("adminusername"), BaseClass.prop.getProperty("adminpassword")},
            // Add more login combinations as needed
        };
    }
}