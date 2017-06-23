package com.example.davidgreenberg.breadapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public SpinnerAlter _alter;
    private BreadDbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        launchApp();
    }

    public void launchApp(){
        this.deleteDatabase(BreadDbHelper.DATABASE_NAME);
        Spinner _spin = (Spinner) findViewById(SpinnerValues.BREAD_NAME_SPINNER_ID);
        this.db = new BreadDbHelper(this.getApplicationContext());
        ModifyDatabase _modify = new ModifyDatabase(this.db);
        _alter = new SpinnerAlter(this,this.db);
        _alter.fillSpinnerByName(SpinnerValues.BREAD_NAME_SPINNER,_spin);
        _spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                try {
                                                    _alter.updateSpinnerByName(SpinnerValues.BREAD_CRUST_SPINNER, parent.getItemAtPosition(position).toString(), R.id.class);
                                                }catch(Exception error){
                                                    error.getCause();
                                                }
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
