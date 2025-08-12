package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidCPFException;
import br.ufjf.dcc.model.utils.CPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    public void shouldThrowExceptionForInvalidFormat() {
        assertThrows(InvalidCPFException.class, () -> CPF.getInstance("123.456.67-37"),
                "Expected InvalidCPFException for incorrectly formatted CPF.");
    }

    @Test
    public void shouldThrowExceptionForInvalidCPFDespiteValidFormat() {
        assertThrows(InvalidCPFException.class, () -> CPF.getInstance("116.008.450-52"),
                "Expected InvalidCPFException for an invalid CPF.");
    }

    @Test
    public void shouldCreateInstanceForValidCPF() throws InvalidCPFException {
        CPF instance = CPF.getInstance("529.982.247-25");
        assertNotNull(instance, "Expected CPF instance to be created for a valid CPF.");
    }
}