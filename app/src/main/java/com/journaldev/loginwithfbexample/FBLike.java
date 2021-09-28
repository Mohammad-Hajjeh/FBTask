package com.journaldev.loginwithfbexample;

public class FBLike {
    String name;
    String id;
    String created_time;

    public FBLike(String name, String id, String created_time) {
        this.name = name;
        this.id = id;
        this.created_time = created_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
