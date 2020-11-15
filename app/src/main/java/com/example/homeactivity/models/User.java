package com.example.homeactivity.models;

public class User {
    private String city;
    private String email;
    private Integer latitude;
    private Integer longitude;
    private long phone_number;
    private String u_id;
    private String username;

    public User(String city, String email, Integer latitude, Integer longitude, long phone_number, String u_id, String username) {
        this.city = city;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone_number = phone_number;
        this.u_id = u_id;
        this.username = username;
    }

    public User(){

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString(){
        return "User{" +
                "user_id='"+u_id+'\''+
                "email='"+email+'\''+
                "phone_number='"+phone_number+'\''+
                "username='"+username+'\''+
                "city='"+city+'\''+
                " latitude='"+ latitude+'\''+
                "longitude='"+longitude+'\''+
                '}';
    }
}
