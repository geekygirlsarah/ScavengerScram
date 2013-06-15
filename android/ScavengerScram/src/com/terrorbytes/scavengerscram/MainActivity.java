package com.terrorbytes.scavengerscram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView hiUsernameLabel;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);
		
		Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(loginIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view) {
		
		Intent intent = new Intent(this, LoginActivity.class);
		EditText editText = (EditText) findViewById(R.id.email);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
	}

}
