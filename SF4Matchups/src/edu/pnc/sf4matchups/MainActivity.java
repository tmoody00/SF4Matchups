//Programmer: Terence Moody
//Date 5/5/2014
//Purpose: Program displays data from a SQLlite database in a list and 
//then displays data based on items selected from that list in fragments





package edu.pnc.sf4matchups;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		CharacterListFragment myFragment = new CharacterListFragment();
		fragmentTransaction.replace(R.id.LinearLayout1, myFragment);
		
		fragmentTransaction.commit();
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onBackPressed(){
	    FragmentManager fm = getFragmentManager();
	    if (fm.getBackStackEntryCount() > 0) {
	       
	        fm.popBackStack();
	    } else {
	       
	        super.onBackPressed();  
	    }
	}

}
