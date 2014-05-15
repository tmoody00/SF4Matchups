package edu.pnc.sf4matchups;

import java.util.ArrayList;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CharacterListFragment extends ListFragment{
	

	
    ArrayList<String> charList = new ArrayList<String>();

	
	

	

 /*   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = new View(getActivity());
        return v;
    }*/
    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		DatabaseHelper db = new DatabaseHelper(getActivity());
		db.open();
	    charList = db.getArrayList();
	    db.close();
		
		
		
		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, charList));
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		 super.onListItemClick(l,v,position,id);
		 String clickedDetail = (String) l.getItemAtPosition(position);

		 CharacterFragment myCharFragment = new CharacterFragment();
		 Bundle bundle = new Bundle();
		 bundle.putString("KEY_DETAIL", clickedDetail);
		 myCharFragment.setArguments(bundle);
		 FragmentTransaction fragmentTransaction =
		 getFragmentManager().beginTransaction();
		 fragmentTransaction.addToBackStack(null);
	     fragmentTransaction.replace(R.id.LinearLayout1, myCharFragment);
         fragmentTransaction.commit(); 
		    
	 }

	

	
}