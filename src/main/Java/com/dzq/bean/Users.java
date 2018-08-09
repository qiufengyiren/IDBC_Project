package com.dzq.bean;

public class Users {
    private String userName;//用户名
    private String password;//密码
    private  int UserType;//类型

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Users(){

    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password=" + password +
                ", UserType=" + UserType +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(String userName, String password, int userType) {
        this.userName = userName;
        this.password = password;
        UserType = userType;
    }
}
