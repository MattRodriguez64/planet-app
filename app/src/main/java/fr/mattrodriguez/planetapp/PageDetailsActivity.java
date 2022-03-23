package fr.mattrodriguez.planetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.mattrodriguez.planetapp.adapter.PlanetAdapter;
import fr.mattrodriguez.planetapp.api.PlanetAPI;
import fr.mattrodriguez.planetapp.model.Planet;
import fr.mattrodriguez.planetapp.model.PlanetInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageDetailsActivity extends AppCompatActivity {

    TextView planetName;
    TextView planetDistance;
    TextView planetDescription;
    TextView planetSeeMore;
    ImageView planetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_details);
        
        planetName = (TextView) findViewById(R.id.planet_title_detail);
        planetDescription = (TextView) findViewById(R.id.planet_description_detail);
        planetSeeMore = (TextView) findViewById(R.id.planet_see_more);
        planetImage = (ImageView) findViewById(R.id.planet_image_detail);

        String tempPlanetIdClicked = getIntent().getStringExtra("PlanetClickedId");
        Log.d("LOG", tempPlanetIdClicked);

        PlanetApplication planetApplication = ( (PlanetApplication) getApplicationContext() );
        PlanetAPI api = planetApplication.getRetrofit().create(PlanetAPI.class);
        int id = Integer.valueOf(tempPlanetIdClicked);
        Call<List<PlanetInfo>> planetsCallApi = api.getPlanetInfo(id);


        planetsCallApi.enqueue(new Callback<List<PlanetInfo>>() {
            @Override
            public void onResponse(Call<List<PlanetInfo>> call, Response<List<PlanetInfo>> response) {
                if (planetApplication.getDb().planetInfoDao().getPlanetInfoByid(id)!=null) {
                    planetApplication.getDb().planetInfoDao().deleteOneByIdPlanet(id);
                }
                if (response.isSuccessful()) {
                    planetApplication.getDb().planetInfoDao().insertAll(response.body().get(0));
                    planetName.setText(response.body().get(0).getName());
                    //planetDistance.setText(0);
                    planetDescription.setText(response.body().get(0).getDescription());
                    planetSeeMore.setText("Voir Plus");
                    planetSeeMore.setOnClickListener(view -> {
                        String url = response.body().get(0).getSeemore();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    });
                    Picasso.get().load(response.body().get(0).getLogo()).into(planetImage);

                } else {
                    Log.d("Yo", "Boo!");
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<PlanetInfo>> call, Throwable t) {
                Log.d("Yo", "Errror!");
                if (planetApplication.getDb().planetInfoDao().getPlanetInfoByid(id)!=null) {
                    PlanetInfo pl = planetApplication.getDb().planetInfoDao().getPlanetInfoByid(id);
                    planetName.setText(pl.getName());
                    //planetDistance.setText(0);
                    planetDescription.setText(pl.getDescription());
                    //planetSeeMore.setText(pl.getSeemore());
                    planetSeeMore.setText("Voir Plus");
                    planetSeeMore.setOnClickListener(view -> {
                        String url = pl.getSeemore();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    });
                    Picasso.get().load(pl.getLogo()).into(planetImage);
                }else {
                    Toast.makeText(getApplicationContext(),"pas de réseau", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}


















