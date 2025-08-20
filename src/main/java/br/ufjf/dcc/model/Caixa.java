package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;

import java.util.Date;

public class Caixa extends Usuario{

    private Caixa(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        super(nome, data, cpf, cep, email, senha);
    }

    public static Caixa getInstance(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        return new Caixa(nome, data, cpf, cep, email, senha);
    }
}
