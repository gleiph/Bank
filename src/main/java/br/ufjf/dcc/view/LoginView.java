package br.ufjf.dcc.view;

import br.ufjf.dcc.controller.LoginController;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class LoginView {

    private final JFrame frame;
    private final JPanel panel;
    private final JPanel labelPanel;
    private final JPanel fieldPanel;
    @Getter
    private final JTextField emailField;
    @Getter
    private final JTextField passwordField;
    private final JButton submitBtn;
    private final JLabel emailLabel;
    private final JLabel passwordLabel;

    public LoginView() {
        frame = new JFrame("Banco");
        panel = new JPanel();
        labelPanel = new JPanel();
        fieldPanel = new JPanel();
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        submitBtn = new JButton("Entrar");
        emailLabel = new JLabel("E-mail");
        passwordLabel = new JLabel("Senha");
    }

    public void draw() {

        frame.setPreferredSize(new Dimension(300, 180));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel.setLayout(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createTitledBorder("Login"));
        labelPanel.setLayout(new GridLayout(0,1, 0, 15));
        fieldPanel.setLayout(new GridLayout(0,1, 0, 15));

        labelPanel.add(emailLabel);
        labelPanel.add(passwordLabel);
        fieldPanel.add(emailField);
        fieldPanel.add(passwordField);

        submitBtn.addActionListener(new LoginController.SubmitLogin(this));

        panel.add(labelPanel, BorderLayout.WEST);
        panel.add(fieldPanel, BorderLayout.EAST);
        panel.add(submitBtn, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
