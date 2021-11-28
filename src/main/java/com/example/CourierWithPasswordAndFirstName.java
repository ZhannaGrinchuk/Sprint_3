package com.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierWithPasswordAndFirstName {

    public final String password;
    public final String firstName;

    public CourierWithPasswordAndFirstName(String firstName, String password) {
        this.password = password;
        this.firstName = firstName;
    }

    public static CourierWithPasswordAndFirstName getRandom() {
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);

        return new CourierWithPasswordAndFirstName(password, firstName);
    }

}