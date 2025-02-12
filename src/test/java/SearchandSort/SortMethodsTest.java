package SearchandSort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class SortMethodsTest {  // ✅ Ensure class is not public (JUnit 5 does not require it)

    @Test  // ✅ Ensure this annotation is present for all tests
    void testBubbleSort_Numeric() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};

        sorter.sortBubbleSort(input);
        assertArrayEquals(expected, input, "Bubble Sort failed for numeric array");
    }

    @Test
    void testQuickSort_Numeric() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};

        sorter.sortQuickSort(input);
        assertArrayEquals(expected, input, "Quick Sort failed for numeric array");
    }

    @Test
    void testBubbleSort_String() {
        SortMethods<String> sorter = new SortMethods<>();
        String[] input = {"banana", "apple", "cherry", "mango"};
        String[] expected = {"apple", "banana", "cherry", "mango"};

        sorter.sortBubbleSort(input);
        assertArrayEquals(expected, input, "Bubble Sort failed for string array");
    }

    @Test
    void testQuickSort_String() {
        SortMethods<String> sorter = new SortMethods<>();
        String[] input = {"banana", "apple", "cherry", "mango"};
        String[] expected = {"apple", "banana", "cherry", "mango"};

        sorter.sortQuickSort(input);
        assertArrayEquals(expected, input, "Quick Sort failed for string array");
    }

    @Test
    void testQuickSort_StringDuplicates() {
        SortMethods<String> sorter = new SortMethods<>();
        String[] input = {"banana", "banana", "apple", "apple", "apple", "apple"};
        String[] expected = {"apple", "apple", "apple", "apple", "banana", "banana"};
        sorter.sortQuickSort(input);
        assertArrayEquals(expected, input, "Quick Sort failed for string array");
    }

    @Test
    void testExecute_Numeric() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};
        sorter.execute(input, "Numeric");
        assertArrayEquals(expected, input, "Execute failed for numeric array");
    }

    @Test
    void testSelectionSort() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};
        sorter.sortSelectionSort(input);
        assertArrayEquals(expected, input, "Selection Sort failed");
    }

    @Test
    void testInsertionSort() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};
        sorter.sortInsertionSort(input);
        assertArrayEquals(expected, input, "Insertion Sort failed");
    }

    @Test
    void testMergeSort() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};
        sorter.sortMergeSort(input);
        assertArrayEquals(expected, input, "Merge Sort failed");
    }

    @Test
    void testHeapSort() {
        SortMethods<Integer> sorter = new SortMethods<>();
        Integer[] input = {64, 25, 12, 22, 11};
        Integer[] expected = {11, 12, 22, 25, 64};
        sorter.sortHeapSort(input);
        assertArrayEquals(expected, input, "Heap Sort failed");
    }
    
}
