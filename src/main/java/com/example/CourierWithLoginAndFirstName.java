package com.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierWithLoginAndFirstName {

    public final String login;
    public final String firstName;

    public CourierWithLoginAndFirstName(String firstName, String login) {
        this.login = login;
        this.firstName = firstName;
    }

    public static CourierWithLoginAndFirstName getRandom() {
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        final String login = RandomStringUtils.randomAlphabetic(10);

        return new CourierWithLoginAndFirstName(login, firstName);
    }

}