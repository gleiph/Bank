package br.ufjf.dcc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Conta {

    private final UUID id;
    @JsonIgnore
    private final Usuario cliente;
    @Setter
    private double saldo;

    private Conta(Usuario cliente, double saldo){
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.saldo = saldo;
    }

    private Conta() {
        this.id = UUID.randomUUID();
        this.cliente = null;
    }

    public static Conta createEmpty() {return new Conta();}

    public static Conta getInstance(UUID id, Usuario cliente, double saldo) {return new Conta(cliente, saldo);}
}
