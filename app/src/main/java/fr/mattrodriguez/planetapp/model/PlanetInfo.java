package fr.mattrodriguez.planetapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planet_info")
public class PlanetInfo {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    private int planetId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "logo")
    private String logo;

    @ColumnInfo(name = "seemore")
    private String seemore;

    public PlanetInfo(int id, int planetId, String name, String description, String logo, String seemore) {
        this.id = id;
        this.planetId = planetId;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.seemore = seemore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanetId() {
        return planetId;
    }

    public void setPlanetId(int planetId) {
        this.planetId = planetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSeemore() {
        return seemore;
    }

    public void setSeemore(String seemore) {
        this.seemore = seemore;
    }
}
