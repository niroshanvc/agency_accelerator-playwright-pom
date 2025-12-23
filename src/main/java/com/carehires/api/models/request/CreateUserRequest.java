package com.carehires.api.models.request;

public class CreateUserRequest {
    private String name;
    private String email;
    private String job;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String email, String job) {
        this.name = name;
        this.email = email;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
