package com.setokk.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.setokk.Main.TOTAL_DAYS_IN_WEEK;

public final class CSVReader
{
    private static List<List<String>> result;

    public static void readCSVFile(String name)
    {
        // Create new list
        result = new ArrayList<>();

        String line;
        String splitBy = ",";

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name));

            while ((line = bufferedReader.readLine()) != null)
                result.add(Arrays.asList(line.split(splitBy, -1)));

            bufferedReader.close();
        }
        catch (IOException e)
        {
            System.exit(1);
        }
    }

    public static Optional<List<List<String>>> getResult()
    {
        return Optional.ofNullable(result);
    }
}
