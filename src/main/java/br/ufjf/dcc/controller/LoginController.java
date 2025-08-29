package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.Usuario;
import br.ufjf.dcc.model.exception.InvalidEmailException;
import br.ufjf.dcc.model.exception.InvalidLoginException;
import br.ufjf.dcc.model.exception.InvalidPasswordException;
import br.ufjf.dcc.model.utils.Email;
import br.ufjf.dcc.model.utils.Senha;
import br.ufjf.dcc.model.repository.UsuarioRepository;
import br.ufjf.dcc.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginController {

    public static Usuario login(Email email, Senha senha) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        List<Usuario> usuarios = usuarioRepository.findAll();

        Usuario usuarioLogado = null;
        for(Usuario user : usuarios){
            if(user.getEmail().getEmail().equals(email.getEmail()) && user.getSenha().getSenha().equals(senha.getSenha())){
                usuarioLogado = user;
            }
        }
        if(usuarioLogado == null){
            throw new InvalidLoginException("Invalid login");
        }

        System.out.println("Successful login");
        return usuarioLogado;
    }

    public static class SubmitLogin implements ActionListener {

        private final LoginView view;

        public SubmitLogin(LoginView view) {
            this.view = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Email email = Email.getInstance(view.getEmailField().getText());
                Senha senha = Senha.getInstance(view.getPasswordField().getText());
                LoginController.login(email, senha);
            }
            catch(InvalidLoginException | InvalidEmailException | InvalidPasswordException error){
                view.showErrorMessage();
            }
        }
    }
}
