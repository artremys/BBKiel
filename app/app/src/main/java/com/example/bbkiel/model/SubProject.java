package com.example.bbkiel.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SubProject implements Serializable {
    public int budgetInvestmentId;
    public Timestamp createdAt;
    public int cachedVotesUp;
    public String organizationName;
    public String location;
    public String title;
    public String description;
    public double latitude;
    public double longitude;
    public int commentsCount;



    public SubProject(int id, Timestamp createdAt, int cachedVotesUp,String organizationName, String location, String title, String description, double latitude, double longitude, int commentsCount) {
        this.budgetInvestmentId = id;
        this.createdAt = createdAt;
        this.cachedVotesUp = cachedVotesUp;
        this.organizationName = organizationName;
        this.location = location;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.commentsCount = commentsCount;
    }

    public SubProject(SubProject response) {
        this.budgetInvestmentId = response.getBudgetInvestmentId();
        this.cachedVotesUp = response.getCachedVotesUp();
        this.createdAt = response.getCreatedAt();
        this.organizationName = response.getOrganizationName();
        this.location = response.getLocation();
        this.title = response.getTitle();
        this.description = response.description;
        this.latitude = response.getLatitude();
        this.longitude = response.getLongitude();
        this.commentsCount = response.getCommentsCount();
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getBudgetInvestmentId() {
        return budgetInvestmentId;
    }

    public void setBudgetInvestmentId(int budgetInvestmentId) {
        this.budgetInvestmentId = budgetInvestmentId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getCachedVotesUp() {
        return cachedVotesUp;
    }

    public void setCachedVotesUp(int cachedVotesUp) {
        this.cachedVotesUp = cachedVotesUp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
