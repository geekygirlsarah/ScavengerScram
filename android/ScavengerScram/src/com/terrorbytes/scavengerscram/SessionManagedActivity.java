package com.terrorbytes.scavengerscram;

import android.app.Activity;
import android.os.Bundle;

public class SessionManagedActivity extends Activity
{
	protected SessionManager mSession;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		this.mSession = new SessionManager(getApplicationContext());	
	}

}
