package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Cliente extends Usuario{

    private List<UUID> contas_id;

    public Cliente(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        super(nome, data, cpf, cep, email, senha);
        contas_id = new ArrayList<>();
    }

    public static Cliente getInstance(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        return new Cliente(nome, data, cpf, cep, email, senha);
    }

    private void setContas(List<UUID> contas){
        this.contas_id = contas;
    }

    public void addContaId(UUID conta){
        this.contas_id.add(conta);
    }
}
