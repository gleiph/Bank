package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.Usuario;
import br.ufjf.dcc.model.repository.UsuarioRepository;
import br.ufjf.dcc.view.AdminView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class UsuarioController {

    private static UsuarioRepository repository;

    public static class AdicionarUsuario implements ActionListener {

        private final AdminView tela;

        public AdicionarUsuario(AdminView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tela.addUsuario();
        }
    }

    public static class EditarUsuario implements ActionListener {
        private final AdminView tela;

        public EditarUsuario(AdminView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tela.editarUsuario();
        }
    }

    public static class RemoverUsuario implements ActionListener {

        private final AdminView tela;

        public RemoverUsuario(AdminView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tela.removerUsuario();
        }
    }

    public static class SelecionarUsuario implements ListSelectionListener {

        private final AdminView tela;

        public SelecionarUsuario(AdminView tela) {
            this.tela = tela;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            tela.atualizarFormulario();
        }
    }

    public static class GerenciarUsuarios implements WindowListener {

        private final AdminView tela;

        public GerenciarUsuarios(AdminView tela) {
            this.tela = tela;
        }

        @Override
        public void windowOpened(WindowEvent e) {
            repository = new UsuarioRepository();
            List<Usuario> all = repository.findAll();
            tela.carregaUsuarios(all);
        }

        @Override
        public void windowClosing(WindowEvent e) {
            repository = new UsuarioRepository();
            repository.save(tela.listaUsuarios());
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }

}
