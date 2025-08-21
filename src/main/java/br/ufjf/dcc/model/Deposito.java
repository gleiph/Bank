package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidValueException;

public class Deposito extends Operacao{

    private final Conta contaDestino;
    private final double valor;

    private Deposito(Conta origem, Conta destino, double valor){
        super(origem);
        if(valor <= 0) {throw new InvalidValueException("Invalid value: " + valor);}
        //quando houver persistência, verificar se a conta de destino existe

        this.valor = valor;
        this.contaDestino = destino;
    }

    public static Deposito getInstance(Conta origem, Conta destino, double valor) {return new Deposito(origem, destino, valor);}

    public void realiza(){
        double saldoAtual = this.contaDestino.getSaldo();
        this.contaDestino.setSaldo(saldoAtual + valor);
    }
}
