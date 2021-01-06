package com.expenditure.planner.dataCenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static com.expenditure.planner.Planner.RESOURCE_PATH;
import static com.expenditure.planner.Planner.SEPARATOR_LINE;

public class DataReader {

    static String read(String filename) {
        File file = new File(RESOURCE_PATH, filename);
        String line;
        StringBuilder output = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
                output.append(SEPARATOR_LINE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
