package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

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
		//System.out.println("# of substrings : " + alreadyPrinted.size());
		alreadyPrinted.clear();
		substrings("aabc", new ArrayList<Character>(), 0);
		//System.out.println("# of substrings : " + alreadyPrinted.size());
		alreadyPrinted.clear();
		substrings("abcd", new ArrayList<Character>(), 0);
		//System.out.println("# of substrings : " + alreadyPrinted.size());		
		
		// this is duplicated.
		
		int expected = (int) Math.pow(2, 5);
		expected -= (int) Math.pow(2, 5-2);
		alreadyPrinted.clear();
		substrings("abcdd", new ArrayList<Character>(), 0);
		int actual = alreadyPrinted.size();
		//System.out.println("# of substrings 1dups : " + alreadyPrinted.size());
		assertEquals(expected, actual);
		
		expected = (int) Math.pow(2, 5);
		expected -= (int) Math.pow(2, 5-2)*2;
		alreadyPrinted.clear();
		substrings("abddd", new ArrayList<Character>(), 0);
		actual = alreadyPrinted.size();
		//System.out.println("# of substrings 2dups : " + alreadyPrinted.size());
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
	
	private int lengthBetweenTwoNodes(BSTNode root, int val1, int val2) {
		return 0;
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
	ask if he would consider bitmap as hashtable or O(1)??
	
	2) Given a tree check if it is a binary search tree or not
	   constraints: space complexity O(1)
	   // inorder traversal
	   I remember there was an article for this in GeeksForGeeks let's review it!
		http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
 */
	}
	
	@Test
	public void isNumber() {
		/*
		 * Write an function to judge whether the input String is a number? 
For example: "-3.3425","80.0", both of them are number
		 */
		
//		int count = 0;
//		String s = "123.90";
//		boolean isNumber = true;
//		while (true) {
//			char c = s.charAt(count);
//			if (count == 0) {
//				if (!Character.isDigit(c) && !(c == '-')) {
//					isNumber = false;
//					break;
//				}
//			}
//			if (count > 0) {
//				
//			}
//			else {
//				if (!Character.isDigit(c)) {
//					isNumber = false;
//					break;
//				}
//			}
//			++count;
//		}
		
		//Character.isDigit(arg0)
	}
	
	// 1. verifying the optimal substructure
	//    exponential 2^n or something

	public static int[] naiveHashmap = new int[500];
	private static boolean karmaJump(boolean[] array, int index, int velocity) {
		// base case for true
//		if (naiveHashmap[index] != -1) {
//			if (naiveHashmap[index] == velocity) {
//				System.out.println("found a match @ " + index + " " + velocity);
//			}
//		}
//		else {
//			naiveHashmap[index] = velocity;
//		}
//		System.out.println("KarmaJump " + index + " " + velocity);
		if (index >= array.length) {
			return true;
		}
		
		if (array[index] == false) {
			return false;
		}
		else {
			if (velocity == 1) {
				return karmaJump(array, index+velocity, velocity) ||
						karmaJump(array, index+velocity+1, velocity+1);
			}
			else {
				return karmaJump(array, index+velocity, velocity) ||
						karmaJump(array, index+velocity-1, velocity-1) ||
						karmaJump(array, index+velocity+1, velocity+1);				
			}
		}
	}
	
	// 2. overlapping subproblems
	// decide which one to use:
	//  1) top down  - memorization
	//  2) bottom up - tabulation
	// needs to be a nice clean code easy to understand!
	private static boolean karmaJumpDP(boolean[] array, int index, int velocity) {
		return false;
	}
	
	// love to be smart with this... how?
	// can we do backtrack? maybe I am overthinking?
	// but it seems like the problem sums up to find the existance of "true" with the velocity which can overshoot the
	// array length n
	
	@Test
	public void karmaJump() {
		for (int i = 0; i < 500; ++i) {
			naiveHashmap[i] = -1;
		}
		//System.out.println("KARMA JUMP STARTED");
		boolean[] notPassable = new boolean[]{true, true, false, true, false, true, false, false, false, true};
		assertEquals(false, karmaJump(notPassable, 0, 1));
		for (int i = 0; i < 500; ++i) {
			naiveHashmap[i] = -1;
		}		
		//System.out.println("KARMA JUMP2 STARTED");
		boolean[] passable = new boolean[]{true, true, false, true, false, true, true, false, false, true, false};
		assertEquals(true, karmaJump(passable, 0, 1));
		
		// Let's cover some corenre cases.
		boolean[] nothing = new boolean[]{};
		assertEquals(true, karmaJump(nothing, 0, 1));
		
		boolean[] falseOnly;
		for (int i = 1; i < 10; ++i) {
			falseOnly = new boolean[i];
			Arrays.fill(falseOnly, false);
			assertEquals(false, karmaJump(falseOnly, 0, 1));
		}
		
		boolean[] trueOnly;
		for (int i = 1; i < 10; ++i) {
			trueOnly = new boolean[i];
			Arrays.fill(trueOnly, true);
			assertEquals(true, karmaJump(trueOnly, 0, 1));
		}
	}
	
	// again DP problem but can we be fancy?!
	private static int fib(int n) {
		if (n<=1) {
			return n;
		}
		
		return fib(n-2)+fib(n-1);
	}
	
	@Test
	// FIXME : move to Karma.java!
	// TODO: move to Karma.java!
	public void karmaEvenFibonacci() {
		int prev1 = 1;
		int prev2 = 0;
		int current = 0;

		int n = 10;
		int totalSum = 0;
		
		while (totalSum <= n) {
			current = prev1+prev2;
			
			if (current%2 == 0) {
				totalSum += current;
			}

			if ((totalSum + current) > n) {
				break;
			}
			
			prev2 = prev1;
			prev1 = current;
		}
		System.out.println("current " + current + " vs " + fib(10));
		System.out.println("even fibonacci up to " + n + " = " + totalSum);
	}
	
	@Test
	public void test12345() {
//		Write a program that gives count of common characters presented
		//in an array of strings..(or array of character arrays) 
//
//		For eg.. for the following input strings.. 
//
//		aghkafgklt 
//		dfghako 
//		qwemnaarkf 
//
//		The output should be 3. because the characters a, f and k are present in all 3 strings. 
//
//		Note: The input strings contains only lower case alphabets
		// would hash help? O(n) what if we cann't use hash?
		// would sorting help? O(nlogn)(and the other two mlogm and ologo) + O(m+n+o) == O(nlogn)?? 
		// 
		// corner cases : 1) any of three arrays is empty
		//                2) duplicated chars.
		//                   so it should be
		//                   for (...) {
		//                        if (map.contains(char))
		//                        	continue;
		//                        else
		//                          map.put(char, 1);
		//                   }
		// what about the next loop?
		//                   for( ...) {
		//                      if (map.contains(char))
		//                          map.put(char, 2)
		//                      else
		//                          continue...
		String s1 = "aghkafgklt";
		String s2 = "dfghako";
		String s3 = "qwemnaarkf";
		
		// optimization : i-97 will give us only alphabet range (0-27) whatever.
		int[] map = new int[128];
		for (int i = 0; i < 128; ++i) {
			map[i] = -1;
		}

		for (Character c:s1.toCharArray()) {
			map[c.charValue()] = 1;
		}
		
		for (Character c:s2.toCharArray()) {
			if (map[c.charValue()] == 1)
				map[c.charValue()] = 2;
		}
		
		for (Character c:s3.toCharArray()) {
			if (map[c.charValue()] == 2)
				map[c.charValue()] = 3;
		}
		
		for (int i = 0; i < 128; ++i) {
			if (map[i] == 3) {
				char c = (char) i;
				System.out.print(c + " ");
			}
		}
	
		System.out.println();
	}
	
	@Test
	public void findPermutation() {
		// Given a string array ex: [1, 2, 3], find the permutation in best time
		// corner case : duplication
		// let's first go with the
		// for (idx < length, ++idex) {
		//    swap(idx, length-1)
		//    permutation(idx+1)
		//    swap(idx, length-1)
		// }
	}
	
	@Test
	public void asdf() {
		/* This class will be given a list of words (such as might be tokenized
		 * from a paragraph of text), and will provide a method that takes two
		 * words and returns the shortest distance (in words) between those two
		 * words in the provided text. 
		 * Example:
		 *   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
		 *   assert(finder.distance("fox","the") == 3);
		 *   assert(finder.distance("quick", "fox") == 1);
		 */
		
		// cool...... naive solution :
		// HashMap of String to list<Integers>.. or maybe TreeMap or something, just have it automatically sorted
		// FIXME : look for java container that does this, otherwise I will be in trouble!!
		// given two words I end up two lists.
		// given that those two are sorted it sums up to find minimum distance between two list in linear time(maybe)
		// (see if we can do it in logn)
		// preprocessing : check the size, if one of them is 1 then easy, just do logn search in the other array and done
		// preprocessing : compare the biggest vs the smallest in two and determine if they're overlapping
		//                 otherwise that will be the smallest distance
		// http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
		// compare as we go
		// say a = {2,3,4} b = {7,8,9}
		//
		// while (i < a.size && j < b.size) {
		//   if (abs(a[i]-b[j]) < min) { min = abs(a[i]-b[j]); }
		//   if (a[i] < b[j])
		//     ++i;
		//   else
		//     ++j;
		// }
		//
		// we should have minimum available here
		// O(l1+l2) where l1 = a.length and l2 = b.length
		// therefore, total complexity = 
		// 1. build  : O(nlogn) where n is # of words because of the treemap(to maintain the sorted order) in the worst case
		// 2. lookup : O(n) in the worst case, let's see why.
		//    {"a","a","b","b","b","b","b","b"}
		//  {2,7,8} {4,5,6}
		// can we do in logn please...... not sure
		//
		
	}
	
	@Test
	public void findCommonAncestor() {
		
	}
//		public interface FirstCommonAncestor { 

			/** 
			* Given two nodes of a tree, 
			* method should return the deepest common ancestor of those nodes. 
			* 
			* A 
			* / \ 
			* B C 
			* / \ \ 
			* D E M 
			* / \ 
			* G F 
			* 
			* commonAncestor(D, F) = B 
			* commonAncestor(C, G) = A 
			*/ 

//			public Node commonAncestor(Node nodeOne, Node nodeTwo) 
//			{ 
//
//			} 
//			} 
//
//			class Node { 
//
//			final Node parent; 
//			final Node left; 
//			final Node right; 
//
//
//			public Node(Node parent, Node left, Node right, data) { 
//			this.parent = parent; 
//			this.left = left; 
//			this.right = right; 
//			this.data = data 
//			} 
//
//			boolean isRoot() { 
//			return parent == null; 
//			} 
//			}
			
			
			/*
			 * 
TreeNode LowestCommonAncestorLoop(TreeNode currentNode, int Node1Value, int Node2Value)
{
    while (currentNode != null)
    {                
        // Move to the left subtree if the given values are less than current node's value.
        if (Node1Value < currentNode.NodeValue && Node2Value < currentNode.NodeValue)
        {
            currentNode = currentNode.LeftNode;
        }
        // Move to right subtree if the given values are greater than current node's value.
        else if (Node1Value > currentNode.NodeValue && Node2Value > currentNode.NodeValue)
        {
            currentNode = currentNode.RightNode;
        }
        else
        {
            // We have found the common ancestor.
            break;
        }
    }
    return currentNode;
}
*/	
	public int[] selfExcludingProduct(int[] a) {
		int[] result = new int[a.length];
		int prod = 1;
		int numberOfZeros = 0;
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == 0) {
				++numberOfZeros;
			}
			else {
				prod *= a[i];
			}
			
			if (numberOfZeros >= 2) {
				prod = 0;
				break;
			}
		}

		if (numberOfZeros == 1) {
			for (int i = 0; i < a.length; ++i) {
				if (a[i] == 0) {
					result[i] = prod;
				}
			}			
		}
		else if (numberOfZeros >= 2) {
			for (int i = 0; i < a.length; ++i) {
				result[i] = 0;
			}
		}
		else {
			for (int i = 0; i < a.length; ++i) {
				result[i] = prod/a[i];
			}
		}

		return result;
	}
	
	
	@Test
	public void testSelfExcludingProduct() {
		/**
		 * Implement a method which takes an integer array and returns an integer array (of equal size) in
		 * which
		 * 
		 * each element is the product of every number
		 * in the input array with the exception of the
		 * number at that index.
		 *
		 * Example:
		 *   [3, 1, 4, 2] => [8, 24, 6, 12]
		 */
		for (int i : selfExcludingProduct(new int[]{3,1,4,2}))
			System.out.print(i + " ");
		System.out.println();
		
		for (int i : selfExcludingProduct(new int[]{3,1,0,2}))
			System.out.print(i + " ");
		System.out.println();
		
		for (int i : selfExcludingProduct(new int[]{3,0,0,2}))
			System.out.print(i + " ");
		System.out.println();
	}
	
	public static int myPow(int a, int b) {
		
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		
		int tmp = myPow(a, b/2);
		
		if (b < 0) {
			return 1/myPow(a, -b);
		}
		
		if (b%2 == 1) {
			return a*tmp*tmp;
		}
		else {
			return tmp*tmp;
		}
	}
	
	@Test
	public void powInLogN() {
		myPow(2,5);
		// ahha! There's a solution with bit shifting as well, didn't realize that!
		
	}
	
	private static void generate(ArrayList<Integer> items, ArrayList<Integer> result, int start, ArrayList<ArrayList<Integer>> totalResult) {
		if (result.size() == items.size()) {
			System.out.print("adding " + result.toString());	
			totalResult.add(result);;  
		}
		
		for (int i = start; i < items.size(); ++i) {
			result.add(items.get(i));
			generate(items, result, i+1, totalResult);
			result.remove(result.size()-1);
		}
	}
	
	public ArrayList<ArrayList<Integer>> generate(ArrayList<Integer> items) {
		ArrayList<ArrayList<Integer>> totalResult = new ArrayList<ArrayList<Integer>>();
		generate(items, new ArrayList<Integer>(), 0, totalResult);
		return totalResult;
	}
	
	@Test
	public void permutationTest() {
//		public interface Permutations { 
//
//			/** 
//			* Generate all permutations of given sequence of elements. 
//			* Return a list of all distinct permutations. 
//			* 
//			* E.g. 
//			* generate([1, 2, 3]) -> [1, 2, 3], [1, 3, 2], [2, 3, 1], [2, 1, 3], [3, 1, 2], [3, 2, 1] 
//			*/ 
//			vector<vector<int>> generate(vector<int> items); 
//			}
//		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);a.add(2);a.add(3);
		ArrayList<ArrayList<Integer>> returned = generate(a);
		System.out.println("total result:");
		System.out.println(returned.toString());
	}
	
	public static String palindrome(String s) {
		final int n = s.length();
		boolean[][] lookup = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			lookup[i] = new boolean[n];
			lookup[i][i] = true;
		}
		
		int startIndex = 0;
		int maxLength = 1;
		
		// 2 chars case
		for (int i = 0; i < n-1; ++i) {
			if (s.charAt(i) == s.charAt(i+1)) {
				lookup[i][i+1] = true;
				startIndex = i;
				maxLength = 2;
			}
		}
		
		// > 3 chars
		for (int k = 3; k <= n; ++k) {
			for (int i = 0; i < n-k-1; ++i) {
				int j = i+k-1;
				if (lookup[i+1][j-1] == true && s.charAt(i) == s.charAt(j)) {
					lookup[i][j] = true;
					maxLength = k;
					startIndex = i;
				}
			}
		}
		return s.substring(startIndex, startIndex+maxLength);
	}
	
	public static int lis(int[] arr, int n) {
		if (n == 1) {
			return 1;
		}
		
		int result = 1;
		int maxSoFar = 1;
		for (int i = 1; i < n; ++i) {
			result = lis(arr, i);
			if (arr[i-1] < arr[n-1]) {
				++result;
			}
			maxSoFar = Math.max(result, maxSoFar);
		}
		return maxSoFar;
	}
	
	public static int lis(int[] arr) {
		int[] l = new int[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			l[i] = 1;
		}
		
		for (int i = 1; i < arr.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (arr[i] > arr[j] && l[i] < l[j]+1) {
					l[i] = l[j]+1;
				}
			}
		}
		
		Integer max = Integer.MIN_VALUE;
		for (int i = 0; i < l.length; ++i) {
			if (l[i] > max) {
				max = l[i];
			}
		}
		return max;
	}
	
	@Test
	public void lisTest() {
		int[] list = new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println("lis1 : " + lis(list, list.length));
		System.out.println("lis2 : " + lis(list));
	}
	
	@Test
	public void palindrome() {
		String s = "forgeeksskeegfor";
		System.out.println("palindrome : " + palindrome(s));
		final int n = s.length();
		boolean[][] lookup = new boolean[n][n];
		
		for (int i = 0; i < n; ++i) {
			lookup[i] = new boolean[n];
			for (int j = 0; j < n; ++j) {
				lookup[i][j] = false;
			}
			// a character itself is always a palindrome
			lookup[i][i] = true;
		}

		int start = 0;
		int maxLength = 0;
		// special case when length is 2
		for (int i = 1; i < n-1; ++i) {
			if (s.charAt(i) == s.charAt(i+1)) {
				lookup[i][i+1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// fix the starting index		
		for (int k = 3; k <= n; ++k) {
			for (int i = 0; i < n-k+1; ++i) {
				// j is at the end of the substring
				int j = i+k-1;
				if (lookup[i+1][j-1] &&	s.charAt(i) == s.charAt(j)) {
					lookup[i][j] = true;
					if (k > maxLength) {
						maxLength = k;
						start = i;
					}
				}
			}
		}
		System.out.println("palindrome @ " + start + " with " + maxLength + " chars " + s.substring(start, start+maxLength));
	}
	
	@Test
	public void kadaneTest() {
	//	System.out.println("------------KAdane----------------");
		int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
		int maxSoFar = arr[0];
		int currentMax = 0;
		for (int i = 1; i< arr.length; ++i) {
			currentMax = Math.max(currentMax, currentMax+arr[i]);
			maxSoFar = Math.max(currentMax, maxSoFar);
		}
//		System.out.println("------------KAdane-----end-------- " + maxSoFar);
	}
	
	// can be as lazy as calling isIsomorphic(s1,s2) && isIsomorphic(s2, s1)
	private boolean isIsomorphic(String s1, String s2) {
		boolean result = true;
		int[] map = new int[256];
		for (int i = 0; i < 256; ++i) {
			map[i] = -1;
		}
		for (int i = 0; i < s1.length(); ++i) {
			final int key = s1.charAt(i)-'a';
			final int value = s2.charAt(i)-'a';
			if (map[key] == -1)
				map[key] = value;
			else if (map[key] != value) {
				result = false;
				break;
			}
			
		}
		return result;
	}
	
	private static String encodeAsFirstSeenIndices(String s1) {
		int[] indices = new int[26];
		for (int i = 0; i < 26; ++i) {
			indices[i] = -1;
		}

		for (int i = 0; i < s1.length(); ++i) {
			final int key = s1.charAt(i)-'a';
			if (indices[key] == -1)
				indices[key] = i;
		}
		
		String encodedS1 = "";
		for (int i = 0; i < s1.length(); ++i) {
			final int key = s1.charAt(i)-'a';
			encodedS1 += indices[key];
		}
		return encodedS1;
	}
	
	private boolean isIsomorphicOptimized(String s1, String s2) {
		return encodeAsFirstSeenIndices(s1) == encodeAsFirstSeenIndices(s1);
	}
	
	@Test
	public void isomorphic() {
		/*Given two (dictionary) words as Strings, determine if they are isomorphic. Two words are called isomorphic 
if the letters in one word can be remapped to get the second word. Remapping a letter means replacing all 
occurrences of it with another letter while the ordering of the letters remains unchanged. No two letters 
may map to the same letter, but a letter may map to itself. 

Example: 
given "foo", "app"; returns true 
we can map 'f' -> 'a' and 'o' -> 'p' 
given "bar", "foo"; returns false 
we can't map both 'a' and 'r' to 'o' 

given "turtle", "tletur"; returns true 
we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' -'r' 

given "ab", "ca"; returns true 
we can map 'a' -> 'c', 'b'
		 * 
		 */
		
		//assertEquals(true, isIsomorphic("foo", "app"));
		assertEquals(false, isIsomorphicOptimized("bar", "foo"));
		//assertEquals(false, isIsomorphic("boo", "bar"));
		//assertEquals(true, isIsomorphic("turtle", "tletur"));
	}
	
	@Test
	public void minimumDistanceInTwoSortedArray() {
/*
 * You have two arrays of integers, where the integers do not repeat and the two arrays have no common integers. 
   Let x be any integer in the first array, y any integer in the second. Find min(Abs(x-y)).
   That is, find the smallest difference between any of the integers in the two arrays. 
   Assumptions: Assume both arrays are sorted in ascending order.
 */
		// case 1. their intervals doesn't overlap such as [1, 2] and [3, 4]
		//         simply return a1[max]-a2[min]
		//         something like
//		if (a1[0] < a2[0] && a1[a1.length-1] < a2[0]) {
//			return a2[0] - a1[a1.length-1];
//		}
//		
//		if (a2[0] < a1[0] && a2[a2.length-1] < a1[0]) {
//			return a1[0] - a2[a2.length-1];
//		}
		
		// case 2. their intervals indeed overlaps. such as [1, 2, 4, 5] [3, 7, 8]
		//
		// pick the intersection point
		// nvm
		// it's O(n) anyway
		
		int[] a1 = new int[]{1,2,4,5};
		int[] a2 = new int[]{3,7,8};
		int i = 0;
		int j = 0;
		int minSoFar = Integer.MAX_VALUE;
		while (i < a1.length && j < a2.length) {
			if (a1[i] <= a2[j]) {
				minSoFar = Math.min(minSoFar, a2[j]-a1[i]);
				++i;
			}
			else {
				minSoFar = Math.min(minSoFar, a1[i]-a2[j]);
				++j;
			}
		}
		System.out.println("minSoFar : " + minSoFar);
	}
	
	private static String reverse(char[] s,int start,int end) {
		if(start >= end)
			return String.valueOf(s);
		
		char temp = s[start];
		s[start] = s[end];
		s[end] = temp;
		return reverse(s,start+1,end-1);
	}
	
	@Test
	public void stringReversal() {
		String s = "abcde";
		char[] arr = s.toCharArray();
		System.out.println("reversed : " + reverse(arr, 0, arr.length-1));
	}
	
	private static Integer binarySearch(int[] arr, int left, int right, int n) {
		if (left > right) {
			return null;
		}
		
		final int midIdx = (left+right)/2;
		if (arr[midIdx] == n) {
			return midIdx;
		}
		
		if (arr[midIdx] > n) {
			return binarySearch(arr, left, midIdx-1, n);
		}
		else {
			return binarySearch(arr, midIdx+1, right, n);
		}
	}
	
	private static int findLeft(int[] arr, int left, int right, int n) {
		final int midIdx = (left+right)/2;
		
		if (left > right || arr[midIdx] < n) {
			return midIdx+1;
		}
		
		if (arr[midIdx] == n) {
			return findLeft(arr, left, midIdx-1, n);
		}
		
		return left;
	}

	private static int findRight(int[] arr, int left, int right, int n) {
		final int midIdx = (left+right)/2;
		
		if (left > right || arr[midIdx] > n) {
			return midIdx-1;
		}
		
		if (arr[midIdx] == n) {
			return findRight(arr, midIdx, right, n);
		}
		return right;
	}
	
	private static void computeRange(int[] arr, int n) {
		Integer index = binarySearch(arr, 0, arr.length, n);
		if (index != null) {
			Integer left = findLeft(arr, 0, index, n);
			Integer right = findRight(arr, index, arr.length-1, n);
			System.out.println("range for " + n + " is {" + left + ", " + right + "}");
		}
		else {
			System.out.println("not found");
		}
	}
	
	@Test
	public void testComputeRange() {
		computeRange(new int[]{0,0,2,3,3,3,3,4,7,7,9}, 3);
		/*
		 * Given a sorted integer array and a number, find the start and end indexes of the number in the array. 

Ex1: Array = {0,0,2,3,3,3,3,4,7,7,9} and Number = 3 --> Output = {3,6} 
Ex2: Array = {0,0,2,3,3,3,3,4,7,7,9} and Number = 5 --> Output = {-1,-1} 

Complexity should be less than O(n)

- bvinay84 on July 11, 2013 in United States Report Dup
		 */
		
		// 1. binary search for the number. if not found return the -1 array
		// 2. if found, recursively,
		//           findLow -> will search for  left...pivot-1
		//           findHigh -> will search for pivot+1...right
		// worst case logn (either not found or the array is full of the given number)
	}
	
	@Test
	public void testLongestIncreasingSubsequenceSum() {
		// variation of LIS
		int[] list = new int[]{ 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = list.length;
		int[] liss = new int[n];
		for (int i = 0; i < n; ++i) {
			liss[i] = list[i];
		}
		
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (list[i] > list[j] && liss[i] < liss[j] + list[i]) {
					liss[i] = liss[j] + list[i];
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i: liss) {
			if (i > max) {
				max = i;
			}
		}
		System.out.println("longest increasing sum " + max);
	}
	// moore's algorithm to find the majority (more than half of the time)
	@Test
	public void MooresAlgorithm() {
		int[] arr = {2,4,5,3,6,2,7,4,7,6,3,3,3,5,43,2,3,4,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,3,3,3,2,22,2,2,2,2,2,2,2};
		
		int candidate = 0;
		int count = 0;
		for (int i:arr) {
			if (count == 0) {
				candidate = i;
				++count;
			}
			if (i != candidate) {
				--count;
			}
			if (i == candidate) {
				++count;
			}
		}
		
		System.out.println("=================>candidate : " + candidate);
	}
	
	@Test
	public void binarySearchInRotatedArray() {
		// logic
		// do regular binary search left, right
		// if mid is the match, return
		// otherwise compare a[mid] to a[left]
		// .......
		// http://www.careercup.com/question?id=15489754
		// get the following done when get home.
	/*
	 * 	public static int findElementInRotatedSorted(int[] a, int start, int end, int key)
	{
		if (end < start)
		{
			return -1;
		}

		int middle = (start + end) / 2;
		if (a[middle] == key)
		{
			return middle;
		}

		if (a[start] <= a[middle])
		{
			if (key < a[middle] && key >= a[start])
			{
				return findElementInRotatedSorted(a, start, middle - 1, key);
			}
			else
			{
				return findElementInRotatedSorted(a, middle + 1, end, key);
			}
		}
		else
		{
			if (a[middle] < key && key <= a[end])
			{
				return findElementInRotatedSorted(a, middle + 1, end, key);
			}
			else
			{
				return findElementInRotatedSorted(a, start, middle - 1, key);
			}
		}
	}
}
	 */
	}
	
	public static class Node {
		public Node left;
		public Node right;
		public int data;
		public Node(int data) {
			this.data = data;
		}
	}
	
	private static Node sortedArrayToBST(int[] arr, int left, int right) {
		if (left > right) {
			return null;
		}
		
		final int mid = (left+right)/2;
		Node node = new Node(arr[mid]);
		node.left = sortedArrayToBST(arr, left, mid-1);
		node.right = sortedArrayToBST(arr, mid+1, right);
		return node;
	}
	
	@Test
	public void sortedArrayToBST() {
		int[] arr = {1,2,3,4,5};
		Node root = sortedArrayToBST(arr, 0, arr.length-1);
		// yeah., so what?
		
	}
	
	@Test
	public void findCommonSet() {
		/*
		 * There are 2 sorted sets.Find the common elements of those sets 
			e.g. 
			A={1,2,3,4,5,6} 
			B={5,6,7,8,9} 
			o/p C={5,6} 

			Complexity should ne 0(n+m) where n and m is the size of the first and second set respectively 

			Which data structure should be used to store the output
		 */
		// http://www.careercup.com/question?id=15532665
		
		
	}
	
	private static boolean isOperator(char c) {
		if (c == '+' || c == '/' || c == '-' || c == '*')
			return true;

		return false;
	}
	
	private static Integer compute(Integer operand1, Integer operand2, char operator) throws Exception {
		switch(operator) {
		case '+':
			return operand1+operand2;
		case '-':
			return operand1-operand2;
		case '/':
			return operand1/operand2;
		case '*':
			return operand1*operand2;
		default:
			throw new RuntimeException("Illegal operation");
		}
	}

	private static int evaluate(String s) throws Exception {
		Stack<Integer> stack = new Stack<Integer>();
		char[] arr = s.toCharArray();
		for (int i = 0; i < arr.length; ++i) {
			final char c = arr[i];
			if (isOperator(c)) {
				Integer operand1 = stack.pop();
				Integer operand2 = stack.pop();
				//System.out.println("computing " + operand1 + c + operand2);
				stack.push(compute(operand1, operand2, c));
			}
			else {
				stack.push(c-'0');
			}
		}
		
		return stack.pop();
	}
	
	// Question : what about prefix?
	// Question : what about in order? >>>>>>>>>>> nonsense
	@Test
	public void evaluatePostFix() throws Exception {
		String s = "345+*612+/-";
		assertEquals(-27, evaluate(s));
		assertEquals(7, evaluate("25+"));
		assertEquals(56, evaluate("25+8*"));
		assertEquals(2, evaluate("25+8*112/"));
	}
	
	private static int findLogBase2Of(int n) {
		int result = 0;
		while (true) {
			n = n>>1;
			if (n <= 0)
				break;
			++result;
		}
		return result;
	}

	@Test
	public void findLogBase2() {
		assertEquals(0, findLogBase2Of(1));
		assertEquals(1, findLogBase2Of(2));
		assertEquals(1, findLogBase2Of(3));
		assertEquals(2, findLogBase2Of(4));
	}
	
	@Test
	public void stringRepresentedBST() {
		
	}
	
	@Test
	public void foo() {
		/*Given a large document and a short pattern consisting of a few words (eg. W1 W2 W3),
		 * find the shortest string that has all the words in any order
		 * (for eg. W2 foo bar dog W1 cat W3 -- is a valid pattern)
		 * 
		 */
		
		/*
		 * Find and store all the valid numbers in an array that are in the string including negative,
		 * positive, hexadecimal, octal, binary?
		 * For example string "abcd 0xa 11.12 123" has values 10, 11.12 , 123.
		 * I would rephrase the question:
		 * find all words(separated from other words through tabs or spaces) in the string that can be expressed
		 * in the form of decimal number. After the words are obtained store their value in the array.
		 * 
		 */
		
		/* String sentence justification.. another DP */
		// min(current # of line breaks, min(case when I select the next, case when I don't select the next)) not sure
		// need to study!!
		// Wordwrap @ https://www.readability.com/articles/y88eqwv3
		// Can be done using Greedy but not optimal (only sub-optimal as Greedy suggest)
	}
	
	private static int makeTriangle(int[] arr) {
		final int n = arr.length;
		int count = 0;
		for (int i = 0; i < n-2; ++i) {
			int k = i+2;
			for (int j = i+1; j < n; ++j) {
				while (k<n && arr[i] + arr[j] > arr[k]) {
					++k;
				}
				count += k-j-1;
			}
		}
		return count;
	}
	
	@Test
	public void testTriangle() {
		int[] arr = new int[] {10, 21, 22, 100, 101, 200, 300};
		System.out.println("triangles: " + makeTriangle(arr));
		
		int[] arr2 = new int[]{1, 2, -4, 1, 3, -2, 3, -1};
		
		int maxSoFar = arr2[0];
		int max = arr2[0];
		for (int i = 1; i < arr2.length;++i) {
			maxSoFar = Math.max(0, maxSoFar+arr2[i]);
			max = Math.max(maxSoFar, max);
		}
		System.out.println("max: " + max);
	}
	
	@Test
	public void lisTest123() {
		//int[] arr = {}
		System.out.println("subString!!!!!!!");
		String s = "ABABC";
		int subSequenceLength = 2;
		for (int i = 0; i < s.length()-subSequenceLength+1; ++i) {
			int j = i + subSequenceLength;
			System.out.println("subString " + i + " , " + j + " : " + s.substring(i,j));

		}
	}
}
