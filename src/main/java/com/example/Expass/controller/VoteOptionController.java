package com.example.Expass.controller;

import com.example.Expass.manager.PollManager;
import com.example.Expass.model.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/voteOptions")
public class VoteOptionController {

    @Autowired
    private PollManager pollManager;

    // Fetch all vote options
    @GetMapping
    public Collection<VoteOption> getAllVoteOptions() {
        return pollManager.getVoteOptions();
    }

    // Fetch a single vote option by ID
    @GetMapping("/{voteOptionId}")
    public VoteOption getVoteOption(@PathVariable String voteOptionId) {
        return pollManager.getVoteOption(voteOptionId);
    }

    // Create a new vote option
    @PostMapping
    public VoteOption createVoteOption(@RequestBody VoteOption voteOption) {
        pollManager.addVoteOption(voteOption.getVoteOptionId(), voteOption);
        return voteOption;
    }

    // Update an existing vote option
    @PutMapping("/{voteOptionId}")
    public VoteOption updateVoteOption(@PathVariable String voteOptionId, @RequestBody VoteOption updatedVoteOption) {
        pollManager.addVoteOption(voteOptionId, updatedVoteOption);
        return updatedVoteOption;
    }

    // Delete a vote option
    @DeleteMapping("/{voteOptionId}")
    public void deleteVoteOption(@PathVariable String voteOptionId) {
        pollManager.removeVoteOption(voteOptionId);
    }

}
