package fr.mattrodriguez.planetapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import fr.mattrodriguez.planetapp.model.Planet;

@Dao
public interface PlanetDao {
    @Query("SELECT * FROM planet")
    List<Planet> getAll();

    @Insert
    void insertAll(ArrayList<Planet> planets);

    @Query("DELETE FROM planet")
    void deleteAll();
}