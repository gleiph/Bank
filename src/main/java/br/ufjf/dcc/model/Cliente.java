package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

    private List<Conta> contas;

    private Cliente(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        super(nome, data, cpf, cep, email, senha);
        contas = new ArrayList<>();
    }

    public static Cliente getInstance(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        return new Cliente(nome, data, cpf, cep, email, senha);
    }

    public void addContaId(Conta conta){
        this.contas.add(conta);
    }
}
