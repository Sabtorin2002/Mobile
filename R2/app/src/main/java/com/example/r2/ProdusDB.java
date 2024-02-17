package com.example.r2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Produs.class}, version = 1,exportSchema = false)
public abstract class ProdusDB extends RoomDatabase{
    private static final String DB_NAME="produse.db";
    private static ProdusDB instanta;

    public static ProdusDB getInstance(Context context){
        if(instanta==null)
        {
            instanta= Room.databaseBuilder(context,ProdusDB.class,DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build();
        }
        return instanta;
    }

    public abstract ProdusDAO getProdusDAO();//metoda abstracta getProdusDAO de tip ProdusDAO
}
