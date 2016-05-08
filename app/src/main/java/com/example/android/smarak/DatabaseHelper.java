package com.example.android.smarak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sanu on 07-May-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements DataListener{

    public static final String Database_Name="records.db";
    public static final String Table_Name="records_table";
    public static final String Col1="name";
    public static final String Col2="subject";
    public static final String Col3="message";
    public static final String Col4="time";
    public static final String Col5="image";
    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Table_Name + "("
            + Col1+ " TEXT," + Col2 + " TEXT,"+ Col3 + " TEXT,"+ Col4 + " TEXT,"
            + Col5 + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }


    @Override
    public void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(Col1, data.getName());
            values.put(Col2, data.getSubject());
            values.put(Col3,data.getMessage());
            values.put(Col4 ,data.getTime());
            values.put(Col5, data.getImage());
            db.insert(Table_Name, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem", e + "");
        }
    }


    @Override
    public ArrayList<Data> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Data> dataList = null;
        try{
            dataList = new ArrayList<Data>();
            Cursor cursor = db.rawQuery( "SELECT * FROM "+Table_Name, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Data obj = new Data();
                    obj.setName(cursor.getString(0));
                    obj.setSubject(cursor.getString(1));
                    obj.setMessage(cursor.getString(2));
                    obj.setTime(cursor.getString(3));
                    obj.setImage(cursor.getString(4));
                    dataList.add(obj);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return dataList;
    }

    @Override
    public int getDataCount() {
        return 0;
    }


}
