package com.myt.zonechat.Model;

public class Users {
    private String mail, password, token, nickname;

    public Users(String mail, String password, String token, String nickname) {
        this.mail = mail;
        this.password = password;
        this.token = token;
        this.nickname = nickname;
    }
}
