package com.example.Expass.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PollAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testScenarioFlow() throws Exception {
        // Create User 1
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 1, \"username\": \"user1\", \"email\": \"user1@example.com\" }"))
                .andExpect(status().isOk());

        // List all users (should show User 1)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"userId\": 1, \"username\": \"user1\", \"email\": \"user1@example.com\" }]"));

        // Create User 2
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"userId\": 2, \"username\": \"user2\", \"email\": \"user2@example.com\" }"))
                .andExpect(status().isOk());

        // List all users (should show User 1 and User 2)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"userId\": 1, \"username\": \"user1\", \"email\": \"user1@example.com\" }, " +
                                         "{ \"userId\": 2, \"username\": \"user2\", \"email\": \"user2@example.com\" }]"));

        // User 1 creates a new poll
        mockMvc.perform(post("/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"pollId\": 1, \"userId\": 1, \"question\": \"What is your favorite color?\", \"publishedAt\": \"2024-09-03T12:00:00Z\", \"validUntil\": \"2024-09-10T12:00:00Z\", " +
                                 "\"voteOption\": [{ \"voteOptionId\": 1, \"caption\": \"Red\", \"presentationOrder\": 1 }, { \"voteOptionId\": 2, \"caption\": \"Green\", \"presentationOrder\": 2 }, { \"voteOptionId\": 3, \"caption\": \"Blue\", \"presentationOrder\": 3 }] }"))
                .andExpect(status().isOk());

        // List polls (should show the newly created poll)
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"pollId\": 1, \"userId\": 1, \"question\": \"What is your favorite color?\", " +
                                         "\"voteOption\": [{ \"voteOptionId\": 1, \"caption\": \"Red\", \"presentationOrder\": 1 }, { \"voteOptionId\": 2, \"caption\": \"Green\", \"presentationOrder\": 2 }, { \"voteOptionId\": 3, \"caption\": \"Blue\", \"presentationOrder\": 3 }] }]"));

        // User 2 votes on the poll
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"voteId\": 1, \"userId\": 2, \"pollId\": 1, \"publishedAt\": \"2024-09-03T12:00:00Z\", \"voteOptionId\": 3}"))
                .andExpect(status().isOk());

        // User 2 changes his vote
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"voteId\": 1, \"userId\": 2, \"pollId\": 1, \"publishedAt\": \"2024-09-03T12:00:00Z\", \"voteOptionId\": 1}"))
                .andExpect(status().isOk());

        // List all votes (should show the most recent vote for User 2)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"voteId\": 1, \"userId\": 2, \"pollId\": 1, \"publishedAt\": \"2024-09-03T12:00:00Z\", \"voteOptionId\": 1 }]"));

        // Delete the poll
        mockMvc.perform(delete("/polls/1"))
                .andExpect(status().isOk());

        // List votes (should be empty after poll deletion)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}