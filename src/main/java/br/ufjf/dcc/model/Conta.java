package br.ufjf.dcc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Conta {

    private final UUID id;
    private final Usuario cliente;
    @Setter
    private double saldo;
    private List<Saque> saques;
    private List<Deposito> depositos;
    private List<Transferencia> transferencias;

    private Conta(Usuario cliente){
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.saldo = 0;
        this.saques = new ArrayList<>();
        this.depositos = new ArrayList<>();
        this.transferencias = new ArrayList<>();
    }

    private Conta() {
        this.id = UUID.randomUUID();
        this.cliente = null;
    }

    public static Conta createEmpty() {return new Conta();}

    public static Conta getInstance(Usuario cliente) {return new Conta(cliente);}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return  Objects.equals(getId(), conta.getId()) &&
                Objects.equals(getCliente(), conta.getCliente()) &&
                Objects.equals(getSaldo(), conta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
