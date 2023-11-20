package com.example.bbkiel.model;

import java.sql.Timestamp;

public class Comment{


        private int id;
        private String ancestry;
        private int cachedVotesDown;
        private int cachedVotesTotal;
        private int cachedVotesUp;
        private int commentableId;
        private String commentableType;
        private Timestamp createdAt;
        private int userId;
        private String body;
        private String userName;


    public Comment(int id, String ancestry, int cachedVotesDown, int cachedVotesTotal, int cachedVotesUp, int commentableId, String commentableType, Timestamp createdAt, int userId, String body, String userName) {
        this.id = id;
        this.ancestry = ancestry;
        this.cachedVotesDown = cachedVotesDown;
        this.cachedVotesTotal = cachedVotesTotal;
        this.cachedVotesUp = cachedVotesUp;
        this.commentableId = commentableId;
        this.commentableType = commentableType;
        this.createdAt = createdAt;
        this.userId = userId;
        this.body = body;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public int getCachedVotesDown() {
        return cachedVotesDown;
    }

    public void setCachedVotesDown(int cachedVotesDown) {
        this.cachedVotesDown = cachedVotesDown;
    }

    public int getCachedVotesTotal() {
        return cachedVotesTotal;
    }

    public void setCachedVotesTotal(int cachedVotesTotal) {
        this.cachedVotesTotal = cachedVotesTotal;
    }

    public int getCachedVotesUp() {
        return cachedVotesUp;
    }

    public void setCachedVotesUp(int cachedVotesUp) {
        this.cachedVotesUp = cachedVotesUp;
    }

    public int getCommentableId() {
        return commentableId;
    }

    public void setCommentableId(int commentableId) {
        this.commentableId = commentableId;
    }

    public String getCommentableType() {
        return commentableType;
    }

    public void setCommentableType(String commentableType) {
        this.commentableType = commentableType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getUserIdent() {
        return userId;
    }

    public void setUserIdent(int userIdent) {
        this.userId = userIdent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
