package com.example.bbkiel.model;

import android.location.Address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Adresslocation {
    public int place_id;
    public String licence;
    public String osm_type;
    public int osm_id;
    public double lat;
    public double lon;
    public int place_rank;
    public String category;
    public String type;
    public double importance;
    public String addresstype;
    public String name;
    public String display_name;
    @SerializedName("address")
    @Expose
    public subAdress address;
    @SerializedName("boundingbox")
    @Expose
    public ArrayList<String> boundingbox;

    public Adresslocation(int place_id, String licence, String osm_type, int osm_id, double lat, double lon, int place_rank, String category, String type, double importance, String addresstype, String name, String display_name, subAdress address, ArrayList<String> boundingbox) {
        this.place_id = place_id;
        this.licence = licence;
        this.osm_type = osm_type;
        this.osm_id = osm_id;
        this.lat = lat;
        this.lon = lon;
        this.place_rank = place_rank;
        this.category = category;
        this.type = type;
        this.importance = importance;
        this.addresstype = addresstype;
        this.name = name;
        this.display_name = display_name;
        this.address = address;
        this.boundingbox = boundingbox;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getOsm_type() {
        return osm_type;
    }

    public void setOsm_type(String osm_type) {
        this.osm_type = osm_type;
    }

    public int getOsm_id() {
        return osm_id;
    }

    public void setOsm_id(int osm_id) {
        this.osm_id = osm_id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getPlace_rank() {
        return place_rank;
    }

    public void setPlace_rank(int place_rank) {
        this.place_rank = place_rank;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public subAdress getAddress() {
        return address;
    }

    public void setAddress(subAdress address) {
        this.address = address;
    }

    public ArrayList<String> getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(ArrayList<String> boundingbox) {
        this.boundingbox = boundingbox;
    }
}