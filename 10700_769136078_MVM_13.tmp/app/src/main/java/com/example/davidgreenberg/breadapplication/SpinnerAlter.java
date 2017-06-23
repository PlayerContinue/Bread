package com.example.davidgreenberg.breadapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by David Greenberg on 6/7/2017.
 */

public class SpinnerAlter {

    private Activity activity;
    private ReadBreadDatabase read;

    public SpinnerAlter(Activity activity, SQLiteOpenHelper helper){
        this.activity = activity;
        this.read = new ReadBreadDatabase(helper);
    }

    public void updateSpinnerByName(String spinnerName,String selectedValue,Class<?> c){
        DatabaseSpinner _spin = (DatabaseSpinner) activity.findViewById(Utility.getResId(spinnerName,c));
        fillSpinnerByName(spinnerName,_spin,selectedValue);
    }

    public  void updateSpinnerByName(Spinner spinner,String SelectedValue){

    }

    /*
        Fills a provided spinner with values from a database
     */
    public void fillSpinnerByName(String spinnerName, Spinner spin){
        fillSpinner(getSpinnerDatabaseValues(spinnerName),spin);

    }

    public void fillSpinnerByName(String spinnerName,DatabaseSpinner spin,String choiceValue){
        fillSpinner(getSpinnerDatabaseValues(spin,choiceValue),spin);

    }



    public void fillSpinner(ArrayList<String> list, Spinner spin){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.activity, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
    }

    /*
    Gets values from the database as a ArrayList
    TODO: Add Database call and fill functionality
     */
    public ArrayList<String> getSpinnerDatabaseValues(String spinnerName){
        Cursor _cursor = this.read.GetBreadList();
        ArrayList<String> breads = new ArrayList<String>();
        String item;
        while(_cursor.moveToNext()){
            item = _cursor.getString(_cursor.getColumnIndexOrThrow(BreadEaterContract.BreadType.COLUMN_NAME_BREAD));
           breads.add(String.valueOf(item));
        }
        return breads; //new ArrayList<String>(Arrays.asList("test1","test2"));
    }

    public ArrayList<String> getSpinnerDatabaseValues(DatabaseSpinner spinner,String spinnerValue){
        Cursor _cursor = this.read.GetDataBaseValues(spinnerValue,spinner);
        ArrayList<String> breads = new ArrayList<String>();
        String item;
        while(_cursor.moveToNext()){
            item = _cursor.getString(_cursor.getColumnIndexOrThrow(BreadEaterContract.BreadCrust.COLUMN_NAME_CRUST_TYPE));
            breads.add(String.valueOf(item));
        }


        return breads;
    }

    public ArrayList<String> getSpinnerDatabaseValues(String spinnerName,String spinnerValue){

        //Get the cursor containing the required values
        Cursor _cursor = this.read.GetDataBaseValues(spinnerValue,spinnerName);
        ArrayList<String> breads = new ArrayList<String>();
        String item;
        while(_cursor.moveToNext()){
            item = _cursor.getString(_cursor.getColumnIndexOrThrow(BreadEaterContract.BreadCrust.COLUMN_NAME_CRUST_TYPE));
            breads.add(String.valueOf(item));
        }


        return breads;
    }


}
