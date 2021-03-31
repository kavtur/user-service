package by.issoft.sample.persistence;

import by.issoft.sample.domain.User;
import org.junit.jupiter.api.Test;

import static by.issoft.sample.data.UserTestSamples.anyValidUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InMemoryUserStorageTest {

    private UserStorage userStorage = new InMemoryUserStorage();

    @Test
    public void testPersist() {
        //given
        User user = anyValidUser();

        //when
        final String id = userStorage.persist(user);

        //then
        assertThat(id, is(notNullValue()));
        final User loaded = userStorage.load(id);
        assertThat(loaded, is(equalTo(user)));
    }

    @Test
    public void testPersist_null() {
        //when
        assertThrows(NullPointerException.class, () -> userStorage.persist(null));
    }

}
