package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class PDFUtils {
    
    /**
     * Extract text from PDF file
     */
    public String extractTextFromPDF(String pdfPath) {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to extract text from PDF: " + pdfPath, e);
        }
    }
    
    /**
     * Extract text from specific page of PDF
     */
    public String extractTextFromPDFPage(String pdfPath, int pageNumber) {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(pageNumber);
            stripper.setEndPage(pageNumber);
            return stripper.getText(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to extract text from PDF page: " + pageNumber, e);
        }
    }
    
    /**
     * Get number of pages in PDF
     */
    public int getPDFPageCount(String pdfPath) {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            return document.getNumberOfPages();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get page count from PDF: " + pdfPath, e);
        }
    }
    
    /**
     * Verify if PDF contains specific text
     */
    public boolean pdfContainsText(String pdfPath, String expectedText) {
        String pdfText = extractTextFromPDF(pdfPath);
        return pdfText.contains(expectedText);
    }
    
    /**
     * Check if file is downloaded in specified directory
     */
    public Set<String> getDownloadedFiles(String downloadPath) {
        Set<String> downloadedFiles = new HashSet<>();
        
        try {
            Path dir = Paths.get(downloadPath);
            if (Files.exists(dir) && Files.isDirectory(dir)) {
                Files.list(dir)
                     .filter(Files::isRegularFile)
                     .forEach(file -> downloadedFiles.add(file.getFileName().toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return downloadedFiles;
    }
    
    /**
     * Check if specific file is downloaded
     */
    public boolean isFileDownloaded(String downloadPath, String fileName) {
        Set<String> files = getDownloadedFiles(downloadPath);
        return files.contains(fileName);
    }
    
    /**
     * Wait for file to be downloaded
     */
    public boolean waitForFileDownload(String downloadPath, String fileName, int timeoutSeconds) {
        int waitTime = 0;
        int interval = 1; // Check every 1 second
        
        while (waitTime < timeoutSeconds) {
            if (isFileDownloaded(downloadPath, fileName)) {
                return true;
            }
            
            try {
                Thread.sleep(interval * 1000);
                waitTime += interval;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        
        return false;
    }
    
    /**
     * Delete file if exists
     */
    public boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Clean download directory
     */
    public void cleanDownloadDirectory(String downloadPath) {
        try {
            Path dir = Paths.get(downloadPath);
            if (Files.exists(dir) && Files.isDirectory(dir)) {
                Files.list(dir)
                     .filter(Files::isRegularFile)
                     .forEach(file -> {
                         try {
                             Files.delete(file);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}