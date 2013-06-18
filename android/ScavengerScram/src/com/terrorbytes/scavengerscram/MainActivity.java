package com.terrorbytes.scavengerscram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
//import android.widget.TextView;
import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.xml.ScavengerScramParseUtil;

public class MainActivity extends SessionManagedActivity 
{
	private static final int JOIN_GAME_REQUEST = 2;
	private static final String STATE_GAMES = "STATE_GAMES";
	
	private ArrayList<Game> mGames = new ArrayList<Game>();
	
	// UI Components
	//private TextView hiUsernameLabel;
	private ListView gameListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSession = new SessionManager(getApplicationContext());

		//hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);
		
		gameListView = (ListView) findViewById(R.id.gameList);
		gameListView.setAdapter(new GameListAdapter(MainActivity.this, mGames));
		gameListView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id)
			{
				Game g = (Game) gameListView.getItemAtPosition(position);
				launchClueIntent(g);
			}
		});
		
		if(!mSession.isLoggedIn())
		{
			Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(loginIntent);
		}
		else
		{
			getGames();
		}
	}

	private void launchClueIntent(Game game) 
	{
		Intent intent = new Intent(getApplicationContext(), CluesActivity.class);
		intent.putExtra(IntentConstants.GAME_OBJ, game);
		startActivity(intent);
	}

	private void getGames()
	{
		new GetGamesTask().execute(mSession.getUserLogin().getId());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode)
		{
		case JOIN_GAME_REQUEST:
			break;
		}
	}

	public void OnClickJoin(View view)
	{
		Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
		startActivityForResult(intent, JOIN_GAME_REQUEST);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
		// Save the user's current game state
		savedInstanceState.putSerializable(STATE_GAMES, mGames);
		
		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}
	
	@SuppressWarnings("unchecked")
	public void onRestoreInstanceState(Bundle savedInstanceState) 
	{    
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);
		
		// Restore state members from saved instance
		mGames = (ArrayList<Game>) savedInstanceState.getSerializable(STATE_GAMES);
	}
	
	private class GetGamesTask extends HttpRequestTask<Integer, Void, List<Game>>
	{
		@Override
		protected List<Game> doInBackground(Integer... args) 
		{
			String response = null;
			try 
			{
				List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
				loginParams.add(new BasicNameValuePair("id", args[0].toString()));
				loginParams.add(new BasicNameValuePair("command", ScavengerScramConstants.GAMES_COMMAND));
				response = httpPost(ScavengerScramConstants.SCAVENGERSCRAM_URL, loginParams);
			} 
			catch (IOException e) {}
			
			if(response == null) return new ArrayList<Game>(); // Empty list
			
			return ScavengerScramParseUtil.parseToGames(response);
		}
		
		@Override
		protected void onPostExecute(List<Game> gamesResult)
		{
			mGames = new ArrayList<Game>(gamesResult);
			gameListView.setAdapter(new GameListAdapter(MainActivity.this, mGames));
		}
		
	}

}
