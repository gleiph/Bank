package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidValueException;

public class Saque extends Operacao{

    private final double valor;

    private Saque(Conta origem, double valor) {
        super(origem);
        if(valor <= 0) {throw new InvalidValueException("Invalid value: "+valor);}
        if(valor > origem.getSaldo()) {throw new InvalidValueException("Insufficient balance: "+origem.getSaldo());}
        this.valor = valor;
    }

    public static Saque getInstance(Conta origem, double valor) {return new Saque(origem, valor);}

    public void realiza(){
        Conta origem = getContaOrigem();
        double saldoAtual = origem.getSaldo();
        origem.setSaldo(saldoAtual - valor);
    }
}
