package com.setokk;

import com.setokk.csv.CSVReader;
import com.setokk.csv.frequencycalculator.FrequencyCalculator;
import com.setokk.csv.splitter.Splitter;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static final int TOTAL_DAYS_IN_WEEK = 7;

    public static void main(String[] args)
    {
        String filename = System.getProperty("user.dir") + "\\Demo.csv";

        CSVReader.readCSVFile(filename);

        List<List<String>> result = CSVReader.getResult().orElseThrow();
        List<List<Double>> convertedResult = new ArrayList<>();

        List<Double> split;

        // We ignore first row (Day names)
        for (int i = 1; i < result.size(); i++)
        {
            for (int j = 0; j < result.get(i).size(); j++)
            {
                String times = result.get(i).get(j);
                convertedResult.add(Splitter.convertTimesToDouble(times));
            }
        }

        for (int i = 0; i < convertedResult.size(); i++)
        {
            for (int j = 0; j < convertedResult.get(i).size(); j++)
            {
                System.out.print(convertedResult.get(i).get(j) + ", ");
            }
            System.out.println();
        }

        // Hour frequencies for each day
        List<List<Double>> hourFrequencies = FrequencyCalculator.calculateHourFrequencies();



    }
}
