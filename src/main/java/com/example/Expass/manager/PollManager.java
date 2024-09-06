package com.example.Expass.manager;

import com.example.Expass.model.Poll;
import com.example.Expass.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component

public class PollManager {
    private final Map<Integer, Poll> polls = new HashMap<>();
    private final Map<Integer, User> users = new HashMap<>();

    public PollManager() {

    }

    // Methods to manage Polls
    public void addPoll(Poll poll) {
        polls.put(poll.getPollID(), poll);
    }

    public Poll getPoll(Poll poll) {
        return polls.get(poll.getPollID());
    }

    public void removePoll(Poll poll) {
        polls.remove(poll.getPollID());
    }

    public boolean pollExists(Poll poll) {
        return polls.containsKey(poll.getPollID());
    }

    // Methods to manage Users
    public void addUser(User user) {
        users.put(user.getUserID(), user);
    }

    public User getUser(User user) {
        return users.get(user.getUserID());
    }

    public void removeUser(User user) {
        users.remove(user.getUserID());
    }

    public boolean userExists(User user) {
        return users.containsKey(user.getUserID());
    }

    // Get all Polls and Users
    public Map<Integer, Poll> getAllPolls() {
        return polls;
    }

    public Map<Integer, User> getAllUsers() {
        return users;
    }
}