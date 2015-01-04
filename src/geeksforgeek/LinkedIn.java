package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class LinkedIn {

	// assuming the window is sorted in ascending order by # of tickets available
	// in order to sell it..
	// or let's look at the problem differently.
	// it's about computing sum..
	// given window # is n,
	// it's n + (n-1) + (n-2) + ... until the next window
	// and the next window has the same formula. isn't this a fibonacci number? (maybe?)
	// ask if this will be called millions of times. then go for dynamic fibonacci version
	
	public int maxPrice(int[] window, int m) {
		int count = 0;
		int price = 0;
		for (int i = window.length-1; i >= 1; --i) {
			int ticketsInCurrentWindow = window[i];
			int ticketsInNextWindow = window[i-1];
			
			while (ticketsInCurrentWindow != ticketsInNextWindow-1 && ticketsInCurrentWindow != 0) {
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
	
	@Test
	public void getIntComplement() {
		// get the maximum # can be represented using 32bits integer
		// given number, halve it
		// negate it.. get the signing bit off...
		// this gets kinda complicated need to refresh my memory
		
		
	}
	
	private static int find(BSTNode node, int key) {
		if (node == null) {
			return -1;
		}
		if (node.data == key) {
			return 1;
		}
		if (node.data > key) {
			return find(node.left, key);
		}
		if (node.data < key) {
			return find(node.right, key);
		}
		return -1;
	}
	
	public static HashMap<String, Boolean> alreadyPrinted = new HashMap<String, Boolean>();
	
	private static String makeSubStringFrom(ArrayList<Character> list) {
		String result = "";
		for (Character c: list) {
			result += c;
		}
		return result;
	}
	
	private static int substrings(String s, ArrayList<Character> list, int start) {
		String subString = makeSubStringFrom(list);
		if (!alreadyPrinted.containsKey(subString)) {
			alreadyPrinted.put(subString, true);
			//System.out.println(subString);
		}

		for (int i = start; i < s.length(); ++i) {
			list.add(s.charAt(i));
			substrings(s, list, i+1);
			list.remove(list.size()-1);
		}
		return -1;
	}
	
	@Test
	public void test1() {
		alreadyPrinted.clear();
		substrings("abc", new ArrayList<Character>(), 0);
		System.out.println("# of substrings : " + alreadyPrinted.size());
		alreadyPrinted.clear();
		substrings("aabc", new ArrayList<Character>(), 0);
		System.out.println("# of substrings : " + alreadyPrinted.size());
		alreadyPrinted.clear();
		substrings("abcd", new ArrayList<Character>(), 0);
		System.out.println("# of substrings : " + alreadyPrinted.size());		
		
		// this is duplicated.
		
		int expected = (int) Math.pow(2, 5);
		expected -= (int) Math.pow(2, 5-2);
		alreadyPrinted.clear();
		substrings("abcdd", new ArrayList<Character>(), 0);
		int actual = alreadyPrinted.size();
		System.out.println("# of substrings 1dups : " + alreadyPrinted.size());
		assertEquals(expected, actual);
		
		expected = (int) Math.pow(2, 5);
		expected -= (int) Math.pow(2, 5-2)*2;
		alreadyPrinted.clear();
		substrings("abddd", new ArrayList<Character>(), 0);
		actual = alreadyPrinted.size();
		System.out.println("# of substrings 2dups : " + alreadyPrinted.size());
		assertEquals(expected, actual);		
		
		
		alreadyPrinted.clear();
		substrings("abcde", new ArrayList<Character>(), 0);
		System.out.println("# of substrings : " + alreadyPrinted.size());

		/*
		 1) Given a binary search tree and a key, if found return 1 else -1.
		 2) Given a string find the number of distinct substrings (should optimize time and space complexity)
		  subsequence vs substring? subsequence should be n! isn't it? e.g(abc = 3! = 3*2*1 = 6).. I meant permutation maybe
		  abc, acb, bac, bca, cab, cba...... those are permutation
		  hangon.... what if it has duplications? such as aab.
		  you said # of distinct...
		  naive would be list all and using hashtable to filter those out.
		  substring should be 2^n
		  I figured that if I filter it somehow gets different #... depending on the # of duplications maybe
		  so I should be able to derive a formula here, in order to avoid using of the hashtable altogether.
		  not sure if the interviewer want to go that further but let me try
		  if there's a duplication that will create n more clones..
		  therefore it's 2^n - (# of duplications * 2^(n-2)) let's verify this.
		  ok. now let's revise it.
		  1. get the # of duplications
		  2. return 2^n - (# of duplications * 2^(n-2)) that's our number!!!! in O(n) time with O(1) space!!
		  
		 3) Given a string find the number of distinct palindromic substrings(should optimize time complexity)
		 */
	}
	
	@Test
	public void interview1() {
/*
 * Interview 1:
1) Given a boolean expression find the number of ways to parenthesis it so that it evaluates to true
2) http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
3) Given n stairs , you climb 1 , 2 or 3 stairs at a time . Find the number of ways to reach the nth step
 */
		// n%3
		// case 0 : 
		//   1 + 1 + 1
		//   1 + 2
		//   2 + 1
		//   3
		// case 1 : 
		//   1 + 1
		//   2
		// case 2 :
		//   1
		
		// e.g when n is 4
		//   it's case 1
		//   we have 2 choices really!?
		//   not really. it's actually case 0 * case 1
		//   which is 4 * 2 = 8 ways
		//   ok what about going directly as 2+2? is it covered?
		//   
	}
	
	@Test
	public void interview2() {
		/*
		 * Interview 2:
			1) Given the pointer to the root of the tree and two values val1 and val2 .
			Find the length of path between the values val1 and val2 in the binary tree.
			{ handle corner cases where both the values are on the same path}
		 */
		/*
		 * what if we could get the Lowest Common Ancestor or something?
		 * then the length would be sum of lengths from the parent to each node
		 * http://getpocket.com/a/read/276317253
		 */
		/*
		 * 2) Given a mapping between numbers and alphabets . Find the number of ways to decode a sequence of numbers
		 	eg: a - 21
		 		b - 2
		 		c - 54
		 		d - 5
		 		e - 4
		 		f - 1
		        2154
		     1) ac
		     2) ade
		     3) bfc
		     4) bfde
		  4 ways to decode 
		 */
	}
	
	@Test
	public void interview3() {
/*
 * Interview 3:
1) Given a matrix of 0 s and 1 s Find the number of connected components having 1s

   0 0 1 0 1
   0 1 1 0 1
   0 0 0 1 1
   0 0 1 1 0 
The above matrix has 2 components
constraints: Time complexity O(N) space complexity O(1)

	hmm.. very interesting. I was thinking of doing BFT and mark cells as visited as I go until cannot
	find more possible moves.
	But that require visited status (hash table).
	A quick walkaway from the problem : ask if he would consider bitmap as hashtable or O(1)??
	
	2) Given a tree check if it is a binary search tree or not
	   constraints: space complexity O(1)
	   
	   recursively
	   isBST(int parentValue, BSTNode node, boolean isLeft) {
	   	 if (node == null) {
	   	 	return true;
		 }
		
		// should be less than parentValue-1 when it's left
		// should be bigger than parentValue+1 when it's right
		if (isLeft && node.value >= parentValue-1)
			return false;
		}
	    else {
	    	if (node.value <= parentValue-1)
	    		return false;
	    }
   	 	return isBST(node.value, node.left, true) && isBST(node.value, node.right, false);
	   }
	   
	   and initially call
	   isBST(root.value, root.left, true) && isBST(root.value, root.right, false);
	   I remember there was an article for this in GeeksForGeeks let's review it!
	
 */
	}
}
