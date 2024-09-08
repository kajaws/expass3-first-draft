package com.example.Expass.manager;

import com.example.Expass.model.Poll;
import com.example.Expass.model.User;
import com.example.Expass.model.Vote;
import com.example.Expass.model.VoteOption;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component

public class PollManager {
    private Map<Long, User> users = new HashMap<>();
    private Map<Long, Poll> polls = new HashMap<>();
    private Map<Long, Vote> votes = new HashMap<>();
    private Map<Long, VoteOption> voteOptions = new HashMap<>();

    // Methods to manage Users
    public void addUser(Long userID, User user) {
        users.put(userID, user);
    }

    public User getUser(Long userID) {
        return users.get(userID);
    }

    public void removeUser(Long userID) {
        users.remove(userID);
    }

    // Methods to manage Polls
    public void addPoll(Long pollId, Poll poll) {
        polls.put(pollId, poll);
    }

    public Poll getPoll(Long pollId){
        return polls.get(pollId);
    }

    public void removePoll(Long pollId) {
        polls.remove(pollId);
        votes.entrySet().removeIf(entry -> entry.getValue().getPollId().equals(pollId));
    }

    // Methods to manage Votes
    public void addVote(Long voteId, Vote vote) {
        votes.put(voteId, vote);
    }

    public Vote getVote(Long voteId) {
        return votes.get(voteId);
    }

    public void removeVote(Long voteId) {
        votes.remove(voteId);
    }

    // Methods to manage VoteOptions
    public void addVoteOption(Long voteOptionId, VoteOption voteOption) {
        voteOptions.put(voteOptionId, voteOption);
    }

    public VoteOption getVoteOption(Long voteOptionId) {
        return voteOptions.get(voteOptionId);
    }

    public void removeVoteOption(Long voteOptionId) {
        voteOptions.remove(voteOptionId);
    }

    // Methods to get all Users, Polls, VoteOptions, and Votes
    public Collection<User> getUsers() {
        return users.values();
    }

    public Collection<Poll> getPolls() {
        return polls.values();
    }

    public Collection<VoteOption> getVoteOptions() {
        return voteOptions.values();
    }

    public Collection<Vote> getVotes() {
        return votes.values();
    }

    // Methods to check if a User or Poll exists
    public boolean isUserExist(Long userId) {
        return users.containsKey(userId);
    }

    public boolean isPollExist(Long pollId) {
        return polls.containsKey(pollId);
    }
}