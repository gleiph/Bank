package br.ufjf.dcc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Conta {

    private final UUID id;
    private final Cliente cliente;
    @Setter
    private double saldo;

    public Conta(UUID id, Cliente cliente, double saldo){
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public static Conta getInstance(UUID id, Cliente cliente, double saldo) {return new Conta(id, cliente, saldo);}

}
