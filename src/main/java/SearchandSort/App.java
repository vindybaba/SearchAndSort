package SearchandSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class App {
    private static final String INPUT_CSV = "src/main/resources/Generated_Test_Cases.csv";  // Input CSV filename
    private static final String OUTPUT_CSV = "src/main/resources/output_results.csv";  // Output CSV filename
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        List<TestCaseResult> results = new ArrayList<>();

        try {
            // Read input CSV
            List<String[]> testCases = readCSV(INPUT_CSV);

            // Process each test case
            for (String[] testCase : testCases) {
                String testID = testCase[0];
                String inputArrayStr = testCase[1];
                String expectedArrayStr = testCase[2];
                String arrayType = testCase[3];

                if (arrayType.equalsIgnoreCase("Numeric")) {
                    Integer[] inputArray = parseNumericArray(inputArrayStr);
                    Integer[] expectedArray = parseNumericArray(expectedArrayStr);
                    results.addAll(runTestCase(testID, inputArray, expectedArray, arrayType));
                } else {
                    String[] inputArray = parseStringArray(inputArrayStr);
                    String[] expectedArray = parseStringArray(expectedArrayStr);
                    results.addAll(runTestCase(testID, inputArray, expectedArray, arrayType));
                }
            }

            // Write output CSV
            writeCSV(OUTPUT_CSV, results);
            System.out.println("Test execution completed. Output saved to " + OUTPUT_CSV);

        } catch (Exception e) {
            System.err.println("Error processing CSV file: " + e.getMessage());
        }
    }

    private static <T extends Comparable<T>> List<TestCaseResult> runTestCase(String testID, T[] inputArray, T[] expectedArray, String arrayType) {
        SortMethods<T> sorter = new SortMethods<>();
        List<TestCaseResult> results = new ArrayList<>();

        // Get all sorting methods dynamically
        Method[] methods = SortMethods.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("sort")) {
                long startTime, endTime, memoryBefore, memoryAfter;
                boolean testPassed = false;
                String exceptionMessage = "None";
                String sortingMethodUsed = method.getName();
                String executionTime = LocalDateTime.now().format(FORMATTER);

                // Measure memory before execution
                Runtime runtime = Runtime.getRuntime();
                runtime.gc(); // Run garbage collection before measuring memory
                memoryBefore = runtime.totalMemory() - runtime.freeMemory();

                // Measure time before execution
                startTime = System.nanoTime();

                try {
                    T[] copiedArray = Arrays.copyOf(inputArray, inputArray.length); // Copy array to preserve original
                    method.invoke(sorter, (Object) copiedArray); // Execute sorting method
                    testPassed = Arrays.equals(copiedArray, expectedArray);
                } catch (Exception e) {
                    exceptionMessage = e.getMessage();
                }

                // Measure time after execution
                endTime = System.nanoTime();
                memoryAfter = runtime.totalMemory() - runtime.freeMemory();

                long timeTaken = endTime - startTime;
                long spaceUsed = memoryAfter - memoryBefore;
                int arraySize = inputArray.length;

                results.add(new TestCaseResult(testID, Arrays.toString(inputArray), timeTaken, spaceUsed, arraySize, arrayType, sortingMethodUsed, testPassed, exceptionMessage, executionTime));
            }
        }
        return results;
    }

    private static List<String[]> readCSV(String filename) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {
            for (CSVRecord record : csvParser) {
                records.add(new String[]{record.get("TestID"), record.get("InputArray"), record.get("ExpectedArray"), record.get("ArrayType")});
            }
        }
        return records;
    }

    private static void writeCSV(String filename, List<TestCaseResult> results) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                     .setHeader("TestID", "SortedArray", "TimeComplexity(ns)", "SpaceComplexity(bytes)", "ArraySize", "ArrayType", "SortingMethod", "TestPassed", "Exception", "ExecutionTime")
                     .build())) {
            for (TestCaseResult result : results) {
                csvPrinter.printRecord(result.testID, result.sortedArray, result.timeComplexity, result.spaceComplexity, result.arraySize, result.arrayType, result.sortingMethod, result.testPassed, result.exceptionMessage, result.executionTime);
            }
            csvPrinter.flush();
        }
    }

    private static Integer[] parseNumericArray(String arrayStr) {
        return Arrays.stream(arrayStr.replace("[", "").replace("]", "").split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }

    private static String[] parseStringArray(String arrayStr) {
        return Arrays.stream(arrayStr.replace("[", "").replace("]", "").split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

    static class TestCaseResult {
        String testID;
        String sortedArray;
        long timeComplexity;
        long spaceComplexity;
        int arraySize;
        String arrayType;
        String sortingMethod;
        boolean testPassed;
        String exceptionMessage;
        String executionTime;

        public TestCaseResult(String testID, String sortedArray, long timeComplexity, long spaceComplexity, int arraySize, String arrayType, String sortingMethod, boolean testPassed, String exceptionMessage, String executionTime) {
            this.testID = testID;
            this.sortedArray = sortedArray;
            this.timeComplexity = timeComplexity;
            this.spaceComplexity = spaceComplexity;
            this.arraySize = arraySize;
            this.arrayType = arrayType;
            this.sortingMethod = sortingMethod;
            this.testPassed = testPassed;
            this.exceptionMessage = exceptionMessage;
            this.executionTime = executionTime;
        }
    }
}
