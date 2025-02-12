package HDT3;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingComparison {
    public static void main(String[] args) {
        int size = 3000;  // Puedes modificar manualmente este valor hasta 3000
        int[] numbers = generateRandomArray(size);

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(numbers));

        // Medir tiempos de ejecución para cada algoritmo
        measureSortingTime(numbers.clone(), "Insertion Sort", () -> insertionSort(numbers));
        measureSortingTime(numbers.clone(), "Merge Sort", () -> mergeSort(numbers));
        measureSortingTime(numbers.clone(), "Quick Sort", () -> quickSort(numbers));
        measureSortingTime(numbers.clone(), "Radix Sort", () -> radixSort(numbers));
        measureSortingTime(numbers.clone(), "Heap Sort", () -> heapSort(numbers));
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // Números entre 0 y 9999
        }
        return arr;
    }

    private static void measureSortingTime(int[] array, String sortName, Runnable sortingMethod) {
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();
        System.out.println(sortName + " took " + (endTime - startTime) / 1_000_000.0 + " ms");
    }

    @Test
    public void testSortingAlgorithms() {
        int[] array = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};

        int[] arrCopy = array.clone();
        insertionSort(arrCopy);
        assertArrayEquals(expected, arrCopy);

        arrCopy = array.clone();
        mergeSort(arrCopy);
        assertArrayEquals(expected, arrCopy);

        arrCopy = array.clone();
        quickSort(arrCopy);
        assertArrayEquals(expected, arrCopy);

        arrCopy = array.clone();
        radixSort(arrCopy);
        assertArrayEquals(expected, arrCopy);

        arrCopy = array.clone();
        heapSort(arrCopy);
        assertArrayEquals(expected, arrCopy);
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            arr[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];
        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[--count[(arr[i] / exp) % 10]] = arr[i];
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, left = 2 * i + 1, right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
