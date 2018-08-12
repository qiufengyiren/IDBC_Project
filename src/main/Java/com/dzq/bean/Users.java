package com.dzq.bean;

public class Users {
    private Integer id;//用户id
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Users(){

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", UserType=" + UserType +
                '}';
    }


    public Users(Integer id, String userName, String password, int userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        UserType = userType;
    }
}
