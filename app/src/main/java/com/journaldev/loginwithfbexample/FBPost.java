package com.journaldev.loginwithfbexample;

public class FBPost {
    String created_time;
    String message;
    String id;

    public FBPost(String created_time, String message, String id) {
        this.created_time = created_time;
        this.message = message;
        this.id = id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
