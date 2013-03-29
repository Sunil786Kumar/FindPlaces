package com.sunilkumar.findplaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

public class AppBackend {

	public static JSONArray webServiceResponse=null;
	public static JSONObject placesDetailJSONObject=null;
	public static JSONArray searchDetailResponse=null;


	private static final String Activity_DISPLAY = "PlacesResultDisplayActivity";
	private static final String Activity_DETAIL ="PlacesResultDetailActivity";
	private static final String Activity_SEARCH="SearchMainActivity";

	private static final String PlacesResultActivity_DISPLAY_TAG = "results";
	private static final String PlacesResultActivity_DETAIL_TAG ="result";
	private static final String SearchResultActivity_SEARCH_TAG ="ResultSet";

	/*
	 * Google API key
	 */
	public static final String GOOGLE_API_KEY="AIzaSyAgF8qluUg8fw8KdVTqNWrBVWSMd7kjJMk";
	public static final String YAHOO_API_KEY="1cd20438c7";

	public static void callWebServiceWithURL(String url,Context context){

		Log.i(MainActivity.FIND_PLACES,"Context is :"+context.getClass().getSimpleName());
		/*
		 * Empty the array first
		 */
		if(webServiceResponse!=null){
			webServiceResponse = null;
		}

		String resultString = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);

		HttpResponse response;
		try{
			response = httpclient.execute(httpget);
			HttpEntity entity= response.getEntity();

			if(entity!=null){
				InputStream instream=entity.getContent();
				resultString = convertStreamToString(instream);
				if(context.getClass().getSimpleName().equals(Activity_DISPLAY)){
					Log.i(MainActivity.FIND_PLACES,"Search is :"+context.getClass().getSimpleName());
					webServiceResponse = getJsonArray(resultString,PlacesResultActivity_DISPLAY_TAG);
				}else if(context.getClass().getSimpleName().equals(Activity_DETAIL)){
					Log.i(MainActivity.FIND_PLACES,"Search is :"+context.getClass().getSimpleName());
					placesDetailJSONObject=getJsonObjectForDetails(resultString,PlacesResultActivity_DETAIL_TAG);
				}else if(context.getClass().getSimpleName().equals(Activity_SEARCH)){
					Log.i(MainActivity.FIND_PLACES,"Search is :"+context.getClass().getSimpleName());
					searchDetailResponse=getJsonArrayForSearch(resultString, SearchResultActivity_SEARCH_TAG);
				}else{
					Log.i(MainActivity.FIND_PLACES,"NO EQUAL is :"+context.getClass().getSimpleName());
				}
				instream.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		String NL = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + NL);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	private static JSONArray getJsonArray(String result,String tag){
		JSONObject json=null;
		JSONArray jArray=null;
		try{
			json = new JSONObject(result);
			jArray=json.getJSONArray(tag);
			//Log.d(MainActivity.FIND_PLACES,"DATA is :"+jArray);

		}catch(Exception e){
			e.printStackTrace();
		}
		return jArray;
	}
	private static JSONObject getJsonObjectForDetails(String result,String tag){
		JSONObject json=null;
		try{
			json = new JSONObject(result).getJSONObject(tag);
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
	private static JSONArray getJsonArrayForSearch(String result,String tag){
		JSONObject json=null;
		JSONArray jArray=null;
		try{
			json = new JSONObject(result).getJSONObject(tag);
			jArray = json.getJSONArray("Results");
		}catch(Exception e){
			e.printStackTrace();
		}
		return jArray;
	}

	public static String createURLRequestToGetReleventPlaces(String placeElement,String callingClass){
		String latitude=MyLocationListener.latitude;
		String longitude=MyLocationListener.longitude;
		String WEB_SERVICE_URL=null;

		if(callingClass.equals(ModalData.CALLING_CLASS_PLACE)){
			Log.d(MainActivity.FIND_PLACES,"CALLING_CLASS_PLACE");
			WEB_SERVICE_URL="https://maps.googleapis.com/maps/api/place/search/json?location="+latitude+","+longitude+"&radius=50000&types="+placeElement+"&sensor=false&key="+GOOGLE_API_KEY;
		}else if(callingClass.equals(ModalData.CALLING_CLASS_SEARCH)){
			Log.d(MainActivity.FIND_PLACES,"CALLING_CLASS_SEARCH");
			WEB_SERVICE_URL="https://maps.googleapis.com/maps/api/place/search/json?location="+latitude+","+longitude+"&radius=1800&keyword="+placeElement+"&sensor=false&key="+GOOGLE_API_KEY;
			Log.d(MainActivity.FIND_PLACES,"URL IS:"+WEB_SERVICE_URL);
		}
		return WEB_SERVICE_URL;
	}
	public static String createURLRequestForMoreDetailsOfAPlace(String placeReference){
		String WEB_SERVICE_URL="https://maps.googleapis.com/maps/api/place/details/json?reference="+placeReference+"&sensor=true&key="+GOOGLE_API_KEY;
		return WEB_SERVICE_URL;
	}
	public static String createURLRequestForSearchPlaces(String searchString){
		String WEB_SERVICE_URL="http://where.yahooapis.com/geocode?location="+formatSearchString(searchString)+"&appid="+YAHOO_API_KEY+"&flags=J";
		return WEB_SERVICE_URL;
	}
	private static String formatSearchString(String searchString){
		return searchString.replaceAll(" ","+");
	}

}
