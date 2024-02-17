package com.example.r2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProdusDAO {
    @Insert
    long insertProdus(Produs produs);

    @Query("SELECT * FROM produse WHERE pret >:pp")
    List<Produs>selectAll(float pp);

    @Delete
    void deleteProdus(Produs produs);
}
