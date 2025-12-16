package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidEmailException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import br.ufjf.dcc.model.utils.CEP;
import br.ufjf.dcc.model.utils.CPF;
import br.ufjf.dcc.model.utils.Email;
import br.ufjf.dcc.model.utils.Endereco;
import br.ufjf.dcc.model.utils.Nome;
import br.ufjf.dcc.model.utils.Perfil;
import br.ufjf.dcc.model.utils.Senha;
import br.ufjf.dcc.model.utils.Telefone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DepositoTest {

    private static final Nome VALID_NAME = Nome.getInstance("Joao", "Silva");
    private static final CPF VALID_CPF = CPF.getInstance("969.968.310-40");
    private static final Telefone VALID_PHONE = Telefone.getInstance("(32)98888-7777");
    private static final Endereco VALID_CEP = Endereco.getInstance("Rua X", 300, "Bairro Y", "Cidade A", "Estado B", CEP.getInstance("04182-115"));
    private static final Email VALID_EMAIL = Email.getInstance("joao@gmail.com");
    private static final Senha VALID_PASSWORD = Senha.getInstance("asdfghjkl");
    private static final Perfil VALID_PROFILE = Perfil.CLIENTE;
    
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
        Conta contaDestino = Conta.getInstance(new Usuario(VALID_NAME, VALID_CPF, VALID_PHONE, VALID_CEP, VALID_EMAIL, VALID_PASSWORD, VALID_PROFILE));
        contaOrigem.setSaldo(100000);
        Deposito instance = Deposito.getInstance(contaOrigem, contaDestino, 1000);
        assertNotNull(instance, "Expected Deposito instance to be created for a valid deposit.");
    }

}
