package com.example.dragonkingdom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class GameStory extends Activity {

	TextView txt;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_story);
        getIntent();
        txt=(TextView) findViewById(R.id.textView1);
        txt.setSelected(true);
	}
}
