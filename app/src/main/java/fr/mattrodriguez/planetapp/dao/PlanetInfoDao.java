package fr.mattrodriguez.planetapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.mattrodriguez.planetapp.model.PlanetInfo;

@Dao
public interface PlanetInfoDao {
    @Query("SELECT * FROM planet_info")
    List<PlanetInfo> getAll();

    @Query("SELECT * FROM planet_info where planetId = :idPlanet")
    PlanetInfo getPlanetInfoByid(int idPlanet);

    @Insert
    void insertAll(PlanetInfo... planetsInfos);


    @Query("DELETE FROM planet_info where  planetId= :idPlanet")
    void deleteOneByIdPlanet(int idPlanet);
}