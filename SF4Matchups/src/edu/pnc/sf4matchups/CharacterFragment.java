package edu.pnc.sf4matchups;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterFragment extends Fragment {
	
	ArrayList<String> comboList = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.character, container, false);
        
        TextView textName = (TextView)view.findViewById(R.id.txtCharName);
        ImageView imageChar = (ImageView)view.findViewById(R.id.characterPortrait);
        Bundle bundle = getArguments();
  
         String detail = bundle.getString("KEY_DETAIL", "no argument pass");
         textName.setText(detail);
        
        int resID = getResources().getIdentifier(detail.toLowerCase(), "drawable", "edu.pnc.sf4matchups");
        imageChar.setImageResource(resID);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        db.open();
        comboList = db.getComboList(detail);
        String combos = comboList.get(0);
        TextView textCombos = (TextView) view.findViewById(R.id.txtCombos);
        textCombos.setText(combos);
        return view;
    }
}
