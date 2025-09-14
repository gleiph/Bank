package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidValueException;
import lombok.Getter;

@Getter
public class Saque extends Operacao{

    private final transient Conta contaOrigem;
    private final double valor;

    private Saque(Conta origem, double valor) {
        super();
        if(valor <= 0) {throw new InvalidValueException("Invalid value: "+valor);}
        if(valor > origem.getSaldo()) {throw new InvalidValueException("Insufficient balance: "+origem.getSaldo());}
        this.contaOrigem = origem;
        this.valor = valor;
    }

    public static Saque getInstance(Conta origem, double valor) {return new Saque(origem, valor);}

    public void realiza(){
        double saldoAtual = contaOrigem.getSaldo();
        contaOrigem.setSaldo(saldoAtual - valor);
    }
}
