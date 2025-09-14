package br.ufjf.dcc.view;

import br.ufjf.dcc.controller.ContaController;
import br.ufjf.dcc.model.Conta;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ClienteView {

    private Conta contaCliente;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel saquePanel;
    private JPanel rightPanel;
    private JButton logoutBtn;
    private JButton saqueBtn;
    private JTextField tfValorSaque;
    private JButton confirmarBtn;

    public ClienteView(Conta contaCliente){
        this.contaCliente = contaCliente;
    }

    public void draw() {

        frame = new JFrame("Banco");
        frame.setPreferredSize(new Dimension(1200, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Cliente"));

        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400, 600));
        leftPanel.setLayout(new GridLayout(0, 1, 50, 150));
        leftPanel.add(new JButton("Extrato"));
        leftPanel.add(new JButton("Depósito"));
        leftPanel.add(new JButton("Saldo"));

        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400, 600));
        rightPanel.setLayout(new GridLayout(0, 1, 50, 150));

        logoutBtn = new JButton("Sair");
        logoutBtn.addActionListener(e -> {logout();});
        saqueBtn = new JButton("Saque");
        saqueBtn.addActionListener(e -> {showSaquePanel();});


        rightPanel.add(new JButton("Transferência"));
        rightPanel.add(saqueBtn);
        rightPanel.add(logoutBtn);

        //Constrói painel de Saque
        saquePanel = new JPanel();
        saquePanel.setPreferredSize(new Dimension(400, 600));
        saquePanel.setLayout(new FlowLayout());
        saquePanel.setBorder(BorderFactory.createTitledBorder("Saque"));
        saquePanel.setVisible(false);

        tfValorSaque = new JTextField(10);
        confirmarBtn = new JButton("Confirmar");
        confirmarBtn.addActionListener(new ContaController.RealizaSaque(this));

        saquePanel.add(new JLabel("Valor do saque"));
        saquePanel.add(tfValorSaque);
        saquePanel.add(confirmarBtn);

        //Adiciona ao painel principal
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(saquePanel, BorderLayout.CENTER);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void logout(){
        this.frame.dispose();
        LoginView view = new LoginView();
        view.draw();
    }

    public void showSaquePanel(){
        saquePanel.setVisible(true);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSucessMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
