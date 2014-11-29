/**
* Country.java
*
* the class that defines a country.
*
* @package com.udev.struct.
* @author Sebaa Amar <amarsebaa@gmail.com>.
* @license None.
* @link Project Link.
*/

package com.udev.struct;

public class Country {
	
	private int citiesCount = 4;
	private City citiesList[] = new City[citiesCount];
	
	//Constructor
	public Country() {
		for (int i = 0; i < getCitiesList().length; i++) {
			getCitiesList()[i] = new City();
		}
	}
	
	private City[] getCitiesList() {
		return citiesList;
	}

	private void setCitiesList(City citiesList[]) {
		this.citiesList = citiesList;
	}
	
	public void showCities(){
		for (int i = 0; i < getCitiesList().length; i++) {
			System.out.println("i = "+i+" X : "+getCitiesList()[i].getPositionX()+"  Y : "+getCitiesList()[i].getPositionY());
		}
	}
}
