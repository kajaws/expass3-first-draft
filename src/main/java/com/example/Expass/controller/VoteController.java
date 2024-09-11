package com.example.Expass.controller;

import com.example.Expass.model.Poll;
import com.example.Expass.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.Expass.manager.PollManager;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    // Fetch all votes
    @GetMapping
    public Collection<Vote> getAllVotes() {
        return pollManager.getVotes();
    }

    // Fetch a single vote by ID
    @GetMapping("/{voteId}")
    public Vote getVote(@PathVariable String voteId) {
        Vote vote = pollManager.getVote(voteId);
        if (vote == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vote not found");
        }
        return vote;
    }

    // Create a new vote
    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        String username = vote.getUsername();
        String question = vote.getQuestion(); // Use question from the request

        // Find the poll by question
        Poll poll = pollManager.getPollByQuestion(question);

        if (poll != null) {
            System.out.println("Poll found: " + poll);
        } else {
            System.err.println("Poll not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found");
        }

        if (pollManager.isUserExistByUsername(username)) {
            System.out.println("User exists: " + username);

            // Check if the vote already exists for this user and poll
            Vote existingVote = pollManager.getVoteByUserAndPoll(username, poll.getPollId());
            if (existingVote != null) {
                System.out.println("Updating existing vote");
                // Update existing vote
                existingVote.setVoteOptionId(vote.getVoteOptionId());
                existingVote.setPublishedAt(vote.getPublishedAt());
                pollManager.updateVote(existingVote.getVoteId(), existingVote);
                return existingVote;
            } else {
                System.out.println("Creating new vote");
                // Create a new vote
                vote.setPollId(poll.getPollId()); // Set the pollId from the found poll
                pollManager.addVote(vote.getVoteId(), vote);
                return vote;
            }
        } else {
            System.err.println("User not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // Update an existing vote
    @PutMapping("/{voteId}")
    public Vote updateVote(@PathVariable String voteId, @RequestBody Vote updatedVote) {
        Vote existingVote = pollManager.getVote(voteId);
        if (existingVote != null && existingVote.getUsername().equals(updatedVote.getUsername())) {
            pollManager.addVote(voteId, updatedVote);
            return updatedVote;
        }
        else {
            throw new IllegalArgumentException("Vote does not exist or user does not match");
        }
    }

    // Delete a vote
    @DeleteMapping("/{voteId}")
    public void deleteVote(@PathVariable String voteId) {
        pollManager.removeVote(voteId);
    }

    // Get votes by poll id
    @GetMapping("/poll/{pollId}")
    public List<Vote> getVotesByPollId(@PathVariable String pollId) {
        return pollManager.getVotesByPoll(pollId);
    }

}
