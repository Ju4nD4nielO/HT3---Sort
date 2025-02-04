package HDT3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class SortingTest {
    private final SortingAlgorithms<Integer> sorter = new SortingAlgorithms<>();
    private final Random random = new Random();

    // Test helper method
    private Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }

    @Test
    void testInsertionSort() {
        Integer[] arr = generateRandomArray(100);
        Integer[] expected = arr.clone();
        Arrays.sort(expected);

        sorter.insertionSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testMergeSort() {
        Integer[] arr = generateRandomArray(100);
        Integer[] expected = arr.clone();
        Arrays.sort(expected);

        sorter.mergeSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testQuickSort() {
        Integer[] arr = generateRandomArray(100);
        Integer[] expected = arr.clone();
        Arrays.sort(expected);

        sorter.quickSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRadixSort() {
        Integer[] arr = generateRandomArray(100);
        Integer[] expected = arr.clone();
        Arrays.sort(expected);

        sorter.radixSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testShellSort() {
        Integer[] arr = generateRandomArray(100);
        Integer[] expected = arr.clone();
        Arrays.sort(expected);

        sorter.shellSort(arr);
        assertArrayEquals(expected, arr);
    }
}

// Profiler class for measuring execution time
class SortingProfiler {
    private final SortingAlgorithms<Integer> sorter = new SortingAlgorithms<>();

    public void profileAlgorithms() {
        int[] sizes = {10, 100, 500, 1000, 1500, 2000, 2500, 3000};

        for (int size : sizes) {
            System.out.println("\nTesting with array size: " + size);

            Integer[] arr = generateRandomArray(size);
            profileSort("Insertion Sort", () -> {
                Integer[] testArr = arr.clone();
                sorter.insertionSort(testArr);
            });

            profileSort("Merge Sort", () -> {
                Integer[] testArr = arr.clone();
                sorter.mergeSort(testArr);
            });

            profileSort("Quick Sort", () -> {
                Integer[] testArr = arr.clone();
                sorter.quickSort(testArr);
            });

            profileSort("Radix Sort", () -> {
                Integer[] testArr = arr.clone();
                sorter.radixSort(testArr);
            });

            profileSort("Shell Sort", () -> {
                Integer[] testArr = arr.clone();
                sorter.shellSort(testArr);
            });
        }
    }

    private void profileSort(String algorithmName, Runnable sortingFunction) {
        long startTime = System.nanoTime();
        sortingFunction.run();
        long endTime = System.nanoTime();

        double timeInMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%s took %.2f ms%n", algorithmName, timeInMs);
    }

    private Integer[] generateRandomArray(int size) {
        Random random = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}