package geeksforgeek;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("KARMA JUMP STARTED");
		boolean[] notPassable = new boolean[]{true, true, false, true, false, true, false, false, false, true};
		assertEquals(false, karmaJump(notPassable, 0, 1));
		for (int i = 0; i < 500; ++i) {
			naiveHashmap[i] = -1;
		}		
		System.out.println("KARMA JUMP2 STARTED");
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
	
	/* In "the 100 game," two players take turns adding, to a running 
	total, any integer from 1..10. The player who first causes the running 
	total to reach or exceed 100 wins.
	What if we change the game so that players cannot re-use integers? 
	For example, if two players might take turns drawing from a common pool of numbers
	of 1..15 without replacement until they reach a total >= 100. This problem is 
	to write a program that determines which player would win with ideal play.

	Write a procedure, "Boolean canIWin(int maxChoosableInteger, int desiredTotal)",
	which returns true if the first player to move can force a win with optimal play.

	Your priority should be programmer efficiency; don't focus on minimizing
	either space or time complexity.
	*/

	private Boolean yourPlay() {
		return true;
	}
	
	private Boolean canIWin(boolean[] choices, int remainingTotal) {
		if (remainingTotal <= 0) {
			return false;
		}

		boolean result = false;

		for (int i = 0; i < choices.length; ++i) {
			if (choices[i]) {
				choices[i] = false;
				
				int currentTotal = remainingTotal-i-1;
				if (currentTotal == 0) {
					return true;
				}
				
				// the other player
				boolean cur = true;
				for (int j = 0; j < choices.length; ++j) {
					if (choices[i]) {
						choices[i] = false;
						cur = canIWin(choices, remainingTotal - j - i);
						choices[i] = true;
					}
				}
				result = result || cur;
				choices[i] = true;
			}
		}
		return result;
	}
	
	public Boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		// 	Implementation here. Write yours
		
		boolean[] choices = new boolean[maxChoosableInteger];
		for (int i = 0; i < maxChoosableInteger; ++i) {
			choices[i] = true;
		}
		boolean myTurn = true;
		return canIWin(choices, desiredTotal);
	}
	
	@Test
	public void canIWin() {
		System.out.println("can I win? " + canIWin(6, 12));
		System.out.println("false||true : " + (false||true));
	}
	
	@Test
	// http://www.careercup.com/question?id=6266917077647360
	public void convertBinaryTree() {
		BSTNode root = new BSTNode(1);
		root.left = new BSTNode(2);
		root.right = new BSTNode(3);
		root.left.left = new BSTNode(4);
		root.left.right = new BSTNode(5);
		root.left.left.left = new BSTNode(6);
		root.left.left.right = new BSTNode(7);
		
		BSTNode node = root;
		Stack<BSTNode> stack = new Stack<BSTNode>();
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
		
		BSTNode prev = null;
		while (!stack.isEmpty()) {
			node = stack.pop();
			// this is the root
			if (node.left == null && node.right == null) {
				System.out.println("top : " + node.data);
				root = node;
			}
			
			if (node.right != null) {
				prev.left = node.right;
				prev.right = node;
				node.right = null;
			}
			prev = node;
		}
		node.left = null;
		node.right = null;
		System.out.println("flipped!!!!!! " + root.data);
		Helper.levelOrder(root);
		System.out.println("\nflipped!!!!!! done" + root.data);
	}
}
