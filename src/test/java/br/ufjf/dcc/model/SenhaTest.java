package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidPasswordException;
import br.ufjf.dcc.model.utils.Senha;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SenhaTest {

    @Test
    public void shouldThrowExceptionForInvalidFormat() {
        assertThrows(InvalidPasswordException.class, () ->
                        Senha.getInstance("senha"),
                "Expected InvalidPasswordException for incorrectly formatted password.");
    }

    @Test
    public void shouldCreateInstanceForValidPassword() throws InvalidPasswordException {
        Senha instance = Senha.getInstance("senha123");
        assertNotNull(instance, "Expected Senha instance to be created for a valid password.");
    }
}
