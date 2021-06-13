package com.expenditure.planner.dao.csv;

import static com.expenditure.planner.Planner.SEPARATOR_LINE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileToString {

    static String read(String resoursePath, String filename) {
        File file = new File(resoursePath, filename);
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

    public List<String[]> csvToList(String inputString, String separator) {
        List<String[]> outList = new LinkedList<String[]>();
        for (String line : inputString.split(SEPARATOR_LINE)) {
            outList.add(line.split(separator));
        }
        return outList;
    }
}
