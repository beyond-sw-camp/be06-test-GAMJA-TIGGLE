package org.example.tiggle.user.DTO.request;

public class PostUserLoginReq {
    String id;
    String password;


    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
