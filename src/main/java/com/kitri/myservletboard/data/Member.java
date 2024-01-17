package com.kitri.myservletboard.data;

public class Member {
    private String login_id;
    private String password;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public Member() {
    }

    public Member(Long id, String login_id, String password, String name, String email) {
        this.id = id;
        this.login_id = login_id;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
