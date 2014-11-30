/**
* BruteForce.java
*
* the class that implement the brute force algorithm.
*
* @package com.udev.algo
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/
package com.udev.algo;

import java.util.Random;

import com.udev.struct.City;
import com.udev.struct.Country;

public class BruteForce {
	
	private City startingCity;
	private int[] path;
	private double cost;
	
	/**
	* constructor, generating a random starting city.
	*
	* @param Country c
	* @expectedException None.
	* @return None.
	*/	
	public BruteForce(Country c) {
		Random rand = new Random();
		int randCity = rand.nextInt((c.citiesCount - 1) - 0 + 1) + 0;
		System.out.println("rand ville = " + randCity);
		startingCity = c.getCitiesList()[randCity];
		System.out.println("X ville de depart = "+startingCity.getPositionX()+" Y ville de depart = "+startingCity.getPositionY() );
	}

	/**
	* the main function that implements the brute force algorithm.
	*
	* @param Country c
	* @expectedException None.
	* @return void.
	*/
	public void bruteForce(Country c){
		
		//the number of existing cities
		int nNodes=c.citiesCount; 
		
		////Temporary cost
		double tmpCost; 
		
		//the minimum cost
		double cheapestCost=0; 
		
		//array of all existing cities
		City cities[] = c.getCitiesList();
		
		//temporary path
		City [] tmpPath = new City[nNodes];    
		//best path
		City [] cheapPath = new City[nNodes]; 
		
		//build the initial path containing the starting city in index 0
		int pisitionStartingCity=findPosition(c, startingCity);
		City temp=cities[0];
		cities[0]=cities[pisitionStartingCity];
		cities[pisitionStartingCity]=temp;
			
		//get a mirror of cities[] defined by their indexes
		int[] pathMirror= new int[nNodes];
		for (int i = 0; i <nNodes; i++) {
			
			pathMirror[i]=i;
		}

		
		//do ... while there is no other possible permutation of nNodes-1 excluding the starting city
		do{
			
			for (int i = 0; i <nNodes; i++) {
				
				System.out.print(pathMirror[i]+"-"+cities[pathMirror[i]].getPositionX());
				System.out.print(" | ");
			}
			
			System.out.println("  ");
			
			//calculating the cost of the temporary path
			tmpCost=0;			
			for (int i = 0; i < nNodes; i++) {
				tmpPath[i]=cities[pathMirror[i]];
				if(i!=0){
					tmpCost +=distance(cities[pathMirror[i]], cities[pathMirror[i-1]]);
				}
			}
			
			//add the distance between the final city and the starting city to form a tour
			tmpCost +=distance(cities[pathMirror[nNodes-1]], cities[0]); 
			
			System.out.print("temporary cost : ");System.out.print(tmpCost);
			System.out.println(" ");
			
			//see if the calculated cost is optimal or not, if yes update the cheapest path
			if(cheapestCost > tmpCost || cheapestCost==0.0){
				cheapestCost=tmpCost;
				
				for (int i = 0; i < nNodes; i++) {
					cheapPath[i]=tmpPath[i];

				}
			}
			
			System.out.print("current minimun: ");System.out.print(cheapestCost);
			System.out.println(" ");
			
		}while((pathMirror = (int[])Permutation.nextPermutation(pathMirror)) != null);
		
		//build the path according to position of cities, witch will be displayed
		int[] realPath=new int[nNodes+2];
        for (int i = 0; i <nNodes; i++) {		
        	realPath[i]=findPosition(c, cheapPath[i]);
        	if(realPath[i]==0) realPath[i]=pisitionStartingCity;
        	else if(realPath[i]==pisitionStartingCity) realPath[i]=0;
		}
		//add the starting city in the end to build a tour
		realPath[nNodes]=pisitionStartingCity;
		
		//set the cheapest path found by the brute force algorithm
		setPath(realPath);
		//set the corresponding cheapest cost
		setCost(cheapestCost);
		
		
		System.out.println(" ");
		System.out.print(" cheapest cost : ");
		System.out.println(cheapestCost);
		System.out.print(" cheapest path : ");
		for (int i = 0; i <nNodes; i++) {
			
			System.out.print("-"+cheapPath[i].getPositionX());
			
			
		}
		System.out.println();
		//show cities
		
		//come back to the previous form of cities table ( before moving the random starting city to the index 0)
		City z=cities[pisitionStartingCity];
		cities[pisitionStartingCity]=cities[0];
		cities[0]=z;
				
	}
	
	/**
	* the function that finds position from cities array with given city.
	*
	* @param Country c, City city
	* @expectedException None.
	* @return int the position of city
	*/	
	public int findPosition(Country c,City city){
		
		boolean output = true;		
		int position = 0;
		
		while(output && position<c.getCitiesList().length){
			if(city.getPositionX() == c.getCitiesList()[position].getPositionX() && city.getPositionY() == c.getCitiesList()[position].getPositionY()){
				output = false;
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
		for (int i = 0; i < getPath().length-1; i++) {
			System.out.println(getPath()[i]);
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
		System.out.println("Cost = "+ getCost());
	}
	
	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	private double getCost() {
		return cost;
	}

	private void setCost(double cost) {
		this.cost = cost;
	}	
	
}
