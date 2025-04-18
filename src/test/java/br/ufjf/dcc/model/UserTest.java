package br.ufjf.dcc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void should_be_not_equal_when_compare_user1_to_user2() {

        User user1 = new User("gleiph", "asdfghjklç", "gleiph@gmail.com", "969.968.310-40");
        User user2 = new User("gleiphghiotto", "asdfghjklç", "gleiph@gmail.com", "969.968.310-40");

        assertNotEquals(user1, user2);

    }

    @Test
    public void should_be_equal_when_compare_user1_to_user2() {

        User user1 = new User("gleiph", "asdfghjkl", "gleiph@gmail.com", "969.968.310-40");
        User user2 = new User("gleiph", "asdfghjkl", "gleiph@gmail.com", "969.968.310-40");

        assertEquals(user1, user2);

    }

    @Test
    public void should_validate_password_when_check_password_of_user1() {

        User user1 = new User("gleiph", "asdfghjkl", "gleiph@gmail.com", "969.968.310-40");

        assertTrue(user1.checkPassword("asdfghjkl"));

    }

    @Test
    public void should_not_validate_password_when_check_password_of_user1() {

        User user1 = new User("gleiph", "asdfghjkl", "gleiph@gmail.com", "969.968.310-40");

        assertFalse(user1.checkPassword("asdfghjkl@"));

    }
}