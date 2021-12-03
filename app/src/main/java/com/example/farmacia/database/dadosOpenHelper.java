package com.example.farmacia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dadosOpenHelper extends SQLiteOpenHelper {

    public dadosOpenHelper(@Nullable Context context) {
        super(context, "dados", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scriptDLL.getCreateTableCliente());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
