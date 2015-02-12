package com.example.androidtest_sj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textView = (TextView) findViewById(R.id.ct);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"MachinatoBold.ttf");
		textView.setTypeface(typeFace);
		textView.setTextSize(22);

	}

	public void onLoginButtonClicked(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	public void onChatButtonClicked(View v) {
		Intent intent = new Intent(this, ChatActivity.class);
		startActivity(intent);
	}

	public void onAnimationTestButtonClicked(View v) {
		Intent intent = new Intent(this, AnimationActivity.class);
		startActivity(intent);
	}
}
