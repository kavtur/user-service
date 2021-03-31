package by.issoft.sample.domain;

import lombok.Value;

@Value
public class User {

    String id;

    String userName;

    String firstName;
    String lastName;

    Age age;

    UserStatus status;

    UserRole userRole;
}
