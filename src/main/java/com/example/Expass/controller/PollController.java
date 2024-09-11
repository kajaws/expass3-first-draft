package com.example.Expass.controller;

import com.example.Expass.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Expass.manager.PollManager;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;

    // Fetch all polls
    @GetMapping
    public Collection<Poll> getAllPolls() {
        return pollManager.getPolls();
    }

    // Fetch a single poll by ID
    @GetMapping("/{pollId}")
    public Poll getPoll(@PathVariable String pollId) {
        return pollManager.getPoll(pollId);
    }

    // Create a new poll
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        pollManager.addPoll(poll.getPollId(), poll);
        return poll;
    }

    // Update an existing poll
    @PutMapping("/{pollId}")
    public Poll updatePoll(@PathVariable String pollId, @RequestBody Poll updatedPoll) {
        pollManager.addPoll(pollId, updatedPoll);
        return updatedPoll;
    }

    // Delete a poll
    @DeleteMapping("/{pollId}")
    public ResponseEntity<String> deletePoll(@PathVariable String pollId) {
        pollManager.removePoll(pollId);
        return ResponseEntity.ok("Poll deleted successfully");
    }

    @GetMapping("/question/{question}")
    public Poll getPollByQuestion(@PathVariable String question) {
        Poll poll = pollManager.getPollByQuestion(question);
        if (poll != null) {
            return poll;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Poll not found");
        }
    }

}
