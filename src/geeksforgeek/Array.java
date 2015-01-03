package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import org.junit.Test;

public class Array {

	public int[] sortedSubsequenceOfSize3(int[] array) {
		
		int[] smaller = new int[array.length];
		int min = 0;
		smaller[0] = -1;
		
		for (int i = 1; i < array.length; ++i) {
			if (array[i] <= array[min]) {
				smaller[i] = -1;
				min = i;
			}
			else {
				smaller[i] = min;
			}
		}
		
		int[] greater = new int[array.length];
		greater[array.length-1] = -1;
		int max = array.length-1;
//int[] input1 = {12, 11, 10, 5, 6, 2, 30};
		for (int i = array.length-2; i >= 0 ; --i) {
			if (array[i] >= array[max]) {
				greater[i] = -1;
				max = i;
			}
			else {
				greater[i] = max;
			}
		}
		
//		System.out.println("smaller ");
//		for (int i: smaller) {
//			System.out.format("%2d ", i);
//		}
//		System.out.println("\ngreater ");
//		for (int i: greater) {
//			System.out.format("%2d ", i);
//		}
		
		for (int i = 0; i < array.length; ++i) {
			if (smaller[i] != -1 && greater[i] != -1) {
				int[] result = new int[3];
				result[0] = array[smaller[i]];
				result[1] = array[i];
				result[2] = array[greater[i]];
				return result;
			}
		}
		return null;
	}
	
	@Test
	// Find a sorted subsequence of size 3 in linear time
	public void findASortedSubsequenceOfSize3InLinearTime() {
		int[] input1 = {12, 11, 10, 5, 6, 2, 30};
		int[] expected1 = {5,6,30};
		assertArrayEquals(expected1, sortedSubsequenceOfSize3(input1));
		
		int[] input2 = {1,2,3,4};
		int[] expected2 = {1,2,4}; // (or 5,6,4 or 1,2,4 or 2,3,4)
		assertArrayEquals(expected2, sortedSubsequenceOfSize3(input2));
		
		//int[] input3 = {12, 11, 10, 5, 6, 2, 30};
		//assertArrayEquals(null, sortedSubsequenceOfSize3(input3));
	}
	
	public final class Interval implements Comparable<Interval> {
		public int start;
		public int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Interval rhs) {
			return Integer.compare(this.start, rhs.start);
		}
	}

	private Interval[] mergeOverlappingIntervals(Interval[] intervals) {
		ArrayList<Interval> sortedIntervals = new ArrayList<Interval>(Arrays.asList(intervals));
		Collections.sort(sortedIntervals);
		
		Stack<Interval> stack = new Stack<Interval>();
		
//		System.out.print("sorted ");
//		for (Interval interval:sortedIntervals) {
//			System.out.print("(" + interval.start + ", " + interval.end + "), ");
//		}
//		System.out.println();
		stack.push(sortedIntervals.get(0));
		
		for (int i = 1; i < sortedIntervals.size(); ++i) {
			Interval top = stack.peek();
			Interval currentInterval = sortedIntervals.get(i);

			// not overlapping
			if (top.end < currentInterval.start) {
				stack.push(currentInterval);
			}
			else {
				top.end = currentInterval.end;
				top = stack.pop();
				stack.push(top);
			}
		}
		
			
		while (!stack.isEmpty()) {
			Interval interval = stack.peek();
			System.out.print("(" + interval.start + ", " + interval.end + "), ");
			stack.pop();
		}
		System.out.println();
		
		return null;
	}
	
	@Test
	public void mergeOverlappingIntervals() {
		Interval[] intervals1 = new Interval[] {
				new Interval(1,3),
				new Interval(2,4),
				new Interval(5,7),				
				new Interval(6,8)
			};
		mergeOverlappingIntervals(intervals1);
		
		
		Interval[] intervals2 = new Interval[] {
				new Interval(6,8),
				new Interval(1,3),
				new Interval(2,4),
				new Interval(4,7),
		};
		mergeOverlappingIntervals(intervals2);
		
		Interval[] intervals3 = new Interval[] {
				new Interval(1,3),
				new Interval(7,9),
				new Interval(4,6),
				new Interval(0,13),
		};
		mergeOverlappingIntervals(intervals3);
	}
	
	private static int longestIncreasingSubIntervals(Interval[] intervals) {
		/*
		 * Interval[] intervals1 = new Interval[] {
				new Interval(5,24),
				new Interval(15,25),
				new Interval(27,40),				
				new Interval(50,60)
			};
		 */
		ArrayList<Interval> sortedIntervals = new ArrayList<Interval>(Arrays.asList(intervals));
		Collections.sort(sortedIntervals);
	
		final int numberOfIntervals = intervals.length;
		int[] maxChainLength = new int[numberOfIntervals];
		for (int i = 0; i < numberOfIntervals; ++i) {
			maxChainLength[i] = 1;
		}
		
		for (int i = 1; i < numberOfIntervals; ++i) {
			for (int j = 0; j < i; ++j) {
				if (intervals[i].start > intervals[j].end && maxChainLength[i] < maxChainLength[j]+1) {
					maxChainLength[i] = maxChainLength[j]+1;
				}
			}
		}
		
		int max = 0;
		for (int i:maxChainLength) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}
	
	
	@Test
	public void longestIncreasingSubIntervals() {
		Interval[] intervals1 = new Interval[] {
				new Interval(5,24),
				new Interval(15,25),
				new Interval(27,40),				
				new Interval(50,60)
			};
		System.out.println("max chan length : " + longestIncreasingSubIntervals(intervals1));
	}
	
	private void findSubarrayWithGivenSum(int[] array, int sum) {
		int current_sum = array[0];
		int start = 0;
		for (int i = 1; i < array.length; ++i) {
			while (current_sum > sum && start < i-1) {
				current_sum = current_sum - array[start];
				++start;
			}
			
			if (current_sum == sum) {
				System.out.println("sum found between " + (i-1) + " and " + start);
				return;
			}
			
			if (i < array.length) {
				current_sum = current_sum + array[i];
			}
		}
		
		System.out.println("sum not found");
	}
	
	@Test
	public void findSubarrayWithGivenSum() {
		findSubarrayWithGivenSum(new int[] {15,2,4,8,9,5,10,23}, 33);
	}
	
	// O(n^3)
	private void findTripletSumToNumber(int[] array, int sum) {
		for (int i = 0; i < array.length-2; ++i) {
			for (int j = i+1; j < array.length-1; ++j) {
				for (int k = j+1; k < array.length; ++k) {
					if (array[i]+array[j]+array[k] == sum) {
						System.out.println("sum found " + array[i] + " " + array[j] + " " + array[k] + " = " + sum);
						return;
					}
				}
			}
		}
		System.out.println("sum not found");
	}

	// optimized O(n^2)
	// this looks familiar, check the youtube video to find out if the problem is identical.
	private void findTripletSumToNumberOptimized(Integer[] array, int sum) {
		ArrayList<Integer> sorted = new ArrayList<Integer>(Arrays.asList(array));
		Collections.sort(sorted); // we spent O(nlogn) here.
		
		// then O(n^2) here.
		for (int i = 0; i < sorted.size()-2; ++i) {
			final int currentValue = sorted.get(i);
			int left = i+1;
			int right = sorted.size()-1;

			while (left < right) {
				final int leftValue = sorted.get(left);
				final int rightValue = sorted.get(right);
				final int currentSum = currentValue + leftValue + rightValue;
				if (currentSum == sum) {
					System.out.println("sum found " + currentValue + " " + leftValue + " " + rightValue + " = " + sum);
					return;
				}
				else if (currentSum < sum) {
					++left;
				}
				else {
					--right;
				}
			}
		}
		System.out.println("sum not found");
	}
	
	@Test
	public void findTripletSumToNumber() {
		findTripletSumToNumber(new int[] {1,4,45,6,10,8}, 22);
		findTripletSumToNumberOptimized(new Integer[] {1,4,45,6,10,8}, 22);
		findTripletSumToNumberOptimized(new Integer[] {1,4,45,6,10,8}, 20);
	}
	
	private void printArray(int[] arr) {
		for (int i:arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	private int longestBitonicSequence(int[] arr) {
		final int length = arr.length;
		int[] lis = new int[length];
		
		for (int i = 0; i < length; ++i) {
			lis[i] = 1;
		}
		
		for (int i = 1; i < length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j]+1;
				}
			}
		}
		
		int[] lds = new int[length];
		
		for (int i = 0; i < length; ++i) {
			lds[i] = 1;
		}
		
		for (int i = length-2; i >= 0 ; --i) {
			for (int j = length-1; j > i; --j) {
				if (arr[i] > arr[j] && lds[i] < lds[j] + 1) {
					lds[i] = lds[j]+1;
				}
			}
		}

		printArray(lis);
		printArray(lds);
		int max = lis[0] + lds[0];
		for (int i = 0; i < length; ++i) {
			if (lis[i] + lds[i] > max) {
				max = lis[i] + lds[i];
			}
		}
		return max;
	}
	
	@Test
	public void longestBitonicSequence() {
		assertEquals(6, longestBitonicSequence(new int[] {1,4,45,6,10,8}));
	}
	
	@Test
	public void maximumDifferenceInArray() {
		int[] arr = {80,2,6,3,100};
		
		int[] diff = new int[arr.length];
		
		for (int i = 0; i < arr.length-1; ++i) {
			diff[i] = arr[i+1] - arr[i];
		}
		
		printArray(diff);
		int maxDiff = diff[0];
		for (int i = 1; i < arr.length-1; ++i) {
			// always wondered wtf this is.. figured that this is needed as we want the MAXIMUM sum.
			// therefore we bring in the previous sum into the consideration!
			if (diff[i-1] > 0) {
				diff[i] += diff[i-1];
				printArray(diff);
			}
			if (maxDiff < diff[i]) {
				maxDiff = diff[i];
			}
		}
		
		assertEquals(98, maxDiff);
	}
	
	// naive version O(n)
	// {0,1,5,7,8} -> expect 2
	private int findSmallestMissingInteger(int[] arr, int length, int range) {
		if (arr[0] != 0) {
			return 0;
		}
		
		for (int i = 1; i < length; ++i) {
			if (arr[i] != i) {
				return i;
			}
		}
		// if not found then the last+1 is the answer
		return length;
	}
	
	// TODO : implement the log(n) version... the kickass version

	@Test
	public void findSmallestMissingInteger() {
		assertEquals(2, findSmallestMissingInteger(new int[]{0,1,5,7,8}, 5, 7));
		assertEquals(3, findSmallestMissingInteger(new int[]{0,1,2,6,9}, 5, 7));
		assertEquals(0, findSmallestMissingInteger(new int[]{4,5,10,11}, 4, 12));
		assertEquals(4, findSmallestMissingInteger(new int[]{0,1,2,3}, 4, 5));
		assertEquals(8, findSmallestMissingInteger(new int[]{0,1,2,3,4,5,6,7,10}, 9, 10));
	}
}