package com.example.dragonkingdom;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class StartMenu extends Activity {
	
	LinearLayout l;
	AnimationDrawable myanimationDrawable;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start_menu);
        
        
        //getWindow().addFlags(WindowManager.LayoutParams.FULLSCREEN);
        
        getIntent();
        l=(LinearLayout) findViewById(R.id.ll1);
        l.setBackgroundResource(R.drawable.frameanimation);
        myanimationDrawable=(AnimationDrawable) l.getBackground();
		myanimationDrawable.start();
	}
	
	public void startClick(View v)
    {
    	Intent intent=new Intent(StartMenu.this,GameBoard.class);
    	startActivity(intent);
    }
    public void aboutClick(View v)
    {
    	Intent intent=new Intent(StartMenu.this,AboutUs.class);
    	startActivity(intent);
    }
    public void helpClick(View v)
    {
    	
    	Intent intent=new Intent(StartMenu.this,HelpMenu.class);
    	startActivity(intent);
    
    }
    public void storyClick(View v)
    {
    	
    	Intent intent=new Intent(StartMenu.this,GameStory.class);
    	startActivity(intent);
    
    }
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	finish();
    }
}
