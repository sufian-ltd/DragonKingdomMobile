package com.example.dragonkingdom;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

public class AboutUs extends Activity {

	LinearLayout l;
	
	
	private Handler myHandler;
	
	Random r=new Random();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        l=(LinearLayout) findViewById(R.id.ll1);
        getIntent();
        /*myHandler = new Handler();
		myHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				l.setBackgroundResource(r.nextInt(0));
			}
		},1000);*/
	}
}
