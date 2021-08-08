package com.fsd.assignment13;

public class UserBuilder
{
    public final String firstName;
    public final String lastName;
    public int age;
    public String phone;
    public String address;

    private static UserBuilder instance;

    private UserBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static UserBuilder getInstance(String firstName, String lastName){
        if (instance==null){
            instance=new UserBuilder(firstName, lastName);
        }
        return instance;
    }

    public User build() {
        User user =  new User(this);
        return user;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder address(String address) {
        this.address = address;
        return this;
    }
}
