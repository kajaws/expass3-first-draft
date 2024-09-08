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
public class PollAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPollAppScenarios() throws Exception {
        // 1. Create User 1
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"username\": \"user1\", \"email\": \"user1@example.com\" }"))
                .andExpect(status().isOk());

        // 2. List all users (should contain User 1)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // 3. Create User 2
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 2, \"username\": \"user2\", \"email\": \"user2@example.com\" }"))
                .andExpect(status().isOk());

        // 4. List all users (should contain User 1 and User 2)
        /*mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));*/

        // 5. User 1 creates a new poll
        mockMvc.perform(post("/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"question\": \"What is your favorite color?\"," +
                                "\"publishedAt\": \"2023-01-01T00:00:00Z\", \"validUntil\": \"2023-12-31T23:59:59Z\"," +
                                "\"voteOption\": [{ \"id\": 1, \"caption\": \"Red\", \"presentationOrder\": 1 }, " +
                                "{ \"id\": 2, \"caption\": \"Blue\", \"presentationOrder\": 2 }] }"))
                .andExpect(status().isOk());

        // 6. List all polls (should contain the new poll)
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        // 7. User 2 votes on the poll
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"publishedAt\": \"2023-01-02T12:00:00Z\", \"selectedOption\": { \"id\": 1 } }"))
                .andExpect(status().isOk());

        // 8. User 2 changes his vote
        mockMvc.perform(put("/votes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"publishedAt\": \"2023-01-02T12:30:00Z\", \"selectedOption\": { \"id\": 2 } }"))
                .andExpect(status().isOk());

        // 9. List all votes (should show the most recent vote for User 2)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].selectedOption.id").value(2));

        // 10. Delete the poll
        mockMvc.perform(delete("/polls/1"))
                .andExpect(status().isNoContent());

        // 11. List all votes (should be empty)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}

