package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidPhoneNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneNumberTest {

    @Test
    void shouldThrowExceptionForInvalidDDD() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        PhoneNumber.getInstance("(01)98888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldThrowExceptionForInvalidPrefix() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        PhoneNumber.getInstance("(32)18888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldThrowExceptionForMissingDDD() {
        assertThrows(InvalidPhoneNumberException.class, () ->
                        PhoneNumber.getInstance("98888-7777"),
                "Expected InvalidPhoneNumber for incorrectly formatted number.");
    }

    @Test
    void shouldCreatePhoneNumberInstance() throws InvalidPhoneNumberException{
        String number = "(32)98888-7777";
        PhoneNumber instance = PhoneNumber.getInstance(number);
        assertNotNull(instance, "Expected PhoneNumber instance to be created for a valid phone number.");
    }
}
