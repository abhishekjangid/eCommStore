package com.fsd.assignment13;

public class Application {
    public static void main(String[] args) {
        User user1 = UserBuilder.getInstance("FIRST", "LAST")
                .age(30)
                .phone("1234567")
                .address("Fake address 1234")
                .build();

        System.out.println(user1);
    }
}
