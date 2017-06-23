package com.example.davidgreenberg.breadapplication;

import android.provider.BaseColumns;

/**
 * Created by David Greenberg on 6/22/2017.
 */

public final class BreadEaterContract{
    //File containing default values for database
    public static final int BREAD_TYPE_SQL = R.raw.bread_types_sql;
    private BreadEaterContract(){};

    public static class BreadType extends BaseTables implements BaseColumns {
        public static final String TABLE_NAME = "bread_types";
        public String SELECTION;
        public String[] PROJECTION;
        public static final String COLUMN_NAME_BREAD = "bread_name";
        public static final String SQL_CREATE_ENTRY = "CREATE TABLE IF NOT EXISTS " + BreadType.TABLE_NAME + " (" +
                BreadType._ID + " INTEGER PRIMARY KEY," +
                BreadType.COLUMN_NAME_BREAD + " INTEGER NOT NULL)";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + BreadType.TABLE_NAME;

    }

    /**
     * Table containing information on crust type
     */
    public static class BreadCrust extends BaseTables implements BaseColumns {
        public static final String TABLE_NAME = "bread_crust";
        public static final String COLUMN_NAME_CRUST_TYPE = "crust_type";
        public static final String COLUMN_NAME_BREAD_NAME_ID = "bread_name_id";
        public static final String SELECTION = COLUMN_NAME_BREAD_NAME_ID;
        public static final String[] PROJECTION = new String[]{COLUMN_NAME_BREAD_NAME_ID,COLUMN_NAME_CRUST_TYPE};
        public static final String SQL_CREATE_ENTRY = "CREATE TABLE IF NOT EXISTS " + BreadCrust.TABLE_NAME + " (" +
                BreadCrust._ID + " INTEGER PRIMARY KEY," +
                BreadCrust.COLUMN_NAME_CRUST_TYPE + " TEXT," +
                BreadCrust.COLUMN_NAME_BREAD_NAME_ID + " TEXT)";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + BreadCrust.TABLE_NAME;
    }

    public static class BreadTimes extends BaseTables implements BaseColumns {
        public static final String TABLE_NAME = "bread_times";
        public static final String SQL_CREATE_ENTRY = String.format("CREATE TABLE IF NOT EXISTS %s ( " +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT)"
                ,
                BreadTimes.TABLE_NAME,BreadTimes._ID,
                BreadTimes.COLUMN_NAME_BREAD_ID,
                BreadTimes.COLUMN_NAME_CRUST_ID,
                BreadTimes.COLUMN_NAME_CYCLE,
                BreadTimes.COLUMN_NAME_SIZE,
                BreadTimes.COLUMN_NAME_PREHEAT_TIME,
                BreadTimes.COLUMN_NAME_KNEAD_1,
                BreadTimes.COLUMN_NAME_KNEAD_2,
                BreadTimes.COLUMN_NAME_RISE_1,
                BreadTimes.COLUMN_NAME_KNEAD_3,
                BreadTimes.COLUMN_NAME_RISE_2,
                BreadTimes.COLUMN_NAME_KNEAD_4,
                BreadTimes.COLUMN_NAME_RISE_3,
                BreadTimes.COLUMN_NAME_BAKE);
        public static final String COLUMN_NAME_BREAD_ID = "bread_id";
        public static final String COLUMN_NAME_CRUST_ID = "crust_id";
        public static final String COLUMN_NAME_CYCLE = "cycle";
        public static final String COLUMN_NAME_SIZE = "size";
        public static final String COLUMN_NAME_PREHEAT_TIME = "preheat_time";
        public static final String COLUMN_NAME_KNEAD_1 = "knead_1";
        public static final String COLUMN_NAME_KNEAD_2 = "knead_2";
        public static final String COLUMN_NAME_RISE_1 = "rise_1";
        public static final String COLUMN_NAME_KNEAD_3 = "knead_3";
        public static final String COLUMN_NAME_RISE_2 = "rise_2";
        public static final String COLUMN_NAME_KNEAD_4 = "knead_4";
        public static final String COLUMN_NAME_RISE_3 = "rise_3";
        public static final String COLUMN_NAME_BAKE = "bake";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + BreadTimes.TABLE_NAME;

    }


}

abstract class BaseTables {
    public String TABLE_NAME;
    public String SELECTION;
    public String[] PROJECTION;
    public BaseTables getInstance(){
        return this;
    };
}
