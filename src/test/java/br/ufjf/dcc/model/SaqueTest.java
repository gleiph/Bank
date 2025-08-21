package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidEmailException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaqueTest {

    @Test
    public void shouldThrowExceptionForNegativeValue(){
        Conta contaOrigem = Conta.createEmpty();
        contaOrigem.setSaldo(10000);
        assertThrows(InvalidValueException.class, () ->
                        Saque.getInstance(contaOrigem, -1000),
                "Expected InvalidValueException for a negative value.");
    }

    @Test
    public void shouldThrowExceptionForInsufficientBalance(){
        Conta contaOrigem = Conta.createEmpty();
        contaOrigem.setSaldo(0);
        assertThrows(InvalidValueException.class, () ->
                        Saque.getInstance(contaOrigem, 1000),
                "Expected InvalidValueException for insufficient balance.");
    }

    @Test
    public void shouldCreateInstanceForValidTransfer() throws InvalidEmailException {
        Conta contaOrigem = Conta.createEmpty();
        contaOrigem.setSaldo(100000);
        Saque instance = Saque.getInstance(contaOrigem, 1000);
        assertNotNull(instance, "Expected Saque instance to be created for a valid withdrawal.");
    }

}
