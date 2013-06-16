package com.terrorbytes.scavengerscram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.terrorbytes.scavengerscram.model.Clue;
import com.terrorbytes.scavengerscram.xml.ScavengerScramParseUtil;

public class CluesActivity extends Activity 
{
	List<Clue> clues;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clues);

		getClues();

		final ListView lv1 = (ListView) findViewById(R.id.clueList);
		lv1.setAdapter(new ClueListAdapter(this, clues));

		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				// Object o = lv1.getItemAtPosition(position);
				// Game fullObject = (Game)o;
				// Toast.makeText(getApplicationContext(), "You have chosen: " +
				// " " + fullObject.getName(), Toast.LENGTH_LONG).show();
//				Intent i = new Intent(getApplicationContext(),
//						CluesActivity.class);
//				i.putExtra(IntentConstants.GAME_ID, "0");
//				startActivity(i);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clues, menu);
		return true;
	}
	
	public void getClues()
	{
		if(true) // Add checks
		{
			//new GetCluesTask().execute(new Integer(1));
		}
	}
	
	private class GetCluesTask extends HttpRequestTask<Integer, Void, List<Clue>>
	{
		@Override
		protected List<Clue> doInBackground(Integer... args) 
		{
			String response = null;
			try 
			{
				List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
				loginParams.add(new BasicNameValuePair("id", args[0].toString()));
				loginParams.add(new BasicNameValuePair("command", ScavengerScramConstants.CLUES_COMMAND));
				response = httpPost(ScavengerScramConstants.SCAVENGERSCRAM_URL, loginParams);
			} 
			catch (IOException e) {}
			
			if(response == null) return new ArrayList<Clue>(); // Empty list
				
			return ScavengerScramParseUtil.parseToClues(response);
		}
		
		@Override
		protected void onPostExecute(final List<Clue> cluesResult)
		{
			clues = cluesResult;
		}
		
	}

}
