package com.example.Expass.controller;

import com.example.Expass.model.Poll;
import com.example.Expass.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Expass.manager.PollManager;

import java.util.Collection;
import java.util.List;

@RestController
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
    public Vote getVote(@PathVariable Long voteId) {
        return pollManager.getVote(voteId);
    }

    // Create a new vote
    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
       if(pollManager.isUserExist(vote.getUserId()) && pollManager.isPollExist(vote.getPollId())) {
           pollManager.addVote(vote.getVoteId(), vote);
              return vote;
       }
       else {
           throw new IllegalArgumentException("User or Poll does not exist");
       }
    }

    // Update an existing vote
    @PutMapping("/{voteId}")
    public Vote updateVote(@PathVariable Long voteId, @RequestBody Vote updatedVote) {
        Vote existingVote = pollManager.getVote(voteId);
        if (existingVote != null && existingVote.getUserId().equals(updatedVote.getUserId())) {
            pollManager.addVote(voteId, updatedVote);
            return updatedVote;
        }
        else {
            throw new IllegalArgumentException("Vote does not exist or user does not match");
        }
    }

    // Delete a vote
    @DeleteMapping("/{voteId}")
    public void deleteVote(@PathVariable Long voteId) {
        pollManager.removeVote(voteId);
    }

    // Get votes by poll id
    @GetMapping("/poll/{pollId}")
    public List<Vote> getVotesByPollId(@PathVariable Long pollId) {
        return pollManager.getVotes().stream().filter(vote -> vote.getPollId().equals(pollId)).toList();
    }

}
