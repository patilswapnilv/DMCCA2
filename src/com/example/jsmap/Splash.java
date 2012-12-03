package com.example.jsmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class Splash  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread(){
			public void run(){
				try{
					ScaleAnimation anim = new ScaleAnimation(0, 1, 0, 1,
														Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					anim.setDuration(3000);
					ImageView iView = (ImageView) findViewById(R.id.imageView1);
					iView.startAnimation(anim);
					sleep(3000);
					iView.setAnimation(null);
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
