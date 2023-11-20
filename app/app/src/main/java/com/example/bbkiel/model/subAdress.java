package com.example.bbkiel.model;

public class subAdress {

    public String road;
    public String town;
    public String municipality;
    public String county;
    public String state;
    public String iSO31662Lvl4;
    public String postcode;
    public String country;
    public String country_code;

    public subAdress(String road, String town, String municipality, String county, String state, String iSO31662Lvl4, String postcode, String country, String country_code) {
        this.road = road;
        this.town = town;
        this.municipality = municipality;
        this.county = county;
        this.state = state;
        this.iSO31662Lvl4 = iSO31662Lvl4;
        this.postcode = postcode;
        this.country = country;
        this.country_code = country_code;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getiSO31662Lvl4() {
        return iSO31662Lvl4;
    }

    public void setiSO31662Lvl4(String iSO31662Lvl4) {
        this.iSO31662Lvl4 = iSO31662Lvl4;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}
