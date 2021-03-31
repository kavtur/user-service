package by.issoft.sample.service;

import by.issoft.sample.domain.User;

public interface UserService {
    String createUser(User user);

    User findById(String id);
}
