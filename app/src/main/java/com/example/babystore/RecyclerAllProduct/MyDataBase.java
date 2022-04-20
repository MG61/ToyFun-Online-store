package com.example.babystore.RecyclerAllProduct;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyDataBase  extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "ToyFun.db";
    public static final int DATABASE_VERSION = 1;

    public static final  String TABLE_NAME = "total_name";
    public static final  String TABLE_NAME2 = "lego";
    public static final  String TABLE_NAME3 = "figure";
    public static final  String TABLE_NAME4 = "radio";
    public static final  String TABLE_NAME5 = "active";
    public static final  String TABLE_NAME6 = "birthday";
    public static final  String TABLE_NAME7 = "razv";
    public static final  String TABLE_NAME8 = "golovolomky";
    public static final  String TABLE_NAME9 = "interactive";


    public static final  String COLUMN_ID = "_id";
    public static final  String COLUMN_NAME = "name1";
    public static final  String COLUMN_PRICE = "price";
    public static final  String COLUMN_ART = "art";
    public static final  String COLUMN_IMAGE = "image";
    final int REQUEST_CODE_GALLERY = 999;

    public MyDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "  + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query);

        String query2 = "CREATE TABLE "  + TABLE_NAME2 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query2);

        String query3 = "CREATE TABLE "  + TABLE_NAME3 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query3);

        String query4 = "CREATE TABLE "  + TABLE_NAME4 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query4);

        String query5 = "CREATE TABLE "  + TABLE_NAME5 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query5);

        String query6 = "CREATE TABLE "  + TABLE_NAME6 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query6);

        String query7 = "CREATE TABLE "  + TABLE_NAME7 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query7);

        String query8 = "CREATE TABLE "  + TABLE_NAME8 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query8);

        String query9 = "CREATE TABLE "  + TABLE_NAME9 +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_ART + " TEXT, " +
                COLUMN_IMAGE + " BLOB);";
        db.execSQL(query9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME6);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME7);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME8);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME9);
        onCreate(db);
    }

    void addproduk(String name1, String price, String art, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,  name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk2(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk3(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME3, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk4(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME4, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk5(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME5, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk6(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME6, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk7(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME7, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk8(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME8, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    void addproduk9(String name1, String price, String art, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name1);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_ART, art);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME9, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
