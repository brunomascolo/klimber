package utils;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Methods {
    public Methods() {
    }
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String selectRandomString(List<String> stringList) {
        // Create a Random object
        Random random = new Random();

        // Generate a random index within the bounds of the list
        int randomIndex = random.nextInt(stringList.size());

        // Retrieve the string value at the random index
        return stringList.get(randomIndex);
    }

    public static String generateRandomString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }



    public static int parseStringToInt(String moneyAmount) {
        // Remove non-numeric characters
        String cleanAmount = moneyAmount.replaceAll("[^\\d]", "");

        // Parse the string into an integer
        int parsedAmount = Integer.parseInt(cleanAmount);

        return parsedAmount;
    }

}
