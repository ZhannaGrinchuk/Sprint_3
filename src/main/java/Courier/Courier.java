package Courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private String login;
    private String password;
    private String firstName;

    public Courier() {

    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public Courier setLogin(String login) {
        this.login = login;
        return this;
    }

    public Courier setPassword(String password) {
        this.password = password;
        return this;
    }

    public Courier setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


    public static Courier getWithLoginOnly() {
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getWithPasswordOnly() {
        return new Courier().setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getWithLoginAndPassword() {
        return new Courier().setLogin(RandomStringUtils.randomAlphabetic(10)).setPassword(RandomStringUtils.randomAlphabetic(10));
    }
}