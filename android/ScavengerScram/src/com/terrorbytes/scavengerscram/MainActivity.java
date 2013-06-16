package com.terrorbytes.scavengerscram;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.terrorbytes.scavengerscram.model.Game;

public class MainActivity extends Activity {

ArrayList<Game> games;
	
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
		//games = new ArrayList<Game>();
		hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);
		
		Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(loginIntent);
		
		games = getGames();
        
        final ListView lv1 = (ListView) findViewById(R.id.gameList);
        lv1.setAdapter(new GameListAdapter(this, games));
        
//        lv1.setOnItemClickListener(new OnItemClickListener() {
//         @Override
//         public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
//          Object o = lv1.getItemAtPosition(position);
//          Game fullObject = (Game)o;
//          Toast.makeText(getApplicationContext(), "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
//         }  
//        });
	}
	
	private ArrayList<Game> getGames(){
	     ArrayList<Game> results = new ArrayList<Game>();
	     
	     Game g1 = new Game();
	     g1.setName("Piratescapades");
	     g1.setDescription("Find the treasure!");
	     results.add(g1);
	     
	     Game g2 = new Game();
	     g2.setName("The Sound of Thunder");
	     g2.setDescription("Get Them Dinos!");
	     results.add(g2);
	     
	     Game g3 = new Game();
	     g3.setName("Sally's Lame Birthday");
	     g3.setDescription("Wander aimlessly and give up");
	     results.add(g3);
	     
	     return results;
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
