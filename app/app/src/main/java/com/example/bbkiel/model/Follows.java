package com.example.bbkiel.model;

import java.sql.Timestamp;

public class Follows {
    private int id;
    private String followableType;
    private int followableId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Follows(int id, String followableType, int followableId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.followableType = followableType;
        this.followableId = followableId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFollowableType() {
        return followableType;
    }

    public void setFollowableType(String followableType) {
        this.followableType = followableType;
    }

    public int getFollowableId() {
        return followableId;
    }

    public void setFollowableId(int followableId) {
        this.followableId = followableId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
