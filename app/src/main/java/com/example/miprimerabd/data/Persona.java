package com.example.miprimerabd.data;

import android.annotation.SuppressLint;
import android.database.Cursor;
import com.example.miprimerabd.data.PersonaContract.PersonaEntry;
import android.content.ContentValues;

public class Persona {
    private String id;
    private String name;

    public Persona(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @SuppressLint("Range")
    public Persona(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(PersonaEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(PersonaEntry.NAME));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(PersonaEntry.ID, id);
        values.put(PersonaEntry.NAME, name);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
