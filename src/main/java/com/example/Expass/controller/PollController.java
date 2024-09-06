package com.example.Expass.controller;

import com.example.Expass.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Expass.manager.PollManager;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        pollManager.addPoll(poll);
        return poll;
    }

    // Hent alle avstemninger
    @GetMapping
    public String listPolls() {
        return pollManager.getAllPolls().toString();
    }

    // Slett en avstemning basert på spørsmålet
    @DeleteMapping
    public String deletePoll(@RequestBody Poll poll) {
        pollManager.removePoll(poll);
        return "Poll deleted";
    }

}
