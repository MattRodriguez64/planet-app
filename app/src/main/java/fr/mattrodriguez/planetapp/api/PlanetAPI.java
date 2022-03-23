package fr.mattrodriguez.planetapp.api;

import java.util.List;

import fr.mattrodriguez.planetapp.model.Planet;
import fr.mattrodriguez.planetapp.model.PlanetInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface PlanetAPI {
    @GET("planetapi/planets")
    Call<List<Planet>> getPlanets();

    @GET("planetapi/planets/{idplanet}/infos")
    Call<List<PlanetInfo>> getPlanetInfo(@Path("idplanet") int id);
}
