package by.issoft.sample.service;

import by.issoft.sample.domain.User;
import by.issoft.sample.domain.UserStatus;
import by.issoft.sample.persistence.UserStorage;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

public class UserServiceImpl implements UserService {

    private final UserStorage userStorage;
    private final UserValidator userValidator;

    public UserServiceImpl(UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }

    @Override
    public String createUser(User user) {
        final boolean valid = userValidator.validateForCreation(user);
        if (!valid) {
            throw new IllegalArgumentException("user is not valid " + user);
        }

        Optional<User> byUsername = userStorage.findByUsername(user.getUserName());
        checkState(byUsername.isEmpty(), "username is busy");


        final String id = userStorage.persist(user);

        return id;
    }

    @Override
    public User findById(String id) {
        return userStorage.load(id);
    }

    public List<User> loadAll() {
        return null;
    }


}

