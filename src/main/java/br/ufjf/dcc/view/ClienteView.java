package br.ufjf.dcc.view;

import br.ufjf.dcc.controller.ContaController;
import br.ufjf.dcc.model.*;

import java.util.List;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ClienteView {

    private final Conta contaCliente;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel centralPanel;
    private JPanel rightPanel;
    private JButton logoutBtn;
    private JButton saqueBtn;
    private JButton saldoBtn;
    private JButton extratoBtn;
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

        //Cria botões laterais
        logoutBtn = new JButton("Sair");
        logoutBtn.addActionListener(e -> {logout();});
        saqueBtn = new JButton("Saque");
        saqueBtn.addActionListener(e -> {showSaquePanel();});
        saldoBtn = new JButton("Saldo");
        saldoBtn.addActionListener(e -> {showSaldoPanel();});
        extratoBtn = new JButton("Extrato");
        extratoBtn.addActionListener(e -> {showExtratoPanel();});

        //Cria painel esquerdo
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400, 600));
        leftPanel.setLayout(new GridLayout(0, 1, 50, 150));
        leftPanel.add(extratoBtn);
        leftPanel.add(new JButton("Depósito"));
        leftPanel.add(saldoBtn);

        //Cria painel direito
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400, 600));
        rightPanel.setLayout(new GridLayout(0, 1, 50, 150));
        rightPanel.add(new JButton("Transferência"));
        rightPanel.add(saqueBtn);
        rightPanel.add(logoutBtn);

        //Constrói painel central
        centralPanel = new JPanel();
        centralPanel.setPreferredSize(new Dimension(400, 600));
        centralPanel.setVisible(true);

        //Adiciona ao painel principal
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(centralPanel, BorderLayout.CENTER);

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
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Saque"));

        tfValorSaque = new JTextField(10);
        confirmarBtn = new JButton("Confirmar");
        confirmarBtn.addActionListener(new ContaController.RealizaSaque(this));

        centralPanel.add(new JLabel("Valor do saque"));
        centralPanel.add(tfValorSaque);
        centralPanel.add(confirmarBtn);

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showSaldoPanel(){
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Saldo"));

        double valorSaldo = this.contaCliente.getSaldo();
        centralPanel.add(new JLabel("O saldo atual da conta é: R$" + valorSaldo));

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showExtratoPanel(){
        centralPanel.removeAll();
        centralPanel.setLayout(new GridLayout(0, 1));
        centralPanel.setBorder(BorderFactory.createTitledBorder("Extrato"));

        List<Saque> saques = contaCliente.getSaques();
        List<Transferencia> transferencias = contaCliente.getTransferencias();
        List<Deposito> depositos = contaCliente.getDepositos();

        //Lista de Saques
        JPanel saquesPanel = new JPanel();
        saquesPanel.setBorder(BorderFactory.createTitledBorder("Saques"));

        DefaultListModel<String> saqueModel = new DefaultListModel<>();
        JList<String> listaSaque = new JList<>(saqueModel);

        for(Saque s : saques){
            saqueModel.addElement("Data: " + s.getData() + " / Valor: R$" + s.getValor());
        }
        JScrollPane saqueScrollPane = new JScrollPane(listaSaque);
        saquesPanel.add(saqueScrollPane);

        //Lista de Transferências
        JPanel transferenciasPanel = new JPanel();
        transferenciasPanel.setBorder(BorderFactory.createTitledBorder("Transferências"));

        DefaultListModel<String> transferenciaModel = new DefaultListModel<>();
        JList<String> listaTransferencia = new JList<>(transferenciaModel);

        for(Transferencia t : transferencias){
            transferenciaModel.addElement("Data: " + t.getData() + " / Valor: R$" + t.getValor()  + " / Para: " + t.getTitularContaDestino());
        }
        JScrollPane transferenciaScrollPane = new JScrollPane(listaTransferencia);
        saquesPanel.add(transferenciaScrollPane);

        //Lista de Depósitos
        JPanel depositosPanel = new JPanel();
        depositosPanel.setBorder(BorderFactory.createTitledBorder("Depósitos"));

        DefaultListModel<String> depositoModel = new DefaultListModel<>();
        JList<String> listaDepositos = new JList<>(depositoModel);

        for(Deposito d : depositos){
            depositoModel.addElement("Data: " + d.getData() + " / Valor: R$" + d.getValor()  + " / Para: " + d.getTitularContaDestino());
        }
        JScrollPane depositoScrollPane = new JScrollPane(listaDepositos);
        saquesPanel.add(depositoScrollPane);

        //Adciona ao painel central
        centralPanel.add(saquesPanel);
        centralPanel.add(transferenciasPanel);
        centralPanel.add(depositosPanel);
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSucessMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
