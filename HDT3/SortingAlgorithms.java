package HDT3;

import java.util.*;
import java.io.*;

    public class SortingAlgorithms<T extends Comparable<T>> {

        // Insertion Sort - O(n^2)
        public void insertionSort(T[] arr) {
            for (int i = 1; i < arr.length; i++) {
                T key = arr[i];
                int j = i - 1;
                while (j >= 0 && arr[j].compareTo(key) > 0) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = key;
            }
        }

        // Merge Sort - O(n log n)
        public void mergeSort(T[] arr) {
            if (arr.length <= 1) return;

            int mid = arr.length / 2;
            T[] left = Arrays.copyOfRange(arr, 0, mid);
            T[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }

        private void merge(T[] arr, T[] left, T[] right) {
            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i].compareTo(right[j]) <= 0) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }
            while (i < left.length) arr[k++] = left[i++];
            while (j < right.length) arr[k++] = right[j++];
        }

        // Quick Sort - O(n log n) average, O(n^2) worst case
        public void quickSort(T[] arr) {
            quickSort(arr, 0, arr.length - 1);
        }

        private void quickSort(T[] arr, int low, int high) {
            if (low < high) {
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }

        private int partition(T[] arr, int low, int high) {
            T pivot = arr[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (arr[j].compareTo(pivot) <= 0) {
                    i++;
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            T temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }

        // Radix Sort (for Integers only) - O(d * n) where d is number of digits
        public void radixSort(Integer[] arr) {
            if (arr.length == 0) return;

            int max = Arrays.stream(arr).max(Integer::compareTo).get();

            for (int exp = 1; max / exp > 0; exp *= 10) {
                countingSort(arr, exp);
            }
        }

        private void countingSort(Integer[] arr, int exp) {
            Integer[] output = new Integer[arr.length];
            int[] count = new int[10];
            Arrays.fill(count, 0);

            for (int i = 0; i < arr.length; i++) {
                count[(arr[i] / exp) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                output[count[(arr[i] / exp) % 10] - 1] = arr[i];
                count[(arr[i] / exp) % 10]--;
            }

            System.arraycopy(output, 0, arr, 0, arr.length);
        }

        // Shell Sort - O(n^(3/2)) average case
        public void shellSort(T[] arr) {
            int n = arr.length;

            for (int gap = n/2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    T temp = arr[i];
                    int j;
                    for (j = i; j >= gap && arr[j - gap].compareTo(temp) > 0; j -= gap) {
                        arr[j] = arr[j - gap];
                    }
                    arr[j] = temp;
                }
            }
        }
    }