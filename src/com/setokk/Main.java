package com.setokk;

import com.setokk.csv.CSVReader;
import com.setokk.csv.CSVUtilities;
import com.setokk.csv.CSVWriter;
import com.setokk.frequencycalculator.FrequencyCalculator;
import com.setokk.splitter.Splitter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static final int TOTAL_DAYS_IN_WEEK = 7;

    public static void main(String[] args)
    {
        // Read file name
        String filename;

        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("Please enter the .csv file's path name: ");
            filename = scanner.nextLine();

        } while (!CSVUtilities.hasValidExtension(filename));


        // Read path to save result file
        String resultPath;
        boolean isDirectory;

        do
        {
            System.out.println("Please enter a path to save result file (must be directory): ");
            resultPath = scanner.nextLine();

            File path = new File(resultPath);
            isDirectory = path.isDirectory();

        } while (!isDirectory);

        // Read csv input file
        CSVReader.readCSVFile(filename);
        List<List<String>> result = CSVReader.getResult().orElseThrow();

        List<List<Double>> convertedTimes = new ArrayList<>();

        // We ignore first row (Day names)
        for (int i = 1; i < result.size(); i++)
        {
            for (int j = 0; j < result.get(i).size(); j++)
            {
                String times = result.get(i).get(j);
                convertedTimes.add(Splitter.convertTimesToDouble(times));
            }
        }

        // Hour frequencies for each day
        List<HashMap<Double, Integer>> hourFrequencies = FrequencyCalculator.calculateHourFrequencies(convertedTimes);

        if (!CSVWriter.writeCSVFile(hourFrequencies, resultPath))
        {
            System.out.println("An error occurred. Please check the csv file extension/format.");
            System.exit(1);
        }

        System.out.println("Result file was created successfully at " + resultPath + "!");
    }
}
