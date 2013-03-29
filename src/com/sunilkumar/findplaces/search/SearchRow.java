package com.sunilkumar.findplaces.search;

import com.sunilkumar.findplaces.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchRow extends RelativeLayout {
	
	private TextView mSuburbName=null;
	private TextView mSuburbAddress=null;

	public SearchRow(Context context) {
		super(context);
		LayoutInflater layoutInflator = ((Activity)context).getLayoutInflater();
		layoutInflator.inflate(R.layout.search_row, this);
		
		this.mSuburbName=(TextView)findViewById(R.id.search_row_suburb_name);
		this.mSuburbAddress=(TextView)findViewById(R.id.search_row_address);
	}
	
	public void setmSuburbName(String suburbName){
		this.mSuburbName.setText(suburbName);
	}
	public void setmSuburbAddress(String suburnAddress){
		this.mSuburbAddress.setText(suburnAddress);
	}

}
