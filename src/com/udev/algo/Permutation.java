/**
* Permutation.java
*
* the class that simulating the nextPermutation in c/c++.
*
* @package com.udev.algo
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/
package com.udev.algo;

import java.util.Arrays;

public class Permutation {
        
	/**
	* the function that simply prints all permutation - to see how it works.
	*
	* @param int[] c, the input array
	* @expectedException None.
	* @return void.
	*/
    public static void printPermutations( int[] c ) {
            
            // Note the array must be sorted in an increasing manner so that all permutations will be included
            Arrays.sort(c);
            
            System.out.println( Arrays.toString( c ) );
            while ( ( c = nextPermutation( c ) ) != null ) {
                    System.out.println( Arrays.toString( c ) );
            }
            
    }
    
    
	/**
	* the function that modifies c to next permutation or returns null if such permutation does not exist.
	*
	* @param final int[] c, the input array
	* @expectedException None.
	* @return int[], the output array corresponding to the next permutation
	*/   
    public static int[] nextPermutation( final int[] c ) {
            // 1. finds the largest k, that c[k] < c[k+1]
            int first = getFirst( c );
            if ( first == -1 ) return null; // no greater permutation
            // 2. find last index toSwap, that c[k] < c[toSwap]
            int toSwap = c.length - 1;
            while ( c[ first ] >= c[ toSwap ] )
                    --toSwap;
            // 3. swap elements with indexes first and last
            swap( c, first++, toSwap );
            // 4. reverse sequence from k+1 to n (inclusive) 
            toSwap = c.length - 1;
            while ( first < toSwap )
                    swap( c, first++, toSwap-- );
            return c;
    }

    /**
	* the function that finds the largest k, that c[k] < c[k+1].
	* if no such k exists (there is not greater permutation), return -1
	*
	* @param final int[] c, the input array
	* @expectedException None.
	* @return int
	*/ 
    private static int getFirst( final int[] c ) {
            for ( int i = c.length - 2; i >= 1; --i )
                    if (  c[ i ]  < c[ i + 1 ] )
                            return i;
            return -1;
    }

    // swaps two elements (with indexes i and j) in array 
    /**
   	* the function that swaps two elements (with indexes i and j) in array.
   	*
   	* @param final int[] c, final int i, final int j
   	* @expectedException None.
   	* @return void
   	*/
    private static void swap( final int[] c, final int i, final int j ) {
            final int tmp = c[ i ];
            c[ i ] = c[ j ];
            c[ j ] = tmp;
    }
    
        
        
}