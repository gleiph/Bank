package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;

import java.util.Date;

public class Gerente extends Usuario{

    private Gerente(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        super(nome, data, cpf, telefone, endereco, email, senha);
    }

    public static Gerente getInstance(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        return new Gerente(nome, data, cpf, telefone, endereco, email, senha);
    }
}
