package by.issoft.sample.data;

import by.issoft.sample.domain.Age;
import by.issoft.sample.domain.User;

public class UserTestSamples {

    public static User anyValidUser() {
        return validUser("vpupkin");
    }

    public static User validUser(String userName) {

        return null;
    }

    public static User anyUser() {
        return anyValidUser();
    }
}
