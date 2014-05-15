package edu.pnc.sf4matchups;


import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if(!prefs.getBoolean("firstTime", false))
		{
		DatabaseHelper db = new DatabaseHelper(this);
		db.open();
		db.populateData();
		db.close();
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("firstTime", true);
		editor.commit();
		}
		TimerTask task = new TimerTask() {
			public void run(){
				finish();
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
			}
		};
		Timer opening = new Timer();
		opening.schedule(task, 5000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
