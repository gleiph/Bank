package br.ufjf.dcc.view;

import br.ufjf.dcc.controller.UsuarioController;
import br.ufjf.dcc.model.Conta;
import br.ufjf.dcc.model.Usuario;
import br.ufjf.dcc.model.exception.*;
import br.ufjf.dcc.model.repository.ContaRepository;
import br.ufjf.dcc.model.utils.*;
import br.ufjf.dcc.model.Usuario.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminView {

    private JFrame tela;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private JTextField tfNome;
    private JTextField tfSobrenome;
    private JTextField tfCpf;
    private JTextField tfTelefone;
    private JTextField tfRua;
    private JTextField tfNumero;
    private JTextField tfBairro;
    private JTextField tfCidade;
    private JTextField tfEstado;
    private JTextField tfCep;
    private JTextField tfEmail;
    private JTextField tfSenha;
    private JComboBox jcPerfil;
    private JList<Usuario> jlUsuarios;

    public void desenha(){

        tela = new JFrame("Agenda");
        tela.addWindowListener(new UsuarioController.GerenciarUsuarios(this));
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.setResizable(false);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);

        desenhaLista();
        desenhaFormulario();

        tela.pack();
    }

    private void desenhaLista(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Usuarios"));
        painel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Usuario> model = new DefaultListModel<>();


        jlUsuarios = new JList<>(model);
        jlUsuarios.addListSelectionListener(new UsuarioController.SelecionarUsuario(this));

        painel.add(new JScrollPane(jlUsuarios), BorderLayout.CENTER);

        tela.getContentPane().add(painel, BorderLayout.WEST);
    }

    private void desenhaFormulario(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário"));


        JPanel formulario = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP, 20));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("Sobrenome"));
        painelLabel.add(new JLabel("CPF"));
        painelLabel.add(new JLabel("Telefone"));
        painelLabel.add(new JLabel("Rua"));
        painelLabel.add(new JLabel("Número"));
        painelLabel.add(new JLabel("Bairro"));
        painelLabel.add(new JLabel("Cidade"));
        painelLabel.add(new JLabel("Estado"));
        painelLabel.add(new JLabel("CEP"));
        painelLabel.add(new JLabel("Email"));
        painelLabel.add(new JLabel("Senha"));
        painelLabel.add(new JLabel("Perfil"));

        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfNome = new JTextField(20);
        tfSobrenome = new JTextField(20);
        tfCpf = new JTextField(20);
        tfTelefone = new JTextField(20);
        tfRua = new JTextField(20);
        tfNumero = new JTextField(20);
        tfBairro = new JTextField(20);
        tfCidade = new JTextField(20);
        tfEstado = new JTextField(20);
        tfCep = new JTextField(20);
        tfEmail = new JTextField(20);
        tfSenha = new JTextField(20);

        Perfil[] perfis = {Perfil.CLIENTE, Perfil.CAIXA, Perfil.GERENTE, Perfil.ADMIN};
        jcPerfil = new JComboBox<>(perfis);
        jcPerfil.setSelectedIndex(-1);

        painelField.add(tfNome);
        painelField.add(tfSobrenome);
        painelField.add(tfCpf);
        painelField.add(tfTelefone);
        painelField.add(tfRua);
        painelField.add(tfNumero);
        painelField.add(tfBairro);
        painelField.add(tfCidade);
        painelField.add(tfEstado);
        painelField.add(tfCep);
        painelField.add(tfEmail);
        painelField.add(tfSenha);
        painelField.add(jcPerfil);

        formulario.add(painelLabel);
        formulario.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new UsuarioController.AdicionarUsuario(this));

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new UsuarioController.RemoverUsuario(this));

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new UsuarioController.EditarUsuario(this));

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {tela.dispose();});

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnRemover);
        botoes.add(btnEditar);
        botoes.add(btnSair);

        painel.add(botoes, BorderLayout.SOUTH);

        tela.getContentPane().add(painel, BorderLayout.CENTER);

    }

    public void carregaUsuarios(List<Usuario> usuarios){

        DefaultListModel<Usuario> model = (DefaultListModel<Usuario>)jlUsuarios.getModel();

        for (Usuario c: usuarios
        ) {
            model.addElement(c);
        }

    }

    public List<Usuario> listaUsuarios(){
        DefaultListModel<Usuario> model = (DefaultListModel<Usuario>)jlUsuarios.getModel();
        List<Usuario> usuarios = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            usuarios.add(model.get(i));
        }

        return usuarios;
    }

    public void addUsuario(){

        DefaultListModel<Usuario> model = (DefaultListModel<Usuario>)jlUsuarios.getModel();
        Perfil perfil = (Perfil) jcPerfil.getSelectedItem();
        try {
            Usuario usuario = new Usuario(
                    Nome.getInstance(tfNome.getText(), tfSobrenome.getText()),
                    CPF.getInstance(tfCpf.getText()),
                    Telefone.getInstance(tfTelefone.getText()),
                    Endereco.getInstance(
                            tfRua.getText(),
                            Integer.parseInt(tfNumero.getText()),
                            tfBairro.getText(),
                            tfCidade.getText(),
                            tfEstado.getText(),
                            CEP.getInstance(tfCep.getText())
                    ),
                    Email.getInstance(tfEmail.getText()),
                    Senha.getInstance(tfSenha.getText()),
                    perfil);
            model.addElement(usuario);
            if(perfil == Perfil.CLIENTE){
                Conta contaCliente = Conta.getInstance(usuario);
                ContaRepository repository = new ContaRepository();
                repository.save(contaCliente);
            }
            JOptionPane.showMessageDialog(tela, "Usuário adicionado.");
        } catch (InvalidNameException |
                 InvalidCPFException |
                 InvalidPhoneNumberException |
                 InvalidEmailException |
                 InvalidPasswordException e) {
            JOptionPane.showMessageDialog(tela, e.getMessage());
        }
    }

    public void removerUsuario(){

        int selectedIndex = jlUsuarios.getSelectedIndex();

        if(selectedIndex != -1){

            DefaultListModel<Usuario> model =
                    (DefaultListModel<Usuario>)jlUsuarios.getModel();
            model.remove(selectedIndex);
        }
        JOptionPane.showMessageDialog(tela, "Usuário removido.");
    }

    public void atualizarFormulario(){

        int selectedIndex = jlUsuarios.getSelectedIndex();
        if(selectedIndex != -1) {

            DefaultListModel<Usuario> model = (DefaultListModel<Usuario>) jlUsuarios.getModel();
            Usuario usuario = model.get(selectedIndex);
            tfNome.setText(usuario.getNome().getNome());
            tfSobrenome.setText(usuario.getNome().getSobrenome());
            tfCpf.setText(usuario.getCpf().getCpf());
            tfTelefone.setText(usuario.getTelefone().getNumero());
            tfRua.setText(usuario.getEndereco().getRua());
            tfNumero.setText(String.valueOf(usuario.getEndereco().getNumero()));
            tfBairro.setText(usuario.getEndereco().getBairro());
            tfCidade.setText(usuario.getEndereco().getCidade());
            tfEstado.setText(usuario.getEndereco().getEstado());
            tfCep.setText(String.valueOf(usuario.getEndereco().getCep()));
            tfEmail.setText(usuario.getEmail().getEmail());
            tfSenha.setText(""); //Protege senha
            Perfil perfil = usuario.getPerfil();
            int index = -1;
            switch(perfil){
                case Perfil.CLIENTE: index = 0;
                    break;
                case Perfil.CAIXA: index = 1;
                    break;
                case Perfil.GERENTE: index = 2;
                    break;
                case Perfil.ADMIN: index = 3;
                default: break;
            }
            jcPerfil.setSelectedIndex(index);
        }
    }

    public void editarUsuario(){

        int selectedIndex = jlUsuarios.getSelectedIndex();

        if(selectedIndex != -1){

            DefaultListModel<Usuario> model = (DefaultListModel<Usuario>)jlUsuarios.getModel();

            Usuario usuario = model.get(selectedIndex);

            model.remove(selectedIndex);

            try {
                usuario.setTelefone(Telefone.getInstance(tfTelefone.getText()));
                usuario.setEndereco(Endereco.getInstance(
                        tfRua.getText(),
                        Integer.parseInt(tfNumero.getText()),
                        tfBairro.getText(),
                        tfCidade.getText(),
                        tfEstado.getText(),
                        CEP.getInstance(tfCep.getText())
                ));
                usuario.setEmail(Email.getInstance(tfEmail.getText()));
                model.add(selectedIndex, usuario);
                JOptionPane.showMessageDialog(tela, "Usuário editado.");
            }catch(InvalidCEPException | InvalidEmailException | InvalidPhoneNumberException error){
                JOptionPane.showMessageDialog(tela, "Informações inválidas!");
            }
        }

        tela.pack();
    }

}
