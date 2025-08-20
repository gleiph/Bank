package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidValueException;

public class Transferencia extends Operacao {

    private final Conta contaDestino;
    private final double valor;

    private Transferencia(Conta origem, Conta destino, double valor){
        super(origem);

        if(valor <= 0) {throw new InvalidValueException("Invalid value: " + valor);}
        if(valor > origem.getSaldo()) {throw new InvalidValueException("Insufficient balance: " + origem.getSaldo());}
        //quando houver persistência, verificar se a conta de destino existe

        this.contaDestino = destino;
        this.valor = valor;
    }

    public static Transferencia getInstance(Conta origem, Conta destino, double valor){
        return new Transferencia(origem, destino, valor);
    }

    public void realiza(){
        Conta origem = getContaOrigem();

        double saldoOrigem = origem.getSaldo();
        origem.setSaldo(saldoOrigem - valor); //Atualiza o saldo na conta de origem

        double saldoDestino = contaDestino.getSaldo();
        contaDestino.setSaldo(saldoDestino + valor); //Atualiza o saldo na conta de destino
    }
}
