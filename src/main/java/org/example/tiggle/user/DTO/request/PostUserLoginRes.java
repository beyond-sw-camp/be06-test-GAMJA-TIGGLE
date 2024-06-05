package org.example.tiggle.user.DTO.request;

public class PostUserLoginRes {
    Integer userId;
    String id;
    String name;

    public PostUserLoginRes(Integer userId, String id, String name) {
        this.userId = userId;
        this.id = id;
        this.name = name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

