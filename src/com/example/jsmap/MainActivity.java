package com.example.jsmap;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	WebView webview;
	private String[] options = {"Stations", "Trains", "Tracks"};
	private boolean[] checkedItems = new boolean[options.length];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview = new WebView(this);
        MyJInterface javaInterface = new MyJInterface(this, webview);
        webview.addJavascriptInterface(javaInterface,  "AndFunction");
        webview.getSettings().setJavaScriptEnabled(true);
        final Activity act = this;
        webview.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(act, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        webview.loadUrl("file:///android_asset/app.html");
        setContentView(webview);
    }
    


    public class MyJInterface
    {
    	Context context;
    	WebView mView;
    	AlertDialog.Builder builder;
    	MyJInterface(Context c, WebView v)
    	{
    		context=c;
    		mView=v;
    		checkedItems[1] = true;
    		checkedItems[2] = true;
    		createDialog();
    	}
    	
    	public void  createDialog() {
    		builder = new AlertDialog.Builder(MainActivity.this);
    		builder.setTitle("Show ...");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			builder.setMultiChoiceItems(options, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					checkedItems[which]=isChecked;
					
					switch(which)
					{
					case 0:
						if(isChecked)
						{
							mView.loadUrl("javascript:loadStations()");
						}
						else
						{
							mView.loadUrl("javascript:clearStationMarkers()");
						}
					break;
					case 1:
						if(isChecked)
						{
							mView.loadUrl("javascript:showTrains()");
						}
						else
						{
							mView.loadUrl("javascript:clearTrainMarkers()");
						}
					break;
					case 2:
						if(isChecked)
						{
							mView.loadUrl("javascript:showTracks()");
						}
						else
						{
							mView.loadUrl("javascript:hideTracks()");
						}
					break;
					}
				}
			});
		builder.create();
	}
    	
    	public void showDialog()
    	{
    		builder.show();
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
