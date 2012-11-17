package com.example.jsmap;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	WebView webview;
	private String[] options = {"Option 1", "Option 2", "Option 3"};
	private boolean[] checkedItems = new boolean[options.length];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new MyJInterface(this), "AndFunction");
        //webview.setWebChromeClient(new WebChromeClient());
        final Activity act = this;
        webview.setWebViewClient(new WebViewClient(){
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(act, description, Toast.LENGTH_SHORT).show();
            }
        });
        webview.loadUrl("file:///android_asset/app.html");
        setContentView(webview);
    }

    public class MyJInterface
    {
    	Context context;
    	MyJInterface(Context c)
    	{
    		context=c;
    	}
    	
    	public void  createDialog() {
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Select options");
			builder.setMultiChoiceItems(options, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					
				}
			});
		builder.create();
		builder.show();
	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
