package com.example.bbkiel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    public int id;
    public String phase;
    public Timestamp createdAt;
    public String slug;
    public String name;
    @SerializedName("investments")
    @Expose
    public ArrayList<SubProject> investments;

    public Project(int id, String phase, Timestamp createdAt, String slug, String name, ArrayList<SubProject> investments) {
        this.id = id;
        this.phase = phase;
        this.createdAt = createdAt;
        this.slug = slug;
        this.name = name;
        this.investments = investments;
    }

    public Project(Project response){
        this.id = response.id;
        this.phase = response.getPhase();
        this.createdAt = response.getCreatedAt();
        this.slug = response.getSlug();
        this.name = response.getName();
        this.investments = response.getInvestments();
    }
    public ArrayList<SubProject> getInvestments() {
        return investments;
    }

    public void setInvestments(ArrayList<SubProject> investments) {
        this.investments = investments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}