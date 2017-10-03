package com.example.nikolauron.photoapp.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by A00923196 on 2017-10-03.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper db = null;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WIPDB.db";

    public static final String PHOTO_TABLE_NAME = "photos";
    public static final String PHOTO_COLUMN_ID = "id";
    public static final String PHOTO_COLUMN_TITLE = "title";
    public static final String PHOTO_COLUMN_DATE = "date";

    private DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //this.onUpgrade(this.getWritableDatabase(), 1, 2);
    }

    public static DBHelper getInstance(Context context) {
        if (db == null) {
            db = new DBHelper(context.getApplicationContext());
        }
        return db;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating Table");
        String CREATE_TABLE;

        CREATE_TABLE = "CREATE TABLE " + PHOTO_TABLE_NAME +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT, " +
                "date TEXT" +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS photos");
        onCreate(db);
    }

    public void addPhoto(Photo p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PHOTO_COLUMN_TITLE, p.getTitle());
        values.put(PHOTO_COLUMN_DATE, p.getDate());
        db.insert(PHOTO_TABLE_NAME, null, values);
    }

    public ArrayList<Photo> getAllPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();
        String selectQuery = "Select * From " + PHOTO_TABLE_NAME + " ORDER BY ID ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Photo temp = new Photo (
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                photos.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return photos;
    }

    public Photo getPhoto(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PHOTO_TABLE_NAME,
                new String[] {PHOTO_COLUMN_ID, PHOTO_COLUMN_TITLE, PHOTO_COLUMN_DATE},
                PHOTO_COLUMN_ID + " = ?",
                new String[] {id},
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Photo(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
        );
    }

    public void updatePhoto(String id, Photo newPhoto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PHOTO_COLUMN_TITLE, newPhoto.getTitle());
        values.put(PHOTO_COLUMN_DATE, newPhoto.getDate());

        this.getWritableDatabase().update(
                PHOTO_TABLE_NAME,
                values,
                PHOTO_COLUMN_ID + " = ?",
                new String[] {id}
        );
    }

    public void removePhoto(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PHOTO_TABLE_NAME, PHOTO_COLUMN_ID + " = ?", new String[] {id});
    }
}
