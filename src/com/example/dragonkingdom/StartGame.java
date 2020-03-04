package com.example.dragonkingdom;

import com.example.dragonkingdom.R.raw;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class StartGame extends Activity {

	private Handler myHandler;
	MediaPlayer song;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_game);
		
		TextView text = (TextView)findViewById(R.id.textView1);
		song = MediaPlayer.create(this, R.raw.dragon);
		song.start();
		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_bottom);
		text.startAnimation(animation);
		
		myHandler = new Handler();
		myHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				finish();
				Intent intent=new Intent(StartGame.this,StartMenu.class);
		    	startActivity(intent);
		    	
			}
		},3800);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_game, menu);
		return true;
	}

}
