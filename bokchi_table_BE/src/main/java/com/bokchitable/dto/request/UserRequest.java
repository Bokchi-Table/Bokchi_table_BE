package com.bokchitable.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @Email(message="invalid_email")
    @NotBlank(message="email_required")
    private String email;

    @NotBlank(message="password_required")
    @Size(min=8, message="password_too_short")
    private String password;

    private String phone; // 선택

    public UserRequest() {}

    // getters/setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}