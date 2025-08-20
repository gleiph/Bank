package br.ufjf.dcc.model;

import br.ufjf.dcc.model.utils.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Usuario {

    private final UUID id;
    private final Nome nome;
    private final Date data_nasc;
    private final CPF cpf;
    private Endereco cep;
    private Email email;
    private Senha senha;

    public Usuario(Nome nome, Date data, CPF cpf, Endereco cep, Email email, Senha senha){
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.data_nasc = data;
        this.cpf = cpf;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {
        this.id = UUID.randomUUID();
        this.nome = null;
        this.data_nasc = null;
        this.cpf = null;
    }
}
