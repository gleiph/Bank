package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidPasswordException;
import lombok.Getter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Getter
public class Senha {

    private String senha;

    public static Senha getInstance(String senha) {return new Senha(senha);}

    private Senha(String senha){
        if(!isValid(senha)){
            throw new InvalidPasswordException("Invalid password: " + senha);
        }
        this.senha = hashPassword(senha);
    }

    private boolean isValid(String senha){
        return senha.length() >= 8;
    }

    public static String hashPassword(String password) {
        Objects.requireNonNull(password, "Password cannot be null");
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Senha senha1 = (Senha) o;
        return Objects.equals(getSenha(), senha1.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSenha());
    }

}
