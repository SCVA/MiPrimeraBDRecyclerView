package com.example.miprimerabd.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.miprimerabd.data.PersonaContract.PersonaEntry;

import androidx.annotation.Nullable;

public class PersonaDBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MI1DB.db";


    public PersonaDBHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PersonaEntry.TABLE_NAME + " ("
                + PersonaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PersonaEntry.ID + " TEXT NOT NULL,"
                + PersonaEntry.NAME + " TEXT NOT NULL,"
                + "UNIQUE (" + PersonaEntry.ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

    public long savePersona(Persona persona) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                PersonaEntry.TABLE_NAME,
                null,
                persona.toContentValues());
    }

    public Cursor getAllPersons() {
        return getReadableDatabase()
                .query(
                        PersonaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getPersonById(String personaId) {
        Cursor c = getReadableDatabase().query(
                PersonaEntry.TABLE_NAME,
                null,
                PersonaEntry.ID + " LIKE ?",
                new String[]{personaId},
                null,
                null,
                null);
        return c;
    }

    public int deletePerson(String personaId) {
        return getWritableDatabase().delete(
                PersonaEntry.TABLE_NAME,
                PersonaEntry.ID + " LIKE ?",
                new String[]{personaId});
    }

    public int updatePerson(Persona persona, String personaId) {
        return getWritableDatabase().update(
                PersonaEntry.TABLE_NAME,
                persona.toContentValues(),
                PersonaEntry.ID + " LIKE ?",
                new String[]{personaId}
        );
    }

}
