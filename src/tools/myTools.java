package tools;


import org.json.simple.JSONArray;

import java.util.Random;

// This class can be used to initialize the database connection
public class myTools {
    public static JSONArray shuffleJsonArray (JSONArray array) {
        // Implementing Fisher–Yates shuffle
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        for (int i = array.size() - 1; i >= 0; i--)
        {
            int j = rnd.nextInt(i + 1);
            // Simple swap
            Object object = array.get(j);
            array.set(j, array.get(i));
            array.set(i, object);
        }
        return array;
    }

    public static int string2int(String string){
        int number = 0;
        try {
            if (string != null && !string.isEmpty()) {
                number = Integer.parseInt(string);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }
}