package com.sunilkumar.findplaces;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONValueRetriever {
	public static String getStringValueFromJsonArray(JSONArray jsonArray,String jsonString,int index){
		String oneObjectsItem=null;
		try{
		JSONObject oneObject = jsonArray.getJSONObject(index);
	//	Log.i(MainActivity.FIND_PLACES,oneObject+"");
		oneObjectsItem = oneObject.getString(jsonString);
		}catch(Exception  e){
			e.printStackTrace();
		}
		return oneObjectsItem;
	}
}
