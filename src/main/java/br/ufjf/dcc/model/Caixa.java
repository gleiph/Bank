package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;

import java.util.Date;

public class Caixa extends Usuario{

    private Caixa(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        super(nome, data, cpf, telefone, endereco, email, senha);
    }

    public static Caixa getInstance(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        return new Caixa(nome, data, cpf, telefone, endereco, email, senha);
    }
}
