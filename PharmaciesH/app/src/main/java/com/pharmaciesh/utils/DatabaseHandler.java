package com.pharmaciesh.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pharmaciesh.entity.Medication;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "medicationsManager";
    private static final String TABLE_MEDICATIONS = "medications";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMBLEM = "emblem";
    private static final String KEY_ACTIVE_SUB = "active_sub";
    private static final String KEY_INSTRUCTION = "instruction";
    private static final String KEY_RELEASE_FORM = "release_form";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MEDICATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMBLEM + " TEXT, " + KEY_ACTIVE_SUB + " TEXT," + KEY_INSTRUCTION + " TEXT," + KEY_RELEASE_FORM + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATIONS);

        onCreate(db);
    }

    @Override
    public void addMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, medication.getId());
        values.put(KEY_NAME, medication.getName());
        values.put(KEY_EMBLEM, medication.getEmblem());
        values.put(KEY_ACTIVE_SUB, medication.getActiveSub());
        values.put(KEY_INSTRUCTION, medication.getInstruction());
        values.put(KEY_RELEASE_FORM, medication.getReleaseForm());

        db.insert(TABLE_MEDICATIONS, null, values);
        db.close();
    }

    @Override
    public Medication getMedication(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICATIONS, new String[] { KEY_ID,
                        KEY_NAME, KEY_EMBLEM, KEY_ACTIVE_SUB, KEY_INSTRUCTION, KEY_RELEASE_FORM }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Medication medication = new Medication(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return medication;
    }



    @Override
    public List<Medication> getAllMedications() {
        List<Medication> contactList = new ArrayList<Medication>();
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication();
                medication.setId(Integer.parseInt(cursor.getString(0)));
                medication.setName(cursor.getString(1));
                medication.setEmblem(cursor.getString(2));
                medication.setActiveSub(cursor.getString(3));
                medication.setInstruction(cursor.getString(4));
                medication.setReleaseForm(cursor.getString(5));

                contactList.add(medication);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    @Override
    public int updateMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medication.getName());
        values.put(KEY_EMBLEM, medication.getEmblem());
        values.put(KEY_ACTIVE_SUB, medication.getActiveSub());
        values.put(KEY_INSTRUCTION, medication.getInstruction());
        values.put(KEY_RELEASE_FORM, medication.getReleaseForm());

        return db.update(TABLE_MEDICATIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(medication.getId()) });
    }

    @Override
    public void deleteMedication(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICATIONS, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICATIONS, null, null);
        db.close();
    }

    @Override
    public int getMedicationsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEDICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        return cursor.getCount();
    }

    @Override
    public boolean checkExists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "Select * from " + TABLE_MEDICATIONS +" where " + KEY_ID + " = " + String.valueOf(id);
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}