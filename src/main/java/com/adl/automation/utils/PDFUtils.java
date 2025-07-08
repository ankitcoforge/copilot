package com.adl.automation.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for PDF file operations
 * Handles reading and validating PDF files for test automation
 */
public class PDFUtils {
    private static final Logger logger = LoggerFactory.getLogger(PDFUtils.class);
    
    /**
     * Extract text from PDF file
     */
    public static String extractTextFromPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            logger.info("Extracted text from PDF: {}", filePath);
            return text;
        } catch (IOException e) {
            logger.error("Error extracting text from PDF: {}", filePath, e);
            return "";
        }
    }
    
    /**
     * Extract text from specific page of PDF
     */
    public static String extractTextFromPDFPage(String filePath, int pageNumber) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageNumber);
            stripper.setEndPage(pageNumber);
            String text = stripper.getText(document);
            logger.info("Extracted text from PDF page {} in file: {}", pageNumber, filePath);
            return text;
        } catch (IOException e) {
            logger.error("Error extracting text from PDF page {} in file: {}", pageNumber, filePath, e);
            return "";
        }
    }
    
    /**
     * Get number of pages in PDF
     */
    public static int getPDFPageCount(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            int pageCount = document.getNumberOfPages();
            logger.info("PDF {} has {} pages", filePath, pageCount);
            return pageCount;
        } catch (IOException e) {
            logger.error("Error getting page count from PDF: {}", filePath, e);
            return 0;
        }
    }
    
    /**
     * Verify if text exists in PDF
     */
    public static boolean verifyTextInPDF(String filePath, String expectedText) {
        String pdfText = extractTextFromPDF(filePath);
        boolean contains = pdfText.toLowerCase().contains(expectedText.toLowerCase());
        logger.info("Text '{}' {} in PDF: {}", expectedText, contains ? "found" : "not found", filePath);
        return contains;
    }
    
    /**
     * Verify if multiple texts exist in PDF
     */
    public static boolean verifyTextsInPDF(String filePath, List<String> expectedTexts) {
        String pdfText = extractTextFromPDF(filePath).toLowerCase();
        boolean allFound = true;
        
        for (String expectedText : expectedTexts) {
            if (!pdfText.contains(expectedText.toLowerCase())) {
                logger.warn("Text '{}' not found in PDF: {}", expectedText, filePath);
                allFound = false;
            }
        }
        
        logger.info("All expected texts {} in PDF: {}", allFound ? "found" : "not found", filePath);
        return allFound;
    }
    
    /**
     * Extract lines containing specific text from PDF
     */
    public static List<String> findLinesContainingText(String filePath, String searchText) {
        List<String> matchingLines = new ArrayList<>();
        String pdfText = extractTextFromPDF(filePath);
        
        String[] lines = pdfText.split("\\r?\\n");
        for (String line : lines) {
            if (line.toLowerCase().contains(searchText.toLowerCase())) {
                matchingLines.add(line.trim());
            }
        }
        
        logger.info("Found {} lines containing '{}' in PDF: {}", matchingLines.size(), searchText, filePath);
        return matchingLines;
    }
    
    /**
     * Validate PDF file format and accessibility
     */
    public static boolean isPDFValid(String filePath) {
        File file = new File(filePath);
        
        if (!file.exists()) {
            logger.error("PDF file does not exist: {}", filePath);
            return false;
        }
        
        if (!file.canRead()) {
            logger.error("PDF file is not readable: {}", filePath);
            return false;
        }
        
        try (PDDocument document = PDDocument.load(file)) {
            int pageCount = document.getNumberOfPages();
            if (pageCount <= 0) {
                logger.error("PDF file has no pages: {}", filePath);
                return false;
            }
            logger.info("PDF file is valid: {}", filePath);
            return true;
        } catch (IOException e) {
            logger.error("Invalid PDF file: {}", filePath, e);
            return false;
        }
    }
    
    /**
     * Wait for PDF file to be completely downloaded and readable
     */
    public static boolean waitForPDFDownload(String filePath, int timeoutSeconds) {
        int attempts = 0;
        int maxAttempts = timeoutSeconds;
        
        while (attempts < maxAttempts) {
            if (isPDFValid(filePath)) {
                logger.info("PDF file downloaded successfully: {}", filePath);
                return true;
            }
            
            try {
                Thread.sleep(1000); // Wait 1 second
                attempts++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted while waiting for PDF download");
                return false;
            }
        }
        
        logger.error("Timeout waiting for PDF download: {}", filePath);
        return false;
    }
    
    /**
     * Compare two PDF files by text content
     */
    public static boolean comparePDFContent(String filePath1, String filePath2) {
        String text1 = extractTextFromPDF(filePath1);
        String text2 = extractTextFromPDF(filePath2);
        
        boolean isEqual = text1.equals(text2);
        logger.info("PDF content comparison: {} vs {} - {}", filePath1, filePath2, isEqual ? "MATCH" : "DIFFERENT");
        return isEqual;
    }
}