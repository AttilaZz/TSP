/**
* Country.java
*
* the class that defines a country.
*
* @package com.udev.struct
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/

package com.udev.struct;

public class Country {
	
	//the number of generated cities 
	public int citiesCount = 4;
	//the list of all the generated cities
	private City citiesList[] = new City[citiesCount];
	
	//Constructor
	public Country() {
		for (int i = 0; i < getCitiesList().length; i++) {
			getCitiesList()[i] = new City();
		}
	}
	
	public City[] getCitiesList() {
		return citiesList;
	}

	public void setCitiesList(City citiesList[]) {
		this.citiesList = citiesList;
	}
	
	public void showCities(){
		for (int i = 0; i < getCitiesList().length; i++) {
			System.out.println("i = "+i+" X : "+getCitiesList()[i].getPositionX()+"  Y : "+getCitiesList()[i].getPositionY());
		}
	}
}
