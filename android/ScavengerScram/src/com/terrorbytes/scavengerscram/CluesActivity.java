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

import com.terrorbytes.scavengerscram.model.Clue;

public class CluesActivity extends Activity {

	ArrayList<Clue> clues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clues);

		clues = getClues();

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clues, menu);
		return true;
	}
	
	public ArrayList<Clue> getClues()
	{
		ArrayList<Clue> results = new ArrayList<Clue>();
		Clue c1 = new Clue();
		c1.setTitle("By a ship");
		c1.setDescription("A ship ahoy is not made a chips.");
		results.add(c1);
		
		Clue c2 = new Clue();
		c2.setTitle("Poop deck");
		c2.setDescription("A broom and mop and swab the ...");
		results.add(c2);
		
		Clue c3 = new Clue();
		c3.setTitle("The Sea Master");
		c3.setDescription("A great place to go, the sea horses blow");
		results.add(c3);
		
		return results;
	}

}
