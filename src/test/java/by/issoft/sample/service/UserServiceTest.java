package by.issoft.sample.service;

import by.issoft.sample.domain.Age;
import by.issoft.sample.domain.User;
import by.issoft.sample.domain.UserStatus;
import by.issoft.sample.persistence.UserStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static by.issoft.sample.data.UserTestSamples.anyUser;
import static by.issoft.sample.data.UserTestSamples.validUser;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

class UserServiceTest {

    private UserServiceImpl userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserStorage userStorage;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);

        when(userStorage.persist(any())).thenReturn(UUID.randomUUID().toString());

        userService = new UserServiceImpl(userStorage, userValidator);
    }

    @Test
    void createUser_invalid() {
        //given
        User user = anyUser();
//        user.setAge(Age.of(10));
//        userIsValid(user);

        //expect
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user), "user of age 10 is invalid");
        verify(userStorage, never()).persist(any());
    }

    @Test
    void createUser_userNameIsBusy() {
        //given
        String userName = "busy_name";
        when(userStorage.findByUsername(userName)).thenReturn(Optional.of(anyUser()));

        User user = validUser(userName);
        when(userValidator.validateForCreation(user)).thenReturn(true);

        //expect
        assertThrows(IllegalStateException.class, () -> userService.createUser(user));
        verify(userStorage, never()).persist(any());
    }

    @Test
    void createUser() {
        //given
        String userName = "name";
        //no users ith name in storage
        when(userStorage.findByUsername(userName)).thenReturn(Optional.empty());

        User user = validUser(userName);
        when(userValidator.validateForCreation(user)).thenReturn(true);

        //when
        final String id = userService.createUser(user);

        //then
        assertNotNull(id);
        verify(userStorage).persist(user);
        assertThat(user.getStatus(), is(UserStatus.INITIAL));
    }

    void userIsValid(User user) {
        when(userValidator.validateForCreation(user)).thenReturn(false);
    }
}
