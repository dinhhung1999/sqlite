package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    Context context;
    private static String TABLE_DB_NAME ="Product.db";
    private static String TABLE_NAME ="Product";
    private static int VERSION_TABLE = 2;

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    private String KEY_NAME = "name";
    private String KEY_PRICE = "price";
    private String KEY_QUANTITY = "quantity";

    public SQLHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreatTable = "CREATE TABLE Product (" + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT,"+ "price INTEGER, "+ "quantity INTEGER)";

        // chạy câu lệnh tạo bảng
        db.execSQL(queryCreatTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("drop table if exists "+ TABLE_NAME);
            onCreate(db);
        }
    }
    public void onInsertToDB(String name, int price, int quantity){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_PRICE,price);
        contentValues.put(KEY_QUANTITY,quantity);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
    public void onUpdateProcduct(int id,String name, int price, int quantity){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_PRICE,price);
        contentValues.put(KEY_QUANTITY,quantity);

        sqLiteDatabase.update(TABLE_NAME,contentValues, "id=?", new String[]{String.valueOf(id)});
    }
    public int onDeleteProduct(int id){
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"id=?", new String[]{String.valueOf(id)});
    }

    //
    public List<Product> getAllProductAdvanced(){
        List<Product> products = new ArrayList<>();
        Product product;
        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false,TABLE_NAME,null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));
            int quantity= cursor.getInt(cursor.getColumnIndex("quantity"));

            product = new Product(id, name, price, quantity);
            products.add(product);

        }
        return products;
    }

}
