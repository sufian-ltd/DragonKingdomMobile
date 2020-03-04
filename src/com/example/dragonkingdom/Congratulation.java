package com.example.dragonkingdom;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Congratulation extends Activity {

	private Handler myHandler;
	MediaPlayer song;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.congratulation);
		TextView text = (TextView)findViewById(R.id.textView1);
		song = MediaPlayer.create(this, R.raw.win);
		song.start();
		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequential);
		text.startAnimation(animation);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
	}
}
