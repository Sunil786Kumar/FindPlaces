package com.sunilkumar.findplaces;

import android.app.ProgressDialog;
import android.content.Context;

public class DisplaySpinner {
	private static ProgressDialog progressDialog;

	public static void displaySpinner(Context context,String title,String message){
		progressDialog = ProgressDialog.show(context, title, message);

	}
	public static void hideSpinner(){
		progressDialog.dismiss();
	}

}
//ListViewWebServiceActivity list = (ListViewWebServiceActivity)context;
//list.testMethod();