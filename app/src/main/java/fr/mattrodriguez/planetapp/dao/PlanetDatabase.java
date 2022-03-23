package fr.mattrodriguez.planetapp.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import fr.mattrodriguez.planetapp.model.Planet;
import fr.mattrodriguez.planetapp.model.PlanetInfo;

@Database(entities = {Planet.class, PlanetInfo.class}, version = 1)
public abstract class PlanetDatabase extends RoomDatabase {
    public abstract PlanetDao planetDao();
    public abstract PlanetInfoDao planetInfoDao();
}