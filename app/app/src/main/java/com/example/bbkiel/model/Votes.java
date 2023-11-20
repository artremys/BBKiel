package com.example.bbkiel.model;

import java.sql.Timestamp;

public class Votes {
    private int id;
    private String comment;
    private Timestamp createdAt;
    private String signatureId;
    private Timestamp updatedAt;
    private int votableId;
    private String votableType;
    private boolean voteFlag;
    private String voteScope;
    private String voteWeight;
    private String voterType;
    private String voteByuser;


    public Votes(int id, String comment, Timestamp createdAt, String signatureId, Timestamp updatedAt, int votableId, String votableType, boolean voteFlag, String voteScope, String voteWeight, String voterType, String voteByuser) {
        this.id = id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.signatureId = signatureId;
        this.updatedAt = updatedAt;
        this.votableId = votableId;
        this.votableType = votableType;
        this.voteFlag = voteFlag;
        this.voteScope = voteScope;
        this.voteWeight = voteWeight;
        this.voterType = voterType;
        this.voteByuser = voteByuser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVotableId() {
        return votableId;
    }

    public void setVotableId(int votableId) {
        this.votableId = votableId;
    }

    public String getVotableType() {
        return votableType;
    }

    public void setVotableType(String votableType) {
        this.votableType = votableType;
    }

    public boolean isVoteFlag() {
        return voteFlag;
    }

    public void setVoteFlag(boolean voteFlag) {
        this.voteFlag = voteFlag;
    }

    public String getVoteScope() {
        return voteScope;
    }

    public void setVoteScope(String voteScope) {
        this.voteScope = voteScope;
    }

    public String getVoteWeight() {
        return voteWeight;
    }

    public void setVoteWeight(String voteWeight) {
        this.voteWeight = voteWeight;
    }

    public String getVoterType() {
        return voterType;
    }

    public void setVoterType(String voterType) {
        this.voterType = voterType;
    }

    public String getVoteByuser() {
        return voteByuser;
    }

    public void setVoteByuser(String voteByuser) {
        this.voteByuser = voteByuser;
    }
}
