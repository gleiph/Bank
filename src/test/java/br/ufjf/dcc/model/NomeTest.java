package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidNameException;
import br.ufjf.dcc.model.utils.Nome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NomeTest {

    @Test
    public void shouldThrowExceptionForInvalidFormat() {
        assertThrows(InvalidNameException.class, () ->
                        Nome.getInstance("A", "Silva"),
                "Expected InvalidNameException for incorrectly formatted name.");
    }

    @Test
    public void shouldCreateInstanceForValidName() throws InvalidNameException {
        Nome instance = Nome.getInstance("Ana", "Silva");
        assertNotNull(instance, "Expected Nome instance to be created for a valid name.");
    }
}
