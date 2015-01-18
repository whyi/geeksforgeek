package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Sorting1 {

	private static ArrayList<Integer> selectionSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void selectionSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, selectionSort(input));
	}

	private static ArrayList<Integer> bubbleSort(ArrayList<Integer> array) {
		return null;
	}
	
	@Test
	public void bubbleSort() {
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(new Integer[]{10,9,8,7,6,1,2,3,4,5}));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10}));
		assertEquals(expected, bubbleSort(input));		
	}

	private static ArrayList<Integer> insertionSort(ArrayList<Integer> array) {
		return null;
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
