package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidAccountException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import br.ufjf.dcc.model.repository.ContaRepository;

public class Deposito extends Operacao{

    private final transient Conta contaOrigem;
    private final transient Conta contaDestino;
    private final double valor;

    private Deposito(Conta origem, Conta destino, double valor){
        super();
        if(valor <= 0) {throw new InvalidValueException("Invalid value: " + valor);}
        ContaRepository repository = new ContaRepository();
        if(!repository.findById(destino.getId())) {throw new InvalidAccountException("Not a valid bank account: " + destino.getId());}

        this.contaOrigem = origem;
        this.valor = valor;
        this.contaDestino = destino;
    }

    public static Deposito getInstance(Conta origem, Conta destino, double valor) {return new Deposito(origem, destino, valor);}

    public void realiza(){
        double saldoAtual = this.contaDestino.getSaldo();
        this.contaDestino.setSaldo(saldoAtual + valor);
    }
}
