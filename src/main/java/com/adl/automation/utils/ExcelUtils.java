package com.adl.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for Excel file operations
 * Handles reading and writing Excel files for test data
 */
public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    
    /**
     * Read data from Excel file and return as List of Maps
     */
    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                logger.error("Sheet '{}' not found in file: {}", sheetName, filePath);
                return data;
            }
            
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                logger.error("Header row not found in sheet: {}", sheetName);
                return data;
            }
            
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(getCellValueAsString(cell));
            }
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    String value = cell != null ? getCellValueAsString(cell) : "";
                    rowData.put(headers.get(j), value);
                }
                data.add(rowData);
            }
            
            logger.info("Read {} rows from Excel file: {}, Sheet: {}", data.size(), filePath, sheetName);
            
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
        }
        
        return data;
    }
    
    /**
     * Write data to Excel file
     */
    public static void writeExcelData(String filePath, String sheetName, List<Map<String, String>> data) {
        if (data.isEmpty()) {
            logger.warn("No data to write to Excel file");
            return;
        }
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);
            
            // Create header row
            Map<String, String> firstRow = data.get(0);
            Row headerRow = sheet.createRow(0);
            List<String> headers = new ArrayList<>(firstRow.keySet());
            
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }
            
            // Create data rows
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, String> rowData = data.get(i);
                
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.createCell(j);
                    String value = rowData.get(headers.get(j));
                    cell.setCellValue(value != null ? value : "");
                }
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Write to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            logger.info("Written {} rows to Excel file: {}, Sheet: {}", data.size(), filePath, sheetName);
            
        } catch (IOException e) {
            logger.error("Error writing Excel file: {}", filePath, e);
        }
    }
    
    /**
     * Get cell value as string regardless of cell type
     */
    private static String getCellValueAsString(Cell cell) {
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
     * Read specific cell value
     */
    public static String getCellValue(String filePath, String sheetName, int rowIndex, int columnIndex) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                logger.error("Sheet '{}' not found in file: {}", sheetName, filePath);
                return "";
            }
            
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                logger.error("Row {} not found in sheet: {}", rowIndex, sheetName);
                return "";
            }
            
            Cell cell = row.getCell(columnIndex);
            return getCellValueAsString(cell);
            
        } catch (IOException e) {
            logger.error("Error reading cell value from Excel file: {}", filePath, e);
            return "";
        }
    }
    
    /**
     * Update specific cell value
     */
    public static void setCellValue(String filePath, String sheetName, int rowIndex, int columnIndex, String value) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
            
            Row row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            
            Cell cell = row.getCell(columnIndex);
            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            
            cell.setCellValue(value);
            
            // Write back to file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
            
            logger.info("Updated cell [{}, {}] with value '{}' in file: {}", rowIndex, columnIndex, value, filePath);
            
        } catch (IOException e) {
            logger.error("Error updating cell value in Excel file: {}", filePath, e);
        }
    }
}