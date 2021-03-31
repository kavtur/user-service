package by.issoft.sample.service;

import by.issoft.sample.domain.User;

public class UserValidator {
    public boolean validateForCreation(User user) {
        if (user.getUserName() == null) {
            return false;
        }

        if (user.getFirstName() == null) {
            return false;
        }

        if (user.getLastName() == null) {
            return false;
        }

        return true;
    }
}
