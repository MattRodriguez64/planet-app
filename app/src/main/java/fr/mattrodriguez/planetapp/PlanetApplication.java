package fr.mattrodriguez.planetapp;

import android.app.Application;

import androidx.room.Room;

import fr.mattrodriguez.planetapp.dao.PlanetDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetApplication  extends Application {
    PlanetDatabase db;
    Retrofit retrofit;

    public PlanetDatabase getDb() {
        return db;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(),
                PlanetDatabase.class, "planet_db").allowMainThreadQueries().build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory( GsonConverterFactory.create())
                .baseUrl("https://my-json-server.typicode.com/hamzabm/")
                .build();
    }
}