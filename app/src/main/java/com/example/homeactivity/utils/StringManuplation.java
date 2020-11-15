package com.example.homeactivity.utils;

public class StringManuplation {
    public static String expandUsername(String username){
        return username.replace("."," ");

    }
    public static String condenseUsername(String username){
        return username.replace(" ",".");

    }
}
