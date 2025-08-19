package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidPhoneNumberException;
import br.ufjf.dcc.model.utils.Telefone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelefoneTest {

    @Test
    void shouldThrowExceptionForInvalidDDD() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        Telefone.getInstance("(01)98888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldThrowExceptionForInvalidPrefix() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        Telefone.getInstance("(32)18888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldThrowExceptionForMissingDDD() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        Telefone.getInstance("98888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldCreatePhoneNumberInstance() throws InvalidPhoneNumberException{
        String number = "(32)98888-7777";
        Telefone instance = Telefone.getInstance(number);
        assertNotNull(instance, "Expected PhoneNumber instance to be created for a valid phone number.");
    }
}
