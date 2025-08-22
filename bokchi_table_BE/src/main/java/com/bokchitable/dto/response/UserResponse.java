package com.bokchitable.dto.response;

import com.bokchitable.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

public class UserResponse {
    @JsonProperty("user_id")
    private Long userId;
    private String email;
    private String phone;
    @JsonProperty("created_at")
    private Instant createdAt;

    public UserResponse() {}

    public static UserResponse from(User u){
        UserResponse r = new UserResponse();
        r.userId = u.getUserId();
        r.email = u.getEmail();
        r.phone = u.getPhone();
        r.createdAt = u.getCreatedAt().toInstant();
        return r;
    }

    // getters
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Instant getCreatedAt() { return createdAt; }
}