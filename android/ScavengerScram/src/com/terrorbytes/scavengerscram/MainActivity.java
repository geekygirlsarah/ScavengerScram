package com.terrorbytes.scavengerscram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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

import com.terrorbytes.scavengerscram.model.Clue;
import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;
import com.terrorbytes.scavengerscram.net.ScavengerScramHttpUtils;
import com.terrorbytes.scavengerscram.xml.ScavengerScramParseUtil;

public class MainActivity extends Activity 
{
	List<Game> games = new ArrayList<Game>();

	//GameListAdapter gla;

	String username;
	String gameCode;
	String playerName;
	
	// UI Components
	TextView hiUsernameLabel;
	ListView gameListView;
	
	UserLogin userBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//hiUsernameLabel = (TextView) findViewById(R.id.hiUsernameLabel);
		
		gameListView = (ListView) findViewById(R.id.gameList);

		gameListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id)
			{
				Game g = (Game) gameListView.getItemAtPosition(position);
				launchClueIntent(g);
			}
		});
		
		Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivityForResult(loginIntent, 1);
	}

	private void launchClueIntent(Game g) 
	{
		Intent i = new Intent(getApplicationContext(), CluesActivity.class);
		i.putExtra(IntentConstants.GAME_ID, g.getGameId());
		i.putExtra(IntentConstants.GAME_OBJ, g);
		i.putExtra(IntentConstants.USER_OBJ, userBean);
		startActivity(i);
	}

	private void getGames()
	{
		//ArrayList<Game> results = new ArrayList<Game>();

		Game g1 = new Game();
		g1.setName("Piratescapades");
		g1.setDescription("Find the treasure!");
		g1.setGameCode("1");
		games.add(g1);

		Game g2 = new Game();
		g2.setName("The Sound of Thunder");
		g2.setDescription("Get Them Dinos!");
		games.add(g2);

		Game g3 = new Game();
		g3.setName("Sally's Lame Birthday");
		g3.setDescription("Wander aimlessly and give up");
		games.add(g3);

		if(userBean != null)
			new GetGamesTask().execute(userBean.getId());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		if (data.getExtras().containsKey(IntentConstants.GAME_CODE)) {
			gameCode = data.getExtras().getString(IntentConstants.GAME_CODE);
			playerName = data.getExtras().getString(IntentConstants.PLAYER_NAME);
			System.out.println(gameCode);
		}
		
		if(data.getExtras().containsKey(IntentConstants.USER_OBJ))
		{
			this.userBean = (UserLogin) data.getExtras().getSerializable(IntentConstants.USER_OBJ);
			//hiUsernameLabel.setText("hi " + userBean.getName());
		}
		else
		{
			this.userBean = null;
		}

		getGames();
	}

	public void OnClickJoin(View view)
	{
		Intent i = new Intent(getApplicationContext(), JoinActivity.class);
		startActivityForResult(i, 2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
			gameListView.setAdapter(new GameListAdapter(MainActivity.this, gamesResult));
		}
		
	}

}
