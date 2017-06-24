package com.example.davidgreenberg.breadapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by David Greenberg on 6/8/2017.
 */

public class BreadDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "bread.db";
    private Context CurrentContext;

    public BreadDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.CurrentContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BreadEaterContract.BreadType.SQL_CREATE_ENTRY);
        db.execSQL(BreadEaterContract.BreadCrust.SQL_CREATE_ENTRY);
        db.execSQL(BreadEaterContract.BreadTimes.SQL_CREATE_ENTRY);
        try {
            BreadDbHelper.CreateInsertFromFile(this.CurrentContext, BreadEaterContract.BREAD_TYPE_SQL, db);
            int i = 0;
        } catch (IOException e) {
            Toast.makeText(this.CurrentContext, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(BreadEaterContract.BreadType.SQL_DELETE_ENTRIES);
        db.execSQL(BreadEaterContract.BreadCrust.SQL_DELETE_ENTRIES);
        db.execSQL(BreadEaterContract.BreadTimes.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Insert data from a file on creation of the asset
     *
     * @param context    The context of the current instance containing the files
     * @param resourceId The id of the file to be utilized
     * @param db         The database to which the value is meant to be added
     * @return
     * @throws IOException
     */
    private static int CreateInsertFromFile(Context context, int resourceId, SQLiteDatabase db) throws IOException {
        // Reseting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            db.execSQL(insertStmt);
            result++;
        }
        insertReader.close();

        // returning number of inserted rows
        return result;
    }
}

class ModifyDatabase {

    SQLiteDatabase db;

    public ModifyDatabase(SQLiteOpenHelper helper) {
        db = helper.getWritableDatabase();
    }

    /**
     * Insert values into the database
     *
     * @param bread_name - The name of the bread
     * @param bread_time - The time the bread takes to make
     * @return long - Id of the new row
     */
    public long addBread(String bread_name, String bread_time) {

        ContentValues _values = new ContentValues();

        _values.put(BreadEaterContract.BreadType.COLUMN_NAME_BREAD, bread_name);
        //_values.put(BreadEaterContract.BreadType.COLUMN_NAME_TIME,bread_time);

        return this.db.insert(BreadEaterContract.BreadType.TABLE_NAME, null, _values);
    }

    /**
     * Run through a file and insert the data into the database
     *
     * @param context
     * @param resourceId
     * @return Number of inserted Rows
     * @throws IOException
     */
    public int insertFromFile(Context context, int resourceId) throws IOException {
        // Reseting Counter
        int result = 0;

        // Open the resource
        InputStream insertsStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

        // Iterate through lines (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt = insertReader.readLine();
            db.execSQL(insertStmt);
            result++;
        }
        insertReader.close();

        // returning number of inserted rows
        return result;
    }


}

/**
 * TODO Add functinality to select the required table by the name of the spinner
 */
class ReadBreadDatabase {
    SQLiteDatabase db;

    public ReadBreadDatabase(SQLiteOpenHelper helper) {
        this.db = helper.getReadableDatabase();
    }

    public Cursor GetBreadList() {

        return db.rawQuery("SELECT * FROM " + BreadEaterContract.BreadType.TABLE_NAME, null);


    }

    public Cursor GetDataBaseValues(String selectValues, DatabaseSpinner spinner) {
        String[] projection;
        String tableName;
        String selectionValue;

        try {

            Class<?> test = Utility.getClassByName(spinner.getTag().toString());

            BaseTables _test = (BaseTables) test.newInstance();
            selectionValue = _test.getSelection();
            projection = _test.getProjection();
            tableName = _test.getTableName();

        String selection = selectionValue + "=?";
        String[] selectionArgs = {selectValues};

        return db.query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);

        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        } catch (InstantiationException e) {
            System.out.println(e.toString());
        } catch (IllegalAccessException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public Cursor GetDataBaseValues(String selectValues, String spinnerName) {
        String[] projection;
        String tableName;
        if (spinnerName.compareTo(SpinnerValues.BREAD_CRUST_SPINNER) == 0) {
            projection = new String[]{
                    BreadEaterContract.BreadCrust._ID,
                    BreadEaterContract.BreadCrust.COLUMN_NAME_CRUST_TYPE
            };
            tableName = BreadEaterContract.BreadCrust.TABLE_NAME;
        } else {
            projection = new String[]{
                    BreadEaterContract.BreadCrust._ID,
                    BreadEaterContract.BreadCrust.COLUMN_NAME_CRUST_TYPE
            };
            tableName = BreadEaterContract.BreadCrust.TABLE_NAME;
        }


        String selection = BreadEaterContract.BreadCrust.COLUMN_NAME_BREAD_NAME_ID + "=?";
        String[] selectionArgs = {selectValues};

        return db.query(
                tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null);
    }


}




