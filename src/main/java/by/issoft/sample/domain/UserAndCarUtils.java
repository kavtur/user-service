package by.issoft.sample.domain;

import java.time.LocalDate;

public class UserAndCarUtils {

    public static User[] findActiveUsersWithDateOfBirth(User[] users, LocalDate dateOfBirth) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    public static User[] findUsersWithDateOfBirthAndStatus(User[] users, LocalDate dateOfBirth, UserStatus status) {
       return findActiveUsersWithDateOfBirth(users, dateOfBirth);
    }

    public static User[] findUsersWithStatuses(User[] users, UserStatus... statuses) {

        UserStatus[] arr = statuses;

        throw new UnsupportedOperationException("not implemented yet");
    }

    public static Car[] findCars(Car[] cars, LocalDate prodDate) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
