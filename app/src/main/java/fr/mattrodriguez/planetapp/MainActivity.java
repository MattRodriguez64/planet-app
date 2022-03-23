package fr.mattrodriguez.planetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.mattrodriguez.planetapp.adapter.PlanetAdapter;
import fr.mattrodriguez.planetapp.api.PlanetAPI;
import fr.mattrodriguez.planetapp.databinding.ActivityMainBinding;
import fr.mattrodriguez.planetapp.model.Planet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.collection_recycler_list);
        PlanetApplication planetApplication = (PlanetApplication) getApplicationContext();
        PlanetAPI api = planetApplication.getRetrofit().create(PlanetAPI.class);
        Call<List<Planet>> planetsCallApi = api.getPlanets();

        planetsCallApi.enqueue(new Callback<List<Planet>>() {
            @Override
            public void onResponse(Call<List<Planet>> call, Response<List<Planet>> response) {
                ArrayList<Planet> planets;
                Log.d("LIST", String.valueOf(response.body().size()));
                if(planetApplication.getDb().planetDao().getAll().size()>0){
                    planetApplication.getDb().planetDao().deleteAll();
                }
                planets = new ArrayList<Planet>(response.body());
                planetApplication.getDb().planetDao().insertAll(planets);
                PlanetAdapter planetAdapter = new PlanetAdapter(getApplicationContext(), planets);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(planetAdapter);
                planetAdapter.setOnItemClickListener(position -> {
                    String planetIdClicked = String.valueOf(planets.get(position).getId());
                    Intent intent = new Intent(getApplicationContext(), PageDetailsActivity.class);
                    Log.d("Log", "Planet " + planets.get(position).getNom() + " clicked");
                    intent.putExtra("PlanetClickedId", planetIdClicked);
                    startActivity(intent);
                });

            }

            @Override
            public void onFailure(Call<List<Planet>> call, Throwable t) {
                ArrayList<Planet> planets;
                if(planetApplication.getDb().planetDao().getAll().size()>0){
                    planets = new ArrayList<Planet>(planetApplication.getDb().planetDao().getAll());
                    PlanetAdapter planetAdapter = new PlanetAdapter(getApplicationContext(), planets);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(planetAdapter);
                    planetAdapter.setOnItemClickListener(position -> {
                        String planetIdClicked = String.valueOf(planets.get(position).getId());
                        Intent intent = new Intent(getApplicationContext(), PageDetailsActivity.class);
                        Log.d("Log", "Planet " + planets.get(position).getNom() + " clicked");
                        intent.putExtra("PlanetClickedId", planetIdClicked);
                        startActivity(intent);
                    });
                }else {
                    Toast.makeText(getApplicationContext(),"Pas de réseau",Toast.LENGTH_LONG).show();
                }
                Log.d("Yo", "Errror!");
            }

        });


        //LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);

    }
}





























