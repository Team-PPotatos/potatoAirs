package org.example.domain;

public class User{

    private String id;
    private String pwd;
    private String name;
    private int wallet;
    private boolean isMember;
    private boolean isLogin;

    public User(String id, String pwd, String name, int wallet){
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.wallet = wallet;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        this.isMember = member;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
    }


}