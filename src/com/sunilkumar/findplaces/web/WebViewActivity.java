package com.sunilkumar.findplaces.web;

import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

	private WebView mWebView=null;
	/*
	 * public API to open URLs provided to mURL
	 */
	public static String mURL=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view);
		this.mWebView=(WebView)findViewById(R.id.web_view);
		this.mWebView.loadUrl(mURL);
	}

}
