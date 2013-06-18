package com.terrorbytes.scavengerscram;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.terrorbytes.scavengerscram.model.Clue;
import com.terrorbytes.scavengerscram.model.Game;
import com.terrorbytes.scavengerscram.model.UserLogin;
import com.terrorbytes.scavengerscram.net.ScavengerScramHttpUtils;
import com.terrorbytes.scavengerscram.xml.ScavengerScramParseUtil;

public class CluesActivity extends Activity 
{
	private int gameId;
	private Game game;
	private Clue selectedClue;
	private UserLogin userBean;
	
	// Photo
	private static final int TAKE_PICTURE = 0;
	private Uri mUri;
	private Bitmap mPhoto;
	
	// UI Components
	private ListView lv1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// Set content view
		setContentView(R.layout.activity_clues);
				
		// Get game ID from intent
		this.gameId = getIntent().getIntExtra(IntentConstants.GAME_ID, -1);
		
		// Get Game from intent
		this.game = (Game) getIntent().getSerializableExtra(IntentConstants.GAME_OBJ);
		
		// Get Game from intent
		this.userBean = (UserLogin) getIntent().getSerializableExtra(IntentConstants.USER_OBJ);
		
		// Get ListView
		this.lv1 = (ListView) findViewById(R.id.clueList);

		// Add on click listener to list view
		this.lv1.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,long id)
			{
				selectedClue = (Clue) lv1.getItemAtPosition(position);
				Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				File f = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				mUri = Uri.fromFile(f);
				startActivityForResult(i, TAKE_PICTURE);
			}
		});
		
		// Get list of clues
		getClues();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clues, menu);
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK)
        {
	        getContentResolver().notifyChange(mUri, null);
	        ContentResolver cr = getContentResolver();
	        try 
	        {
	        	mPhoto = android.provider.MediaStore.Images.Media.getBitmap(cr, mUri);
	            //((ImageView) findViewById(R.id.photo_holder)).setImageBitmap(mPhoto);
	            SendPhotoTask sendPhotoTask = new SendPhotoTask();
	            sendPhotoTask.execute((Void)null);
	        } 
	        catch (Exception e) 
	        {
	        	Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
	        }
        }
	}
	
	public void getClues()
	{
		
		if(true) // Add checks
		{
			GetCluesTask getCluesTask = new GetCluesTask();
			getCluesTask.execute(Integer.valueOf(this.gameId));
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
		protected void onPostExecute(List<Clue> cluesResult)
		{
			lv1.setAdapter(new ClueListAdapter(CluesActivity.this, cluesResult));
		}
		
	}
	
	private class SendPhotoTask extends HttpRequestTask<Void, Void, Boolean>
	{
		@Override
		protected Boolean doInBackground(Void... args) 
		{
			String response = null;
			try 
			{
				List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
				loginParams.add(new BasicNameValuePair("player", userBean.getId() + ""));
				loginParams.add(new BasicNameValuePair("game", "1"));
				loginParams.add(new BasicNameValuePair("clue", selectedClue.getClueNumber() + ""));
				loginParams.add(new BasicNameValuePair("file", ScavengerScramHttpUtils.convertToBase64(mPhoto)));
				loginParams.add(new BasicNameValuePair("command", ScavengerScramConstants.ANSWER_COMMAND));
				response = httpPost(ScavengerScramConstants.SCAVENGERSCRAM_URL, loginParams);
			} 
			catch (IOException e) {}
			
			if(response == null) return false; // Empty list
				
			return ScavengerScramParseUtil.parseForAnswerResult(response);
		}
		
		@Override
		protected void onPostExecute(final Boolean cluesResult)
		{
			
			runOnUiThread(new Runnable()
			{
				@Override
				public void run() 
				{
					//Toast.makeText(CluesActivity.this, (Boolean.FALSE.equals(cluesResult)) ? "Picture not sent" : "Picture sent", Toast.LENGTH_LONG).show();
				}
				
			});
		}
		
	}

}
