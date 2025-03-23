package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidCPFException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {

    @Test
    public void isInvalidCPFFormat() {
        boolean actual = catchInvalidCPFException("123.456.67-37").isPresent();
        assertTrue(actual);
    }

    @Test
    public void isValidCPFFormatButInvalid() {
        boolean actual = catchInvalidCPFException("116.008.450-52").isPresent();
        assertTrue(actual);
    }

    @Test
    public void isValidCPFFormat() throws InvalidCPFException {
        CPF instance = CPF.getInstance("529.982.247-25");
        assertNotNull(instance);
    }

    private Optional<InvalidCPFException> catchInvalidCPFException(String cpf) {
        InvalidCPFException exception;
        exception = assertThrows(InvalidCPFException.class,
                ()->{CPF.getInstance(cpf);}
        );
        return Optional.ofNullable(exception);
    }

}