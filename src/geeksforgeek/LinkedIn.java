package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedIn {

	// assuming the window is sorted in ascending order by # of tickets available
	public int maxPrice(int[] window, int m) {
		System.out.println("m is " + m);
		int count = 0;
		int price = 0;
		for (int i = window.length-1; i >= 1; --i) {
			int ticketsInCurrentWindow = window[i];
			int ticketsInNextWindow = window[i-1];
			
			while (ticketsInCurrentWindow != ticketsInNextWindow-1 && ticketsInCurrentWindow != 0) {
				System.out.println("selling " + ticketsInCurrentWindow);
				price += ticketsInCurrentWindow;
				--ticketsInCurrentWindow; // mark as sold
				++count;
				if (count == m) {
					return price;
				}
			}
		}
		
		if (count < m) {
			int ticketsInCurrentWindow = window[0];
			while (ticketsInCurrentWindow != 0) {
				price += ticketsInCurrentWindow;
				--ticketsInCurrentWindow;
				++count;
				if (count == m) {
					return price;
				}				
			}
		}
		return 0;
	}

	@Test
	public void test() {
		/*
		 * 2. There are "n" ticket windows in the railway station.
		 * ith window has ai tickets available.
		 * Price of a ticket is equal to the number of tickets remaining in that window at that time.
		 * When "m" tickets have been sold, what's the maximum amount of money the railway station can earn?
		 * exa. n=2, m=4
		 * in 2 window available tickets are : 2 , 5
		 * 2nd window sold 4 tickets so 5+4+3+2=14.
		 */
		
		// ith window has ai tickets available.
		// so 5th window has 5 tickets available
		// and it's price would be 5+4+3+2 = 14 as described.
		// in order to maximize the value I would do the following
		// 1. sort the window by remaining # of tickets
		// 2. count = 0
		// 3. for each window wi and it's next window win
		//  while (# of ticket @ wi != # of ticket @ win) {
		//    price += # of ticket @ wi
		//    -- # of ticket @ wi
		//    ++count;
		//    if count == m
		//      return price;
		// }
		// complexity O(nlogn) for sorting unless it's already sorted by descending(or ascending order)
		
		int m = 4;
		int[] windows = {2,5};
		System.out.println(maxPrice(windows, m));
		System.out.println(maxPrice(windows, 5));
		// this worked...... can I do better? optimization?
		//
	}

}
