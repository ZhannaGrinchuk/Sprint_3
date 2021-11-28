package com.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierWithLoginAndPassword {

    public final String login;
    public final String password;


    public CourierWithLoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public static CourierWithLoginAndPassword getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);

        return new CourierWithLoginAndPassword(login, password);
    }

}
