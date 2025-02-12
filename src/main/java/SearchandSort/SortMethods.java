package SearchandSort;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SortMethods<T extends Comparable<T>> {

    private long startTime, endTime;
    private long memoryBefore, memoryAfter;

    public String execute(T[] array, String type) {
        System.out.println("Executing sorting algorithms for " + type + " array...");

        Method[] methods = this.getClass().getDeclaredMethods();
        String sortingMethodUsed = "Unknown";  // Store the sorting method used

        for (Method method : methods) {
            if (method.getName().startsWith("sort")) {
                try {
                    T[] copiedArray = Arrays.copyOf(array, array.length);
                    System.out.println("\nSorting using: " + method.getName());
                    sortingMethodUsed = method.getName();  // Capture the method name

                    // Measure memory before execution
                    Runtime runtime = Runtime.getRuntime();
                    runtime.gc(); // Run GC to reduce memory noise
                    memoryBefore = runtime.totalMemory() - runtime.freeMemory();

                    // Measure time before execution
                    startTime = System.nanoTime();

                    // Invoke the sorting method dynamically
                    method.invoke(this, (Object) array);

                    // Measure time after execution
                    endTime = System.nanoTime();

                    // Measure memory after execution
                    memoryAfter = runtime.totalMemory() - runtime.freeMemory();

                    // Print results
                    System.out.println("Sorted Array: " + Arrays.toString(copiedArray));
                    System.out.println("Time Taken: " + (endTime - startTime) + " ns");
                    System.out.println("Memory Used: " + (memoryAfter - memoryBefore) + " bytes");

                    break; // Only execute one sorting method

                } catch (Exception e) {
                    System.out.println("Error executing " + method.getName() + ": " + e.getMessage());
                }
            }
        }
        return sortingMethodUsed;
    }

    // Bubble Sort
    public void sortBubbleSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort (Recursive)
    public void sortQuickSort(T[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }

    private void quickSortHelper(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSortHelper(array, low, pi - 1);
            quickSortHelper(array, pi + 1, high);
        }
    }

    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Selection Sort
    public void sortSelectionSort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
    }

    // Insertion Sort
    public void sortInsertionSort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    // Merge Sort
    public void sortMergeSort(T[] array) {
        mergeSortHelper(array, 0, array.length - 1);
    }

    private void mergeSortHelper(T[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(array, left, mid);
            mergeSortHelper(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(T[] array, int left, int mid, int right) {
        T[] leftArr = Arrays.copyOfRange(array, left, mid + 1);
        T[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }

        while (i < leftArr.length) array[k++] = leftArr[i++];
        while (j < rightArr.length) array[k++] = rightArr[j++];
    }

    // Heap Sort
    public void sortHeapSort(T[] array) {
        PriorityQueue<T> heap = new PriorityQueue<>(Arrays.asList(array));
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.poll();
        }
    }

    // Utility function to swap elements
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
