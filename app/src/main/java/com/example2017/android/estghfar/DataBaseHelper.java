package com.example2017.android.estghfar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by M7moud on 23-Feb-18.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DataBaseName="mylist.db";
    public static final String TABLE_NAME="mylist_data";
    public static final String COL1="ID";
    public static final String COL2="ITEM1";

    public DataBaseHelper(Context context) {
        super(context, DataBaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ITEM1 TEXT)";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean AddData(String data){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,data);

         long result=db.insert(TABLE_NAME,null,contentValues);

        if(result == -1 ){
            return false;
        }else {
            return true;
        }

    }

public Cursor getData(){
    SQLiteDatabase db=this.getWritableDatabase();
    Cursor data=db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    return data;
}
}
