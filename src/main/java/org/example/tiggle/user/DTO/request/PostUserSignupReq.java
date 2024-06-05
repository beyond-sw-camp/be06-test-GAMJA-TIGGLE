package org.example.tiggle.user.DTO.request;

public class PostUserSignupReq {
    String id;
    String password;
    String name;
    String email;
    String createdAt;
    String loginType;

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLoginType() {
        return loginType;
    }



    public void setEmail(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* 값이 안 담기는 거 같아서 null나올 땐 toString로 하기!
    @Override
    public String toString() {
        return "PostUserReq{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }*/
}
