package com.example.Expass.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private List<VoteOption> voteOption;
    private String pollId;
    private String username;

    public Poll() {
        this.pollId = UUID.randomUUID().toString();
    }

    // Getters and Setters
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public String getPollId() {
        return pollId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<VoteOption> getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(List<VoteOption> voteOption) {
        this.voteOption = voteOption;
    }

}
