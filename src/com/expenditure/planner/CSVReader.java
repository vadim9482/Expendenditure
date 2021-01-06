package com.expenditure.planner;

import static com.expenditure.planner.Planner.SEPARATOR_LINE;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

    public List<String[]> csvRead(String inputString, String separator) {
        List<String[]> outList = new LinkedList<String[]>();
        for (String line : inputString.split(SEPARATOR_LINE)) {
            outList.add(line.split(separator));
        }
        return outList;
    }
}
