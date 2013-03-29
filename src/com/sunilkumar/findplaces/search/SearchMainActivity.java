package com.sunilkumar.findplaces.search;

import com.sunilkumar.findplaces.MainActivity;
import com.sunilkumar.findplaces.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;


public class SearchMainActivity extends Activity  {

	private EditText mSearchString=null;
	private EditText mSearchText=null;
	private ListView mListView=null;
	private SearchBaseAdaptor mSearchAdaptor=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_main);

		this.mListView=(ListView)findViewById(R.id.search_list_view);
		this.mSearchString=(EditText)findViewById(R.id.search_string);
		this.mSearchText=(EditText)findViewById(R.id.search_text);

		mSearchAdaptor=new SearchBaseAdaptor(SearchMainActivity.this);
		//this.mListView.setAdapter(mSearchAdaptor);

		this.mSearchText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence searchString, int start, int before, int count) {
				Log.d(MainActivity.FIND_PLACES,"Places are :"+mSearchString.getText().toString());
				mSearchAdaptor.filterData(searchString);
				mListView.setAdapter(mSearchAdaptor);	
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		}) ;        
	}
	public String getSearchString(){
		return mSearchString.getText().toString();
	}
}
