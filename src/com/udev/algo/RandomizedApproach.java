/**
 * RandomizedApproach.java
 *
 * the class that implements the randomized approach algorithm.
 *
 * @package com.udev.
 * @author BOUACHERIA Bachir <bouacheria.bachir@hotmail.fr>.
 * @license None.
 * @link Project Link.
 */
package com.udev.algo;

import java.util.Random;
import com.udev.struct.City;
import com.udev.struct.Country;

public class RandomizedApproach {

	//The starting city of salesman
	private City startingCity;

	//The list of cities of salesman's country 
	private Country countrySalesman = new Country();

	//The cost of salesman 
	private double cost = 0;

	//The path of salesman
	private int[] path;

	//The best cost of salesman initialized to max value
	private double bestCost = Integer.MAX_VALUE;

	//The best path of salesman 
	private int[] bestPath;

	//The number of random iterations that the algorithm gone to do 
	final int NUMBER_OF_TOUR = 99999;
	
	/**
	 * The constructor. 
	 *
	 * @param Country country.
	 * @expectedException None.
	 * @return none.
	 */	
	public RandomizedApproach(Country country) {
		
		countrySalesman.setCitiesList(country.getCitiesList());
	}
		
	/**
	 * the function that implements the randomized approach algorithm. 
	 *
	 * @param Country country.
	 * @expectedException None.
	 * @return none.
	 */	
	public void randomizedApproach(Country country) {
		
		Random rand = new Random();
		int randCity = rand.nextInt((country.citiesCount - 1) - 0 + 1) + 0;
		startingCity = countrySalesman.getCitiesList()[randCity];


		setBestPath(new int[country.citiesCount+1]);
		path = new int[country.citiesCount+1];
		int startingCitybis;
		int randomNumber;

		//initialization of path of array whit starting city  
		path[0] = findPosition(countrySalesman,startingCity);

		//mark the starting city as visited 
		countrySalesman.getCitiesList()[path[0]].setVisited(true);

		//find position of next starting city
		startingCitybis = findPosition(countrySalesman,startingCity);

		//initialize the best cost to max value 
		bestCost = Integer.MAX_VALUE;

		for (int j = 0; j < NUMBER_OF_TOUR; j++) {

			//initialize the cost to 0
			cost = 0;

			//Set all the cities as unvisited 
			for (int h = 0; h < country.getCitiesList().length; h++) {
				if(startingCitybis != h) countrySalesman.getCitiesList()[h].setVisited(false);
			}

			//loop to cover all cities in country
			for (int i = 1; i < country.getCitiesList().length; i++) {

				//find position of an unvisited city  
				do{
					randomNumber = rand.nextInt(country.citiesCount-1 - 0 + 1) + 0;  
				}while(countrySalesman.getCitiesList()[randomNumber].isVisited() != false);

				//mark city as visited 
				countrySalesman.getCitiesList()[randomNumber].setVisited(true);

				//update a value of cost
				cost = cost + distance(startingCity, countrySalesman.getCitiesList()[randomNumber]);

				//starting city become a new found city 
				startingCity = countrySalesman.getCitiesList()[randomNumber];

				//update the array path
				path[i] = findPosition(countrySalesman, startingCity);

			}


			path[path.length - 1] = path[0];

			//update the array path to come back in the first city
			cost = cost + distance(startingCity, countrySalesman.getCitiesList()[path[0]]);

			//update the best cost if new cost is lower than previous cost 
			if(cost < bestCost){
				bestCost = cost;
				setBestPath(path);
			}
		}
	}

	/**
	 * the function that finds position from cities array with given city.
	 *
	 * @param Country c, City city
	 * @expectedException None.
	 * @return int the position of city
	 */	
	public int findPosition(Country c,City city){

		boolean out = true;		
		int position = 0;

		while(out && position<c.getCitiesList().length){
			if(city.getPositionX() == c.getCitiesList()[position].getPositionX() && city.getPositionY() == c.getCitiesList()[position].getPositionY()){
				out = false;
			}
			else{
				position++;
			}	
		}

		return position;
	}	



	/**
	 * the main function gives the distance between two cities.
	 *
	 * @param City c1, City c2
	 * @expectedException None.
	 * @return double, the distance between c1 and c2
	 */
	public double distance(City c1, City c2){

		return (Math.sqrt(Math.pow((c1.getPositionX()-c2.getPositionX()),2)+Math.pow((c1.getPositionY()-c2.getPositionY()),2)));

	}



	/**
	 * the function that displays the optimum path found by the algorithm.
	 *
	 * @param None.
	 * @expectedException None.
	 * @return None.
	 */
	public void displayPath(){
		for (int i = 0; i < path.length; i++) {
			System.out.println(path[i]);
		}	
	}



	/**
	 * the function that displays the optimum cost found by the algorithm.
	 *
	 * @param None.
	 * @expectedException None.
	 * @return None.
	 */
	public void displayCost(){
		System.out.println("Cost = "+ cost);
	}

	public int[] getBestPath() {
		return bestPath;
	}

	public void setBestPath(int[] bestPath) {
		this.bestPath = bestPath;
	}
}
