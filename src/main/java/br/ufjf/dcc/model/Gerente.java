package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;

import java.util.Date;

public class Gerente extends Usuario{

    public Gerente(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        super(nome, data, cpf, cep, email, senha);
    }

    public static Gerente getInstance(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        return new Gerente(nome, data, cpf, cep, email, senha);
    }
}
