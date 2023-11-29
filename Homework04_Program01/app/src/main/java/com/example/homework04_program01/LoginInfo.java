package com.example.homework04_program01;

public class LoginInfo
{
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoginInfo.user = user;
    }
}
