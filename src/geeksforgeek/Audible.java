package geeksforgeek;

import static org.junit.Assert.*;

import org.junit.Test;

public class Audible {

	@Test
	public void greedyTest() {
	    int startTime[] =  {1, 3, 0, 5, 8, 5};
	    int finishTime[] =  {2, 4, 6, 7, 9, 9};
	    
	    // sort it
	    int i = 0;
	    System.out.print(i+ " ");
	    for (int j = 1; j < startTime.length; ++j) {
	    	if (startTime[j] >= finishTime[i]) {
	    		System.out.print(j + " ");
	    		i = j;
	    	}
	    }
	    System.out.println();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
