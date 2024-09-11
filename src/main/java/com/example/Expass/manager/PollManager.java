package com.example.Expass.manager;

import com.example.Expass.model.Poll;
import com.example.Expass.model.User;
import com.example.Expass.model.Vote;
import com.example.Expass.model.VoteOption;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component

public class PollManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Poll> polls = new HashMap<>();
    private Map<String, Vote> votes = new HashMap<>();
    private Map<String, VoteOption> voteOptions = new HashMap<>();

    // Methods to manage Users
    public void addUser(String userID, User user) {
        users.put(userID, user);
    }

    public User getUser(String userID) {
        return users.get(userID);
    }

    public User getUserByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void removeUser(String userID) {
        users.remove(userID);
    }

    // Methods to manage Polls
    public void addPoll(String pollId, Poll poll) {
        polls.put(pollId, poll);
    }

    public Poll getPoll(String pollId){
        return polls.get(pollId);
    }

    public void removePoll(String pollId) {
        polls.remove(pollId);
        votes.entrySet().removeIf(entry -> entry.getValue().getPollId().equals(pollId));
    }

    public Poll getPollByQuestion(String question) {
        return polls.values().stream()
                .filter(poll -> poll.getQuestion().equals(question))
                .findFirst()
                .orElse(null);
    }

    // Methods to manage Votes
    public void addVote(String voteId, Vote vote) {
        votes.put(voteId, vote);
    }

    public Vote getVote(String voteId) {
        return votes.get(voteId);
    }

    public void removeVote(String voteId) {
        votes.remove(voteId);
    }

    // Methods to manage VoteOptions
    public void addVoteOption(String voteOptionId, VoteOption voteOption) {
        voteOptions.put(voteOptionId, voteOption);
    }

    public VoteOption getVoteOption(String voteOptionId) {
        return voteOptions.get(voteOptionId);
    }

    public void removeVoteOption(String voteOptionId) {
        voteOptions.remove(voteOptionId);
    }

    public void updateVote(String voteId, Vote updatedVote) {
        votes.put(voteId, updatedVote);
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
    public boolean isUserExistByUsername(String username) {
        return users.values().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public boolean isPollExist(String pollId) {
        return polls.containsKey(pollId);
    }

    public List<Vote> getVotesByPoll(String pollId) {
        return votes.values().stream()
                .filter(vote -> vote.getPollId().equals(pollId))
                .collect(Collectors.toList());
    }

    public Vote getVoteByUserAndPoll(String username, String pollId) {
        for (Vote vote : votes.values()) {
            if (vote.getUsername().equals(username) && vote.getPollId().equals(pollId)) {
                return vote;
            }
        }
        return null;
    }


}