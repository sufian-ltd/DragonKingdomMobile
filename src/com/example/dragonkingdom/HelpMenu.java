package com.example.dragonkingdom;

import android.app.Activity;
import android.os.Bundle;

public class HelpMenu extends Activity {

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_menu);
        getIntent();
	}
}
