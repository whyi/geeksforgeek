package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class GreedyAlgorithms {

	@Test
	// http://www.geeksforgeeks.org/greedy-algorithms-set-1-activity-selection-problem/
	public void activitySelectionProblem() {
		// given two sets return the maximum possible activities one can perform
		int[] start = {1, 3, 0, 5, 8, 5};
		int[] finish = {2, 4, 6, 7, 9, 9}; // assuming finish is already sorted. Otherwise O(nlogn) to sort this.
		
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		// pick the 1st one
		int previousEndingTime = finish[0];
		result.add(0);
		
		for (int i = 1; i < finish.length; ++i) {
			if (previousEndingTime < start[i]) {
				result.add(i);
				previousEndingTime = finish[i];
			}
		}

		assertEquals(new ArrayList<Integer>(Arrays.asList(new Integer[] {0,1,3,4})), result);
	}

}
