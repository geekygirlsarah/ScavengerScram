package com.terrorbytes.scavengerscram;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView hiUsernameLabel;
	public final static String USERNAME = "username";
	public final static String GAME_CODE = "GAME_CODE";

	String gameCode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);

		Intent loginIntent = new Intent(getApplicationContext(),
				LoginActivity.class);

		// loginIntent.putExtra(USERNAME, USERNAME);
		startActivityForResult(loginIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data.getExtras().containsKey(USERNAME)) {
			String username = data.getExtras().getString(USERNAME);
			hiUsernameLabel.setText("hi" + username);
		}
		else if(data.getExtras().containsKey(GAME_CODE))
		{
			gameCode = data.getExtras().getString(GAME_CODE);
			System.out.println(gameCode);
		}
	}

	public void OnClickJoin(View view) {
		Intent i = new Intent(getApplicationContext(), JoinActivity.class);
		startActivityForResult(i, 2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// public void sendMessage(View view) {
	//
	// Intent intent = new Intent(this, LoginActivity.class);
	// EditText editText = (EditText) findViewById(R.id.email);
	// String message = editText.getText().toString();
	// intent.putExtra(EXTRA_MESSAGE, message);
	// }

}
