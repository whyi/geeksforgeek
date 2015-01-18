package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class Sorting1 {

	private static ArrayList<Integer> selectionSort(ArrayList<Integer> array) {
		for (int i = 0; i < array.size(); ++i) {
			int minIndex = i;
			for (int j = i+1; j < array.size(); ++j) {
				if (array.get(minIndex) > array.get(j)) {
					minIndex = j;
				}
			}
			Collections.swap(array, i, minIndex);
		}
		return array;
	}
	
	@Test
	public void selectionSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, selectionSort(input));
	}

	private static ArrayList<Integer> bubbleSort(ArrayList<Integer> array) {
		final int n = array.size();
		for (int i = 0; i < n; ++i) {
			boolean swapped = false;
			for (int j = 0; j < n-i-1; ++j) {
				if (array.get(j) > array.get(j+1)) {
					Collections.swap(array, j, j+1);
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
		return array;
	}
	
	@Test
	public void bubbleSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, bubbleSort(input));		
	}

	// FIXME : not so intuitive, try with plain array than the arraylist.
	private static ArrayList<Integer> insertionSort(ArrayList<Integer> array) {
		final int n = array.size();
		
		for (int i = 1; i < n; ++i) {
			final Integer value = array.get(i);
			
			int j = i-1;
			while (j >= 0 && array.get(j) > value) {
				array.set(j+1, array.get(j));
				--j;
			}
			array.set(j+1, value);
		}
		return array;
	}
	
	@Test
	public void insertionSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, insertionSort(input));
	}
	
	private static void merge(int[] array, int left, int mid, int right) {
		System.out.println("merge " + left + " " + mid + " " + right);
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		
		for (int i = left; i < mid; ++i) {
			leftList.add(array[i]);
		}
		
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		
		for (int i = mid; i < right; ++i) {
			rightList.add(array[i]);
		}
		System.out.println("left : " + leftList);
		System.out.println("right: " + rightList);
		
		int i = 0;
		int j = 0;
		int k = left;
		while (i < leftList.size() && j < rightList.size()) {
			if (leftList.get(i) < rightList.get(j)) {
				array[k] = leftList.get(i);
				++i;
			}
			else {
				array[k] = rightList.get(j);
				++j;
			}
			++k;
		}
		
		// leftovers
		while (i < leftList.size()) {
			array[k] = leftList.get(i);
			++i;
			++k;
		}
		
		while (j < rightList.size()) {
			array[k] =  rightList.get(j);
			++j;
			++k;
		}
		System.out.print("merged : ");
		for (int val:array)
			System.out.print(val + " ");
		System.out.println();
	}
	
	 private static void merge11(int[] numbers, int low, int middle, int high) {
		 int[] helper = new int[numbers.length];
	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;

	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	      if (helper[i] <= helper[j]) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }
		System.out.print("merged : ");
		for (int val:numbers)
			System.out.print(val + " ");
		System.out.println();
	  }

	
	private static void mergeSortImpl(int[] array, int left, int right) {
		if (left < right) {
			final int mid = left+(right-left)/2;
			mergeSortImpl(array, left, mid);
			mergeSortImpl(array, mid+1, right);
			merge11(array, left, mid, right);
		}
	}
	
	private static ArrayList<Integer> mergeSort(int[] arr) {
		mergeSortImpl(arr, 0, arr.length-1);
		return null;
	}
	
	@Test
	public void mergeSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		
		int[] arr = {10,9,8,7,6};
		assertEquals(expected, mergeSort(arr));
	}
	
	private static ArrayList<Integer> quickSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void quickSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, quickSort(input));
	}
	
	private static ArrayList<Integer> heapSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void heapSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, heapSort(input));
	}	

	private static ArrayList<Integer> radixSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void radixSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, radixSort(input));
	}

}
