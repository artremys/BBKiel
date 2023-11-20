package com.example.bbkiel.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    private int id;
    private String locale;
    private String username;
    private boolean isBanned;
    @SerializedName("votes")
    @Expose
    public ArrayList<Votes> votes;
    @SerializedName("follows")
    @Expose
    public ArrayList<Follows> follows;

    public User(int id, String locale, String username, boolean isBanned, ArrayList<com.example.bbkiel.model.Votes> votes, ArrayList<Follows> follows) {
        this.id = id;
        this.locale = locale;
        this.username = username;
        this.isBanned = isBanned;
        this.votes = votes;
        this.follows = follows;
    }

    public User(User response) {
        this.id = response.getId();
        this.locale = response.getLocale();
        this.username = response.getUsername();
        this.votes = response.getVotes();
        this.follows = response.getFollows();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public ArrayList<com.example.bbkiel.model.Votes> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<com.example.bbkiel.model.Votes> votes) {
        votes = votes;
    }

    public ArrayList<Follows> getFollows() {
        return follows;
    }

    public void setFollows(ArrayList<Follows> follows) {
        this.follows = follows;
    }
}
