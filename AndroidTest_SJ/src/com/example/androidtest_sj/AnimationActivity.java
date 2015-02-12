package com.example.androidtest_sj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends ActionBarActivity {
	ImageView image;
	private float x, y;
	private int mx, my;
	TextView tv1, tv2;
	private String t = "Animate the App Partner icon. Make it fade to 0% alpha and then to 100% alpha when the fade button is pressed. Allow it to be dragged around the screen by touching and dragging.";
	private String t2 = "BONUS POINTS FOR CREATIVITY.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
	
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			toolbar.setTitle("Animation");
			toolbar.setNavigationIcon(R.drawable.btn_back);
			setSupportActionBar(toolbar);

		}
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		
		tv1(tv1); tv2(tv2);
		image = (ImageView) findViewById(R.id.imageView1);
		image.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x = event.getX();
					y = event.getY();
				case MotionEvent.ACTION_MOVE:
					mx = (int) (event.getRawX() - x);
					my = (int) (event.getRawY() - 50 - y);

					v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
					break;
				}
				return true;
			}
		});
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();

			}
		});
	}

	public void tv1(TextView tv) {
		tv.setText(t.toString());
		tv.setGravity(Gravity.CENTER);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"ExtraLight.ttf");
		tv.setTypeface(typeFace);
		tv.setTextSize(16);
	}
	public void tv2(TextView tv) {
		tv.setText(t2.toString());
		tv.setGravity(Gravity.CENTER);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"SemiBoldItalic.ttf");
		tv.setTypeface(typeFace);
		tv.setTextSize(36);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.animation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void fadebutton(View v) {

		Animation animation = AnimationUtils.loadAnimation(
				AnimationActivity.this, R.anim.rotate);
		image.startAnimation(animation);

	}

	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
