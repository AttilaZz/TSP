/**
* City.java
*
* the class that defines a city.
*
* @package com.udev.struct.
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/

package com.udev.struct;

import java.util.Random;

public class City {
	//the position of the city in the abscissa of the panel
	private int positionX;
	//the position of the city in the ordinate of the panel
	private int positionY;
	private boolean visited;
	final int panelX = 500;
	final int panelY = 1000;
	
	//Constructor
	public City() {
		
		Random rand = new Random();
		positionX = rand.nextInt(panelX - 10 + 1) + 10;
		positionY = rand.nextInt(panelY - 10 + 1) + 10;
		visited = false;
	}

	public int getPositionX() {
		return positionX;
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
}
