package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import lombok.Getter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Usuario {

    @Getter
    private final UUID id;
    @Getter
    private final Nome nome;
    @Getter
    private final Date data_nasc;
    @Getter
    private final CPF cpf;
    @Getter
    private Telefone telefone;
    @Getter
    private Endereco endereco;
    @Getter
    private Email email;
    private Senha senha;

    public Usuario(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        Objects.requireNonNull(nome, "Name cannot be null");
        Objects.requireNonNull(data, "Birthdate cannot be null");
        Objects.requireNonNull(cpf, "CPF cannot be null");
        Objects.requireNonNull(telefone, "Phone number cannot be null");
        Objects.requireNonNull(endereco, "CEP cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(senha, "Password cannot be null");

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.data_nasc = data;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
        this.id = UUID.randomUUID();
        this.nome = null;
        this.data_nasc = null;
        this.cpf = null;
    }

    public boolean checkPassword(String senha) {
        return senha != null && Senha.hashPassword(senha).equals(this.senha.getSenha());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario user = (Usuario) o;
        return  Objects.equals(getNome(), user.getNome()) &&
                Objects.equals(getData_nasc(), user.getData_nasc()) &&
                Objects.equals(getCpf(), user.getCpf()) &&
                Objects.equals(getTelefone(), user.getTelefone()) &&
                Objects.equals(getEndereco(), user.getEndereco()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(senha, user.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getData_nasc(), getCpf(), getTelefone(), getEndereco(), getEmail(), senha);
    }
}
