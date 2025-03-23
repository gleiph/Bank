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

}