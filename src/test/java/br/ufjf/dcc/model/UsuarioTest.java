package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Tests")
class UsuarioTest {
    private static final Nome VALID_NAME = Nome.getInstance("Joao", "Silva");
    private static final Date VALID_DATE = new Date(125, Calendar.JANUARY, 1);
    private static final CPF VALID_CPF = CPF.getInstance("969.968.310-40");
    private static final Telefone VALID_PHONE = Telefone.getInstance("(32)98888-7777");
    private static final Endereco VALID_CEP = Endereco.getInstance("Rua X", 300, "Bairro Y", "Cidade A", "Estado B", CEP.getInstance("04182-115"));
    private static final Email VALID_EMAIL = Email.getInstance("joao@gmail.com");
    private static final Senha VALID_PASSWORD = Senha.getInstance("asdfghjkl");

    private Usuario validUser;

    @BeforeEach
    void setUp() {
        validUser = new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {
        @Test
        void should_throw_exception_when_email_is_null() {
            assertThrows(NullPointerException.class, () ->
                new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, null, VALID_PASSWORD));
        }

        @Test
        void should_throw_exception_when_password_is_null() {
            assertThrows(NullPointerException.class, () ->
                    new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, null));
        }

        @Test
        void should_create_user_with_valid_data() {
            Usuario user = new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD); ;
            assertNotNull(user);
            assertEquals(VALID_NAME, user.getNome());
            assertEquals(VALID_DATE, user.getData_nasc());
            assertEquals(VALID_CPF, user.getCpf());
            assertEquals(VALID_PHONE, user.getTelefone());
            assertEquals(VALID_CEP, user.getEndereco());
            assertEquals(VALID_EMAIL, user.getEmail());
        }
    }

    @Nested
    @DisplayName("Equals Tests")
    class EqualsTests {
        @Test
        void should_be_not_equal_when_compare_user1_to_user2() {
            Usuario user2 = new Usuario(Nome.getInstance("Roberto", "Silveira"), VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD); ;
            assertNotEquals(validUser, user2);
        }

        @Test
        void should_be_equal_when_compare_user1_to_user2() {
            Usuario user2 = new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD);
            assertEquals(validUser, user2);
        }

        @Test
        void should_be_not_equal_when_compare_to_null() {
            Usuario actual = null;
            assertNotEquals(validUser, actual);
        }

        @Test
        void should_have_same_hashcode_when_users_are_equal() {
            Usuario user2 = new Usuario(VALID_NAME, VALID_DATE, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD);
            assertEquals(validUser.hashCode(), user2.hashCode());
        }
    }

    @Nested
    @DisplayName("Password Tests")
    class PasswordTests {
        @Test
        void should_validate_password_when_check_password_of_user1() {
            assertTrue(validUser.checkPassword("asdfghjkl"));
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

}