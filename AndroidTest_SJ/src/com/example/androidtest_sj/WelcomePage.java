package com.example.androidtest_sj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class WelcomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome_page);
        new Handler().postDelayed(new Runnable(){ 
               
             @Override
             public void run() { 
                 Intent mainIntent = new Intent(WelcomePage.this,MainActivity.class); 
                 WelcomePage.this.startActivity(mainIntent); 
                     WelcomePage.this.finish(); 
             } 
                 
            }, 3000); //3 seconds
	}
}
