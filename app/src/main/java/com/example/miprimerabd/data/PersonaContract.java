package com.example.miprimerabd.data;

import android.provider.BaseColumns;

public class PersonaContract {
    public static abstract class PersonaEntry implements BaseColumns {
        public static final String TABLE_NAME ="persona";

        public static final String ID = "id";
        public static final String NAME = "name";
    }
}
