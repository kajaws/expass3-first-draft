package com.example.Expass.model;

public class VoteOption {
    private String caption;
    private int presentationOrder;
    private String voteOptionId;

    public VoteOption() {}

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public String getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(String voteOptionId) {
        this.voteOptionId = voteOptionId;
    }
}
