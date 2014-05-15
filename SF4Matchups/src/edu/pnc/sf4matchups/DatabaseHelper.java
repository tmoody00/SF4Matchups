package edu.pnc.sf4matchups;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper  extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "Characters";
    //public static final String COLUMN_PHONES ="phone_number";

    private static final String DATABASE_NAME = "characters.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    //create database
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + " ( CharacterName VARCHAR,Moves VARCHAR, Combos VARCHAR);";
    public DatabaseHelper(Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        // TODO Auto-generated constructor stub
    }
    public void populateData()
    {
    	
    	DatabaseHelper db = new DatabaseHelper(context);
    	db.open();
    	db.addCharacter("Adon", "_Rising Jaguar - F,D,F any kick_", "lk,lk, lk Rising Jaguar");
    	db.addCharacter("Balrog", "_Rushing Straight - Charge b then f any punch_", "lp,lp ~ lkxx HP headbutt");
      	db.addCharacter("Ryu", "_Shoryukent - dp any punch_", "c.lp,clp ~ c.hpxx lk tatsu");
      	db.addCharacter("Ken", "_Shoryuken - dp any punch_", "c.lp,c.lp,lp,c.mk xx hk tatsu");
      	db.addCharacter("Akuma", "_Shoryuken - dp any punch_", "hk ~ lpxx lk tatsu, hp shoryuken");
    	db.close();
    }
    public SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) 
    {
        // TODO Auto-generated method stub

        db.execSQL(DATABASE_CREATE);
        db.execSQL("Create table Matches(Player Varchar, Opponent Varchar, Tips Varchar);");
    }
    public void addCharacter(String name, String moves, String combos)
    {
    	ContentValues values =new ContentValues(3);
    	
    	values.put("CharacterName", name);
    	values.put("moves", moves);
    	values.put("combos", combos);
    	
    	getWritableDatabase().insert("Characters" , "name", values);
    }
    
    public void addCombo(SQLiteDatabase db,String character, String Combo)
    {
    	db.execSQL("UPDATE TABLE Character set combos = combos +  _'"+Combo+"'where character = '"+character+"';");
    }
    public void addTip(String tip){
    	
    }
    

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {
        // TODO Auto-generated method stub
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }
    
  public void open(){
	  db = getWritableDatabase();
  }
  public void close(){
	  db.close();
  }

  public ArrayList<String> getArrayList(){
	  ArrayList<String> charList = null;
	  Cursor cursor = null;
	  try{
		  String query = "Select CharacterName from Characters;";
		  cursor = db.rawQuery(query, null);
		  if(cursor != null && cursor.moveToFirst()){
			  charList = new ArrayList<String>();
			  do
			  {
				  charList.add(cursor.getString(0));
			  }while(cursor.moveToNext());
			  }
		  
		  }catch (Exception e){
			  e.printStackTrace();
			  charList=null;
			 
		  }finally{
			  if(cursor!=null && !cursor.isClosed()){
				  cursor.deactivate();
				  cursor.close();
				  cursor = null;
			  }
			  close();
	  
	  }
	  return charList;
  }
  
  public ArrayList<String> getComboList(String Character){
	  ArrayList<String> charList = null;
	  Cursor cursor = null;
	  try{
		  String query = "Select Combos from Characters where CharacterName = '"+Character+"';";
		  cursor = db.rawQuery(query, null);
		  if(cursor != null && cursor.moveToFirst()){
			  charList = new ArrayList<String>();
			  do
			  {
				  charList.add(cursor.getString(0));
			  }while(cursor.moveToNext());
			  }
		  
		  }catch (Exception e){
			  e.printStackTrace();
			  charList=null;
			 
		  }finally{
			  if(cursor!=null && !cursor.isClosed()){
				  cursor.deactivate();
				  cursor.close();
				  cursor = null;
			  }
			  close();
	  
	  }
	  return charList;
  }
  public ArrayList<String> getTipList(String Player, String Opponent){
	  ArrayList<String> tipList = null;
	  Cursor cursor = null;
	  try{
		  String query = "Select Tips from Matches where Player = '"+Player+"' and Opponent = '"+Opponent+"';";
		  cursor = db.rawQuery(query, null);
		  if(cursor != null && cursor.moveToFirst()){
			  tipList = new ArrayList<String>();
			  do
			  {
				  tipList.add(cursor.getString(0));
			  }while(cursor.moveToNext());
			  }
		  
		  }catch (Exception e){
			  e.printStackTrace();
			  tipList=null;
			 
		  }finally{
			  if(cursor!=null && !cursor.isClosed()){
				  cursor.deactivate();
				  cursor.close();
				  cursor = null;
			  }
			  close();
	  
	  }
	  return tipList;
  }
 
}

