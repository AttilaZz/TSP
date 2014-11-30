/**
* App.java
*
* the main class.
*
* @package com.udev.
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/
package com.udev;

import com.udev.algo.BruteForce;
import com.udev.struct.Country;

public class App {
	public static void main(String[] args) {
		
		Country c = new Country();
		BruteForce bf = new BruteForce(c);
		bf.bruteForce(c);
		bf.displayCost();
		bf.displayPath();
	}
}
