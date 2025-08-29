package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Usuario{

    @JsonIgnore
    private List<Conta> contas;

    private Cliente(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        super(nome, data, cpf, telefone, endereco, email, senha);
        contas = new ArrayList<>();
    }

    public static Cliente getInstance(Nome nome, Date data, CPF cpf, Telefone telefone, Endereco endereco, Email email, Senha senha){
        return new Cliente(nome, data, cpf, telefone, endereco, email, senha);
    }

    public void addContaId(Conta conta){
        this.contas.add(conta);
    }
}
