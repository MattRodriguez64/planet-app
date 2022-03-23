package fr.mattrodriguez.planetapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planet")
public class Planet {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "distance")
    private int distance;

    @ColumnInfo(name = "logo")
    private String logo;

    public Planet(int id, String nom, int distance, String logo) {
        this.id = id;
        this.nom = nom;
        this.distance = distance;
        this.logo = logo;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
