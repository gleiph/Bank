package br.ufjf.dcc.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class User {

    private final String login;
    private final String password;
    private final Email email;
    private final CPF cpf;

    public User(String login, String password, String email, String cpf) {
        this.login = login;
        this.password = hashPassword(password);
        this.email = Email.getInstance(email);
        this.cpf = CPF.getInstance(cpf);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Converte os bytes para uma string hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar a senha", e);
        }
    }

    public boolean checkPassword(String password) {
        return hashPassword(password).equals(this.password);
    }

    public String getLogin() {
        return login;
    }

    public Email getEmail() {
        return email;
    }

    public CPF getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(password, user.password) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getCpf(), user.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), password, getEmail(), getCpf());
    }
}
