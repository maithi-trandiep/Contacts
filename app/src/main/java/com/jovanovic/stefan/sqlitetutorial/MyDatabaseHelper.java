package com.jovanovic.stefan.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOM = "contact_nom";
    private static final String COLUMN_PRENOM = "contact_prenom";
    private static final String COLUMN_TELEPHONE = "contact_telephone";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOM + " TEXT, " +
                        COLUMN_PRENOM + " TEXT, " +
                        COLUMN_TELEPHONE + " TEXT);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addContact(String nom, String prenom, String telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_TELEPHONE, telephone);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, " Contact ajouté !", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String nom, String prenom, String telephone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_TELEPHONE, telephone);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Erreur lors de la mise à jour", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Contact mis à jour !", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Erreur lors de la suppression.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Contact supprimé.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
