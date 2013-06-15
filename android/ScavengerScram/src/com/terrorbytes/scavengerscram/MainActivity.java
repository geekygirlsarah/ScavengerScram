package com.terrorbytes.scavengerscram;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.terrorbytes.scavengerscram.model.Game;

public class MainActivity extends Activity {

	private static ArrayList<Game> games;
	
	TextView hiUsernameLabel;
	//public final static String USERNAME = "username";
	//public final static String GAME_CODE = "GAME_CODE";

	String username;
	String gameCode;
	String playerName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		games = new ArrayList<Game>();
		hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);
		
		Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(loginIntent);
	}
	
	public int gameCount()
	{
		return games.size();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data.getExtras().containsKey(IntentConstants.USERNAME)) {
			username = data.getExtras().getString(IntentConstants.USERNAME);
			hiUsernameLabel.setText("hi" + username);
		}
		else if(data.getExtras().containsKey(IntentConstants.GAME_CODE))
		{
			gameCode = data.getExtras().getString(IntentConstants.GAME_CODE);
			playerName = data.getExtras().getString(IntentConstants.PLAYER_NAME);
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
	

}
