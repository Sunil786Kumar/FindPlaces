package com.sunilkumar.findplaces;

public class ModalData {

	/*
	 * Value for default PlacesActivity(Intent)
	 */
	public static int DEFAULT_VALUE=0;


	public static String SPINNER_HEADING_PLACES_RESULT="Places";
	public static String SPINNER_MESSAGE_PLACES_RESULT="searching...";
	
	public static String SPINNER_HEADING_PLACES_DETAIL="Places Detail";
	public static String SPINNER_MESSAGE_PLACES_DETAIL="loading...";
	
	public static String SPINNER_HEADING_SEARCH="Places";
	public static String SPINNER_MESSAGE_SEARCH="searching...";
	
	public static final String CALLING_CLASS_SEARCH="SEARCH";
	public static final String CALLING_CLASS_PLACE="PLACE";
	
	/*
	 * Modal data for places 
	 */
	public static String[] placesTabData= {"ATM","Bank","Bar","Cafe",
		"Food","Gas Station","Laundry","Locksmith",
		"Parking","Pharmacy","Shopping Mall","Train Station",
		"Health","Homeware","Hospital","Library",
		"Liquor Store","Takeway","Police","Plumber",
		"Real Estate","Store","Travel"};

	/*
	 * Will return selected element based on index of the array
	 */
	public static String getSelectedPlaceElement(int rowIndex){
		return placesTabData[rowIndex];
	}

	public static String getCorrectPlaceElement(String selectedPlaceElement){
		if(selectedPlaceElement=="ATM")
			return "atm";
		if(selectedPlaceElement=="Bank")
			return "bank";
		if(selectedPlaceElement=="Bar")
			return "bar";
		if(selectedPlaceElement=="Cafe")
			return "cafe";

		if(selectedPlaceElement=="Food")
			return "food";
		if(selectedPlaceElement=="Gas Station")
			return "gas_station";
		if(selectedPlaceElement=="Laundry")
			return "laundry";
		if(selectedPlaceElement=="Locksmith")
			return "locksmith";

		if(selectedPlaceElement=="Parking")
			return "parking";
		if(selectedPlaceElement=="Pharmacy")
			return "pharmacy";
		if(selectedPlaceElement=="Shopping Mall")
			return "shopping_mall";
		if(selectedPlaceElement=="Train Station")
			return "train_station";

		if(selectedPlaceElement=="Health")
			return "health";
		if(selectedPlaceElement=="Homewares")
			return "home_goods_store";
		if(selectedPlaceElement=="Hospital")
			return "hospital";
		if(selectedPlaceElement=="Library")
			return "library";

		if(selectedPlaceElement=="Liquor Store")
			return "liquor_store";
		if(selectedPlaceElement=="Takeaway")
			return "meal_takeaway";
		if(selectedPlaceElement=="Police")
			return "police";
		if(selectedPlaceElement=="Plumber")
			return "plumber";

		if(selectedPlaceElement=="Real Estate")
			return "real_estate_agency";
		if(selectedPlaceElement=="Store")
			return "store";
		if(selectedPlaceElement=="Travel")
			return "travel_agency";
		else
			return null;
	}


}
