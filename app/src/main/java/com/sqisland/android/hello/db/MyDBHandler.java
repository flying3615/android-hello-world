package com.sqisland.android.hello.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sqisland.android.hello.model.Products;

/**
 * Created by liuyufei on 29/03/17.
 */

public class MyDBHandler extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product.db";

    private static final String TABLE_PRODUCTS = "products";

    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_PRODUCTNAME= "productName";
    private Context context;

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_PRODUCTS+" ("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRODUCTNAME+" TEXT "+
                ");";
        //create table at the creation
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_PRODUCTS);
        onCreate(db);
    }


    public void resetDB(){
        context.deleteDatabase(DATABASE_NAME);
    }

    //Add a new row to the database
    public void addProduct(Products products){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME,products.get_productName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PRODUCTS+" WHERE "+COLUMN_PRODUCTNAME+"=\""+productName+"\";");
    }

    //print out the database as string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_PRODUCTS;

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productName"))!=null){
                dbString += c.getString(c.getColumnIndex("productName"));
                dbString += "\n";
                //fucking stupid buckys!!!
                c.moveToNext();
            }
        }
        db.close();
        return dbString;
    }

}
