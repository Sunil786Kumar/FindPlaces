package com.sunilkumar.findplaces.places;

import com.sunilkumar.findplaces.ModalData;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PlacesListAdaptor extends BaseAdapter {

	private Context mContext;
	private PlacesListRow view=null;
	public static String PLACE_SELECTED="PLACE_SELECTED";

	public PlacesListAdaptor(Context context){
		this.mContext=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ModalData.placesTabData.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null) {
			view=new PlacesListRow(mContext);
		}
		else{
			view=(PlacesListRow)convertView;
		}
		view.setTextForPlacesTab(ModalData.placesTabData[position]);

		view.setTag(position);
		view.setOnClickListener( new View.OnClickListener()
		{
			public void onClick(View v)
			{
				callIntent(v);
			}

		});

		return view;
	}
	private void callIntent(View v){
		Intent displayPlacesIntent=new Intent(mContext,PlacesResultDisplayActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PlacesResultDisplayActivity.mSelectedPlaceCategory=
				ModalData.getCorrectPlaceElement(ModalData.getSelectedPlaceElement(Integer.parseInt( v.getTag().toString())));
		PlacesResultDisplayActivity.mCallingClass=ModalData.CALLING_CLASS_PLACE;
		mContext.startActivity(displayPlacesIntent);
	}
}
