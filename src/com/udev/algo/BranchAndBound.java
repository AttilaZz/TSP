/**
* BranchAndBound.java
*
* the class that implement the brute force algorithm.
*
* @package com.udev.algo
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/

package com.udev.algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.udev.struct.City;
import com.udev.struct.Country;

public class BranchAndBound {
	
	private City startingCity;
	private int[] path;
	private double cost;
	double optimumCost = Integer.MAX_VALUE;
	// the array containing the optimum route
	private ArrayList<City> optimumRoute= new ArrayList();
	
	/**
	* constructor, generating a random starting city.
	*
	* @param Country c
	 * @return 
	* @expectedException None.
	* @return None.
	*/
	public BranchAndBound(Country c) {
		Random rand = new Random();
		int randCity = rand.nextInt((c.citiesCount - 1) - 0 + 1) + 0;
		System.out.println("rand ville = " + randCity);
		startingCity = c.getCitiesList()[randCity];
		System.out.println("X ville de depart = "+startingCity.getPositionX()+" Y ville de depart = "+startingCity.getPositionY() );

	}
	
	/**
	* the function that calls the branch and bound algorithm.
	*
	* @param Country c
	* @expectedException None.
	* @return void.
	*/
	public void branchBound(Country c) {
		
		//initialize the source city as the starting city
		City sourceCity=startingCity;
		
		//initialize the route with and add the starting city in the beginning
		ArrayList<City> initialRoute=new ArrayList();
		initialRoute.add(sourceCity);
	
		double routeCost=0;
		
		//call the function that implements the algorithm
		search_with_branchBound(sourceCity, initialRoute,c,routeCost);
		
		System.out.println(getOptimumRoute().toString());
		
		int path[]=new int[c.citiesCount+1];
		int i=0;
		for (Iterator iterator = getOptimumRoute().iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			path[i]=findPosition(c, city);
			i++;		
		}	
		
		setPath(path);
		setCost(optimumCost);
	}
	
	public void searchWithBranchBound(City from, ArrayList<City> followedRoute,Country country,double routeCost){
				
		if (followedRoute.size()==country.citiesCount) {
			
			followedRoute.add(startingCity);
			routeCost+=distance(from, startingCity);
			 
			if (routeCost < optimumCost) {
                optimumCost = routeCost;
                setOptimumRoute((ArrayList<City>)followedRoute.clone());
                
                
            }
		}
		else{			
				for (City to : country.getCitiesList()) {
					
					if (!followedRoute.contains(to)) {
						
						routeCost+=distance(from, to);
						
						if (routeCost<optimumCost) {
							ArrayList<City> increasedRoute = (ArrayList<City>)followedRoute.clone();
	                        increasedRoute.add(to);
	                       
	                        search_with_branchBound(to, increasedRoute,country,routeCost);
						}
						routeCost-=distance(from, to);
					}
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
		
		for (int i = 0; i < getPath().length; i++) {
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
	
	private ArrayList<City> getOptimumRoute() {
		return optimumRoute;
	}

	private void setOptimumRoute(ArrayList<City> optimumRoute) {
		this.optimumRoute = optimumRoute;
	}

	public int[] getPath() {
		return path;
	}

	public void setPath(int[] path) {
		this.path = path;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
