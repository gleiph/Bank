package br.ufjf.dcc.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Tests")
class UserTest {
    private static final String VALID_LOGIN = "gleiph";
    private static final String VALID_PASSWORD = "asdfghjkl";
    private static final String VALID_EMAIL = "gleiph@gmail.com";
    private static final String VALID_CPF = "969.968.310-40";

    private User validUser;

    @BeforeEach
    void setUp() {
        validUser = new User(VALID_LOGIN, VALID_PASSWORD, VALID_EMAIL, VALID_CPF);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        void should_throw_exception_when_login_is_null() {
            assertThrows(NullPointerException.class, () ->
                new User(null, VALID_PASSWORD, VALID_EMAIL, VALID_CPF));
        }

        @Test
        void should_throw_exception_when_password_is_null() {
            assertThrows(NullPointerException.class, () ->
                new User(VALID_LOGIN, null, VALID_EMAIL, VALID_CPF));
        }

        @Test
        void should_create_user_with_valid_data() {
            User user = new User(VALID_LOGIN, VALID_PASSWORD, VALID_EMAIL, VALID_CPF);
            assertNotNull(user);
            assertEquals(VALID_LOGIN, user.getLogin());
            assertEquals(VALID_EMAIL, user.getEmail().toString());
            assertEquals(VALID_CPF, user.getCpf().toString());
        }
    }

    @Nested
    @DisplayName("Equals Tests")
    class EqualsTests {
        @Test
        void should_be_not_equal_when_compare_user1_to_user2() {
            User user2 = new User("gleiphghiotto", VALID_PASSWORD, VALID_EMAIL, VALID_CPF);
            assertNotEquals(validUser, user2);
        }

        @Test
        void should_be_equal_when_compare_user1_to_user2() {
            User user2 = new User(VALID_LOGIN, VALID_PASSWORD, VALID_EMAIL, VALID_CPF);
            assertEquals(validUser, user2);
        }

        @Test
        void should_be_not_equal_when_compare_to_null() {
            User actual = null;
            assertNotEquals(validUser, actual);
        }

        @Test
        void should_have_same_hashcode_when_users_are_equal() {
            User user2 = new User(VALID_LOGIN, VALID_PASSWORD, VALID_EMAIL, VALID_CPF);
            assertEquals(validUser.hashCode(), user2.hashCode());
        }
    }

    @Nested
    @DisplayName("Password Tests")
    class PasswordTests {
        @Test
        void should_validate_password_when_check_password_of_user1() {
            assertTrue(validUser.checkPassword(VALID_PASSWORD));
        }

        @Test
        void should_not_validate_password_when_check_password_of_user1() {
            assertFalse(validUser.checkPassword("asdfghjkl@"));
        }

        @Test
        void should_not_validate_null_password() {
            assertFalse(validUser.checkPassword(null));
        }

        @Test
        void should_not_validate_empty_password() {
            assertFalse(validUser.checkPassword(""));
        }
    }

    @Nested
    @DisplayName("Getter Tests")
    class GetterTests {
        @Test
        void should_return_correct_login() {
            assertEquals(VALID_LOGIN, validUser.getLogin());
        }

        @Test
        void should_return_correct_email() {
            assertNotNull(validUser.getEmail());
            assertEquals(VALID_EMAIL, validUser.getEmail().toString());
        }

        @Test
        void should_return_correct_cpf() {
            assertNotNull(validUser.getCpf());
            assertEquals(VALID_CPF, validUser.getCpf().toString());
        }
    }
}