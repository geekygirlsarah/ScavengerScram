package com.terrorbytes.scavengerscram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class JoinActivity extends Activity {
	public final static String GAME_CODE = "GAME_CODE";
	
	TextView codeEditText;
	String code;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);
		codeEditText = (TextView) findViewById(R.id.codeEditText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.join, menu);
		return true;
	}
	

	public void OnClickSubmit(View view) {
		code = codeEditText.getText().toString();
		
		if(code != null && ! code.isEmpty())
		{
			Intent intent = getIntent();
			intent.putExtra(GAME_CODE, code);
			setResult(1, intent);
			
			finish();
		}
	}

}
