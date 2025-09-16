package br.ufjf.dcc.view;

import br.ufjf.dcc.controller.ContaController;
import br.ufjf.dcc.model.Conta;
import br.ufjf.dcc.model.Deposito;
import br.ufjf.dcc.model.Saque;
import br.ufjf.dcc.model.Transferencia;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    private JButton depositoBtn;
    private JButton transferenciaBtn;
    private JTextField tfValorSaque;
    private JTextField tfValorDeposito;
    private JTextField tfValorTransferencia;
    private JTextField tfContaDeposito;
    private JTextField tfContaTransferencia;
    private JButton confirmarSaqueBtn;
    private JButton confirmarDepositoBtn;
    private JButton confirmarTransferenciaBtn;

    public ClienteView(Conta contaCliente) {
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
        logoutBtn.addActionListener(e -> {
            logout();
        });
        saqueBtn = new JButton("Saque");
        saqueBtn.addActionListener(e -> {
            showSaquePanel();
        });
        saldoBtn = new JButton("Saldo");
        saldoBtn.addActionListener(e -> {
            showSaldoPanel();
        });
        extratoBtn = new JButton("Extrato");
        extratoBtn.addActionListener(e -> {
            showExtratoPanel();
        });
        depositoBtn = new JButton("Depósito");
        depositoBtn.addActionListener(e -> {
            showDepositoPanel();
        });
        transferenciaBtn = new JButton("Transferência");
        transferenciaBtn.addActionListener(e -> {
            showTransferenciaPanel();
        });

        //Cria painel esquerdo
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400, 600));
        leftPanel.setLayout(new GridLayout(0, 1, 50, 150));
        leftPanel.add(extratoBtn);
        leftPanel.add(depositoBtn);
        leftPanel.add(saldoBtn);

        //Cria painel direito
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(400, 600));
        rightPanel.setLayout(new GridLayout(0, 1, 50, 150));
        rightPanel.add(transferenciaBtn);
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

    public void logout() {
        this.frame.dispose();
        LoginView view = new LoginView();
        view.draw();
    }

    public void showSaquePanel() {
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Saque"));

        tfValorSaque = new JTextField(10);
        confirmarSaqueBtn = new JButton("Confirmar");
        confirmarSaqueBtn.addActionListener(new ContaController.RealizaSaque(this));

        centralPanel.add(new JLabel("Valor do saque"));
        centralPanel.add(tfValorSaque);
        centralPanel.add(confirmarSaqueBtn);

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showSaldoPanel() {
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Saldo"));

        double valorSaldo = this.contaCliente.getSaldo();
        centralPanel.add(new JLabel("O saldo atual da conta é: R$" + valorSaldo));

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showExtratoPanel() {
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

        for (Saque s : saques) {
            saqueModel.addElement("Data: " + s.getData().getDate() + "/" + (s.getData().getMonth() + 1) + "/" +
                    (s.getData().getYear() + 1900) + " - Valor: R$" + s.getValor());
        }
        JScrollPane saqueScrollPane = new JScrollPane(listaSaque);
        saqueScrollPane.createHorizontalScrollBar();
        saquesPanel.add(saqueScrollPane);

        //Lista de Transferências

        JPanel transferenciasPanel = new JPanel();
        transferenciasPanel.setBorder(BorderFactory.createTitledBorder("Transferências"));

        DefaultListModel<String> transferenciaModel = new DefaultListModel<>();
        JList<String> listaTransferencia = new JList<>(transferenciaModel);

        for (Transferencia t : transferencias) {
            transferenciaModel.addElement("Data: " + t.getData().getDate() + "/" + (t.getData().getMonth() + 1) + "/" +
                    (t.getData().getYear() + 1900) + " - Valor: R$" + t.getValor() + " - Para: " + t.getTitularContaDestino());
        }
        JScrollPane transferenciaScrollPane = new JScrollPane(listaTransferencia);
        transferenciaScrollPane.createHorizontalScrollBar();
        transferenciasPanel.add(transferenciaScrollPane);

        //Lista de Depósitos

        JPanel depositosPanel = new JPanel();
        depositosPanel.setBorder(BorderFactory.createTitledBorder("Depósitos"));

        DefaultListModel<String> depositoModel = new DefaultListModel<>();
        JList<String> listaDepositos = new JList<>(depositoModel);

        for (Deposito d : depositos) {
            depositoModel.addElement("Data: " + d.getData().getDate() + "/" + (d.getData().getMonth() + 1) + "/" +
                    (d.getData().getYear() + 1900) + " - Valor: R$" + d.getValor() + " - Para: " + d.getTitularContaDestino());
        }
        JScrollPane depositoScrollPane = new JScrollPane(listaDepositos);
        depositoScrollPane.createHorizontalScrollBar();
        depositosPanel.add(depositoScrollPane);

        //Adiciona ao painel central
        centralPanel.add(saquesPanel);
        centralPanel.add(transferenciasPanel);
        centralPanel.add(depositosPanel);
        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showDepositoPanel() {
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Depósito"));

        tfContaDeposito = new JTextField(20);
        tfValorDeposito = new JTextField(10);
        confirmarDepositoBtn = new JButton("Confirmar");
        confirmarDepositoBtn.addActionListener(new ContaController.RealizaDeposito(this));

        centralPanel.add(new JLabel("Conta de destino"));
        centralPanel.add(tfContaDeposito);
        centralPanel.add(new JLabel("Valor do depósito"));
        centralPanel.add(tfValorDeposito);
        centralPanel.add(confirmarDepositoBtn);

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showTransferenciaPanel(){
        centralPanel.removeAll();
        centralPanel.setLayout(new FlowLayout());
        centralPanel.setBorder(BorderFactory.createTitledBorder("Transferência"));

        tfContaTransferencia = new JTextField(20);
        tfValorTransferencia = new JTextField(10);
        confirmarTransferenciaBtn = new JButton("Confirmar");
        confirmarTransferenciaBtn.addActionListener(new ContaController.RealizaTransferencia(this));

        centralPanel.add(new JLabel("Conta de destino"));
        centralPanel.add(tfContaTransferencia);
        centralPanel.add(new JLabel("Valor da transferência"));
        centralPanel.add(tfValorTransferencia);
        centralPanel.add(confirmarTransferenciaBtn);

        centralPanel.revalidate();
        centralPanel.repaint();
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showSucessMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}
