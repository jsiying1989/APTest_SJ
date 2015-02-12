package com.example.androidtest_sj;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private EditText et_name, et_pass;
	private TextView tv_result;
	private ImageButton loginbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			toolbar.setTitle("Login");
			toolbar.setNavigationIcon(R.drawable.btn_back);
			
			setSupportActionBar(toolbar);

		}
		et_name = (EditText) findViewById(R.id.username);
		et_pass = (EditText) findViewById(R.id.password);

		tv_result = (TextView) findViewById(R.id.tv_result);
		loginbutton = (ImageButton) findViewById(R.id.loginbutton);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();

			}
		});

	}

	public void clear1(View v) {
		et_name.setText("");
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"Machinato.ttf");
		et_name.setTypeface(typeFace);
		et_name.setTextSize(22);
	}

	public void clear2(View v) {
		et_pass.setText("");
		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"Machinato.ttf");
		et_pass.setTypeface(typeFace);
		et_pass.setTextSize(22);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void login(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.loginbutton:

			final String userName = et_name.getText().toString();
			final String password = et_pass.getText().toString();
			if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
				Toast.makeText(this, "Must have Username and Password",
						Toast.LENGTH_LONG).show();
			} else {
				// new thread
				/*
				 * new Thread() { public void run() {
				 */
				loginByAsyncHttpClientPost(userName, password);
				/*
				 * }; }.start();
				 */
			}
			break;
		}

	}

	/*
	 * String spec =
	 * "http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php";
	 */
	public void loginByAsyncHttpClientPost(String userName, String password) {
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php"; // url
		// create params
		RequestParams params = new RequestParams();
		params.put("username", userName);
		params.put("password", password);

		// Post
		client.post(url, params, new AsyncHttpResponseHandler() {
			long startTime = System.currentTimeMillis();

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				if (statusCode == 200) {
					String rB = new String(responseBody);
					// tv_result.setText(new String(responseBody));
					long elapsedTime = System.currentTimeMillis() - startTime;
					if (containornot(rB, "Success")) {

						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("Login Successfully")
								.setMessage(
										new String(responseBody) + " Time: "
												+ elapsedTime)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialoginterface,
													int i) {
												onBackPressed();
											}
										}).show();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("Login Failed")
								.setMessage(
										new String(responseBody) + " Time: "
												+ elapsedTime)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialoginterface,
													int i) {
												Toast.makeText(
														getApplicationContext(),
														"login failed",
														Toast.LENGTH_SHORT)
														.show();
											}
										}).show();
					}
				}

			}

			private boolean containornot(String A, String B) {
				return A.contains(B);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				error.printStackTrace();// print error info
			}
		});
	}

}
