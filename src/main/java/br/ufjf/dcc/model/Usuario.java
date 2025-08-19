package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import lombok.Getter;

import java.util.Date;

@Getter
public class Usuario {

    private final Nome nome;
    private final Date data_nasc;
    private final CPF cpf;
    private Endereco cep;
    private Email email;
    private Senha senha;

    public static Usuario getInstance(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha) {
        return new Usuario(nome, data, cpf, cep, email, senha);
    }

    public Usuario(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        this.nome = nome;
        this.data_nasc = data;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
    }

}
