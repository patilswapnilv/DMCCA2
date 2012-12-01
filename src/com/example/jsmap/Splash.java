package com.example.jsmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash  extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(3000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent startTrain = new Intent("com.example.jsmap.MAINACTIVITY");
					startActivity(startTrain);
				}
			}
		};
		timer.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
