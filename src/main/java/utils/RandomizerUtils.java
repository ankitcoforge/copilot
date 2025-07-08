package utils;

import java.util.Random;

public class RandomizerUtils {
    
    private static final Random random = new Random();
    
    /**
     * Generate random number within range
     */
    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    
    /**
     * Generate random string of specified length
     */
    public static String getRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return result.toString();
    }
    
    /**
     * Generate random email
     */
    public static String getRandomEmail() {
        return "test" + getRandomNumber(1000, 9999) + "@example.com";
    }
    
    /**
     * Generate random phone number
     */
    public static String getRandomPhoneNumber() {
        return String.format("(%03d) %03d-%04d", 
                           getRandomNumber(100, 999),
                           getRandomNumber(100, 999),
                           getRandomNumber(1000, 9999));
    }
    
    /**
     * Generate random VIN number
     */
    public static String getRandomVIN() {
        String chars = "ABCDEFGHJKLMNPRSTUVWXYZ123456789"; // Valid VIN characters
        StringBuilder vin = new StringBuilder();
        
        for (int i = 0; i < 17; i++) {
            vin.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        return vin.toString();
    }
    
    /**
     * Generate random mileage
     */
    public static String getRandomMileage() {
        return String.valueOf(getRandomNumber(10000, 100000));
    }
    
    /**
     * Generate random boolean
     */
    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }
}