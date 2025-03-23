package br.ufjf.dcc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    public void isInvalidCPFFormat() {
        CPF cpf = new CPF();
        boolean actual = cpf.isInvalidCPF("123.456.67-37");
        assertFalse(actual);
    }

    @Test
    public void isValidCPFFormatButInvalid() {
        CPF cpf = new CPF();
        boolean actual = cpf.isInvalidCPF("116.008.450-52");
        assertFalse(actual);
    }

    @Test
    public void isValidCPFFormat() {
        CPF cpf = new CPF();
        boolean actual = cpf.isInvalidCPF("529.982.247-25");
        assertTrue(actual);
    }



}