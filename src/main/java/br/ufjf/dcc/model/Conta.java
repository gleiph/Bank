package br.ufjf.dcc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Conta {

    private final UUID id;
    @JsonIgnore
    private final Cliente cliente;
    @Setter
    private double saldo;

    private Conta(Cliente cliente, double saldo){
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.saldo = saldo;
    }

    private Conta() {
        this.id = UUID.randomUUID();
        this.cliente = null;
    }

    public static Conta createEmpty() {return new Conta();}

    public static Conta getInstance(UUID id, Cliente cliente, double saldo) {return new Conta(cliente, saldo);}
}
