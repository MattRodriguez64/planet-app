package fr.mattrodriguez.planetapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fr.mattrodriguez.planetapp.R;
import fr.mattrodriguez.planetapp.model.Planet;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder> {

    private Context context;
    private ArrayList<Planet> planets;
    OnItemClickListener listener;


    public PlanetAdapter(Context ct, ArrayList<Planet> planets){
        this.context = ct;
        this.planets = planets;
    }

    @NonNull
    @Override
    public PlanetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.planet_item, parent, false);
        return new PlanetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetViewHolder holder, int position) {
        holder.planetName.setText(planets.get(position).getNom());
        holder.planetDistance.setText(String.valueOf(planets.get(position).getDistance()));
        Log.d("IMG URL", planets.get(position).getLogo());
        Picasso.get().load(planets.get(position).getLogo()).into(holder.planetImage);
        holder.setOnItemClickListener(this.listener);
        //holder.planetImage.setImageResource(R.drawable.planetlogo);

    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    public class PlanetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView planetName;
        TextView planetDistance;
        ImageView planetImage;
        private PlanetAdapter.OnItemClickListener listener;

        public PlanetViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.planet_name);
            planetDistance = itemView.findViewById(R.id.planet_distance);
            planetImage = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                this.listener.onItemClick(getAdapterPosition());

            }
        }

        public void setOnItemClickListener(PlanetAdapter.OnItemClickListener i) {
            this.listener = i;
        }
    }
}





















