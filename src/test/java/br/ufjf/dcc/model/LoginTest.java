package br.ufjf.dcc.model;

import br.ufjf.dcc.controller.LoginController;
import br.ufjf.dcc.model.exception.InvalidEmailException;
import br.ufjf.dcc.model.exception.InvalidLoginException;
import br.ufjf.dcc.model.repository.UsuarioRepository;
import br.ufjf.dcc.model.utils.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

public class LoginTest {

    @Test
    public void shouldReturnAValidUser(){
        Date d = new Date(125, Calendar.AUGUST, 25);
        Cliente c = Cliente.getInstance(Nome.getInstance("João", "Silva"), d, CPF.getInstance("011.251.206-22"),
                new Endereco("Rua Zero", 33, "Bairro X", "Cidade", "Estado", CEP.getInstance("88135-145")),
                Email.getInstance("joao@email.com"), Senha.getInstance("12345678"));
        UsuarioRepository repository = new UsuarioRepository();
        repository.save(c);

        LoginController controller = new LoginController();
        Usuario usuarioLogado = controller.login(Email.getInstance("joao@email.com"), Senha.getInstance("12345678"));
        assertNotNull(usuarioLogado, "Expected a valid user to be returned");
    }

    @Test
    public void shouldThrowExceptionForInvalidLogin(){
        LoginController controller = new LoginController();
        assertThrows(InvalidLoginException.class, () ->
                controller.login(Email.getInstance("emailNaoExistente@email.com"), Senha.getInstance("12345678")),
                "Expected InvalidLoginException for invalid login.");
    }

}
