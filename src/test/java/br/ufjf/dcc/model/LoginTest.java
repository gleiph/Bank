package br.ufjf.dcc.model;

import br.ufjf.dcc.controller.LoginController;
import br.ufjf.dcc.model.exception.InvalidLoginException;
import br.ufjf.dcc.model.repository.UsuarioRepository;
import br.ufjf.dcc.model.utils.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginTest {

    @Test
    public void shouldReturnAValidUser(){
        Usuario user = new Usuario(
                Nome.getInstance("João", "Silva"),
                CPF.getInstance("011.251.206-22"),
                Telefone.getInstance("(32)98888-7777"),
                Endereco.getInstance("Rua Zero", 33, "Bairro X", "Cidade", "Estado", CEP.getInstance("88135-145")),
                Email.getInstance("joao@gmail.com"),
                Senha.getInstance("senha123"),
                Perfil.CLIENTE);
        UsuarioRepository repository = new UsuarioRepository();
        repository.save(user);

        Usuario usuarioLogado = LoginController.login("joao@gmail.com", "senha123");
        assertNotNull(usuarioLogado, "Expected a valid user to be returned");
    }

    @Test
    public void shouldThrowExceptionForInvalidLogin(){
        assertThrows(InvalidLoginException.class, () ->
                LoginController.login("emailNaoExistente@email.com", "12345678"),
                "Expected InvalidLoginException for invalid login.");
    }

}
