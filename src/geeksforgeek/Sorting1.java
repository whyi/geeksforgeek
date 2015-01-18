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
	
	private static ArrayList<Integer> mergeSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void mergeSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, mergeSort(input));
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
