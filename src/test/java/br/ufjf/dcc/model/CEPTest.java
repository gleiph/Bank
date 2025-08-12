package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidCEPException;
import br.ufjf.dcc.model.exception.InvalidCPFException;
import br.ufjf.dcc.model.utils.CEP;

import br.ufjf.dcc.model.utils.CPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CEPTest {

    @Test
    void shouldThrowExceptionForInvalidFormat(){
        assertThrows(InvalidCEPException.class, () -> CEP.getInstance("3601-130"),
                "Expected InvalidCEPException for incorrectly formatted CEP.");
    }

    @Test
    void shouldThrowExceptionForInvalidLength(){
        assertThrows(InvalidCEPException.class, () -> CEP.getInstance("3601130"),
                "Expected InvalidCEPException for incorrectly formatted CEP.");
    }

    @Test
    void shouldCreateInstanceForValidCEP() throws InvalidCEPException{
        CEP instance = CEP.getInstance("36016-130");
        assertNotNull(instance, "Expected CEP instance to be created for a valid CEP.");
    }

    @Test
    void shouldCreateInstanceForValidCEPWithoutHyphen() throws InvalidCEPException{
        CEP instance = CEP.getInstance("36016130");
        assertNotNull(instance, "Expected CEP instance to be created for a valid CEP.");
    }
}
