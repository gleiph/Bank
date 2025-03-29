package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void shouldThrowExceptionForInvalidFormat() {
        assertThrows(InvalidEmailException.class, () ->
                        Email.getInstance("dcc025@ufjf"),
                "Expected InvalidEmailException for incorrectly formatted email.");
    }

    @Test
    public void shouldCreateInstanceForValidEmail() throws InvalidEmailException {
        Email instance = Email.getInstance("dcc025@gmail.com");
        assertNotNull(instance, "Expected Email instance to be created for a valid Email.");
    }
}