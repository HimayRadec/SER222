/**
 * (basic description of the program or class)
 * 
 * Completion time: (estimation of hours spent on this program)
 *
 * @author (your name), Acuna, Sedgewick
 * @version (a version number or a date)
 */

package edu.ser222.m02_01;


import java.sql.SQLOutput;
import java.util.Random;


public class CompletedBenchmarkTool implements BenchmarkTool {
    
    /***************************************************************************
     * START - SORTING UTILITIES, DO NOT MODIFY (FROM SEDGEWICK)               *
     **************************************************************************/
    
    public static void insertionSort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++)
        {
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..          
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
    
    
    public static void shellsort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        
        while (h >= 1) {
            // h-sort the array.
            for (int i = h; i < N; i++) {
                // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    // Half the data is 0s, half 1s
    @Override
    public Integer[] generateTestDataBinary(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        Integer[] testData = new Integer[size];

        // Fill half the array with 0s
        for (int i = 0; i < size/2; i++) testData[i] = 0;

        // Fill the other half with 1s
        for (int i = size/2; i < size; i++) testData[i] = 1;

        return testData;
    }


    @Override
    public Integer[] generateTestDataHalves(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        Integer[] testData = new Integer[size];

        int halfSize = size / 2;

        // Fill the first half with 0s
        for (int i = 0; i < halfSize; i++) {
            testData[i] = 0;
        }

        // Fill the second half with consecutive integers following the pattern
        int currentNumber = 1;
        int currentIndex = halfSize;

        // TODO: For sure some errors here
        while (currentIndex < size) {
            for (int i = 0; i < currentNumber && currentIndex < size; i++) {
                testData[currentIndex] = currentNumber;
                currentIndex++;
            }
            currentNumber++;
        }

        System.out.println("Generate Data Halves");
        for (Integer value : testData) {
            System.out.println(value);
        }

        return testData;
    }


    // Generates an array of integers where half the data is 0s, and half random
    @Override
    public Integer[] generateTestDataHalfRandom(int size) {
        // Check if size == 0 or less
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        Integer[] testData = new Integer[size];
        Random random = new Random();

        // Fill the first half with 0s
        for (int i = 0; i < size/2; i++) {
            testData[i] = 0;
        }

        for (int i = size/2; i < size; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        return testData;
    }

    @Override
    public double computeDoublingFormula(double t1, double t2){
        // Check for invalid times
        if (t1 <= 0 || t2 <= 0) {
            throw new IllegalArgumentException("Both times must be positive");
        }

        return Math.log(2) * (t2/t1);
    }

    @Override
    public double benchmarkInsertionSort(Integer[] small, Integer[] large){
        // TODO: Check this
//        if (small == null || large == null || small.length == 0 || large.length != 2 * small.length) {
//            throw new IllegalArgumentException("Invalid input arrays");
//        }


        // Measure the runtime of insertion sort for the small array
        Stopwatch smallStopwatch = new Stopwatch();
        insertionSort(small);
        double smallEndtime = smallStopwatch.elapsedTime();

        // Measure the runtime of insertion sort for the large array
        Stopwatch largeStopwatch = new Stopwatch();
        insertionSort(large);
        double largeEndtime = largeStopwatch.elapsedTime();

        // Compute the empirical 'b' value using the doubling formula

        return computeDoublingFormula(smallEndtime, largeEndtime);
    }

    @Override
    public double benchmarkShellsort(Integer[] small, Integer[] large){

        // Check for incorrect parameter values
//        if (small == null || large == null || small.length == 0 || large.length != 2 * small.length) {
//            throw new IllegalArgumentException("Invalid input arrays");
//        }

        // Measure the runtime of shellsort for the small array
        Stopwatch smallStopwatch = new Stopwatch();
        shellsort(small);
        double smallEndtime = smallStopwatch.elapsedTime();

        // Measure the runtime of shellsort for the large array
        Stopwatch largeStopwatch = new Stopwatch();
        shellsort(large);
        double largeEndtime = largeStopwatch.elapsedTime();

        return computeDoublingFormula(smallEndtime, largeEndtime);
    }

    @Override
    public void runBenchmarks(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }

        // Benchmark insertion sort and shellsort for each dataset
        double bValueInsertionBinary = benchmarkInsertionSort(generateTestDataBinary(size), generateTestDataBinary(size*2));
        double bValueInsertionHalves = benchmarkInsertionSort(generateTestDataHalves(size), generateTestDataHalves(size*2));
        double bValueInsertionHalfRandom = benchmarkInsertionSort(generateTestDataHalfRandom(size), generateTestDataHalfRandom(size*2));

        double bValueShellSortBinary = benchmarkShellsort(generateTestDataBinary(size), generateTestDataBinary(size*2));
        double bValueShellSortHalves = benchmarkShellsort(generateTestDataHalves(size), generateTestDataHalves(size*2));
        double bValueShellSortHalfRandom = benchmarkShellsort(generateTestDataHalfRandom(size), generateTestDataHalfRandom(size*2));

        System.out.println("         Insertion    Shellsort");
        System.out.printf("Bin      %.4f       %.4f%n", bValueInsertionBinary, bValueShellSortBinary);
        System.out.printf("Half     %.4f       %.4f%n", bValueInsertionHalves, bValueShellSortHalves);
        System.out.printf("RanInt   %.4f       %.4f%n", bValueInsertionHalfRandom, bValueShellSortHalfRandom);
    }


    /***************************************************************************
     * END - SORTING UTILITIES, DO NOT MODIFY                                  *
     **************************************************************************/

    //TODO: implement interface methods.



    public static void main(String args[]) {
        BenchmarkTool me = new CompletedBenchmarkTool();
        int size = 8;
        
        //NOTE: feel free to change size here. all other code must go in the
        //      methods.
        
        me.runBenchmarks(size);
    }
}