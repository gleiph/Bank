package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidEmailException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositoTest {

    @Test
    public void shouldThrowExceptionForNegativeValue(){
        Conta contaOrigem = Conta.createEmpty();
        Conta contaDestino = Conta.createEmpty();
        contaOrigem.setSaldo(10000);
        assertThrows(InvalidValueException.class, () ->
                        Deposito.getInstance(contaOrigem, contaDestino, -1000),
                "Expected InvalidValueException for a negative value.");
    }

    @Test
    public void shouldCreateInstanceForValidTransfer() throws InvalidEmailException {
        Conta contaOrigem = Conta.createEmpty();
        Conta contaDestino = Conta.createEmpty();
        contaOrigem.setSaldo(100000);
        Deposito instance = Deposito.getInstance(contaOrigem, contaDestino, 1000);
        assertNotNull(instance, "Expected Deposito instance to be created for a valid deposit.");
    }

}
