package br.ufjf.dcc.model;

import br.ufjf.dcc.model.exception.InvalidAccountException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import br.ufjf.dcc.model.repository.ContaRepository;
import lombok.Getter;

@Getter
public class Transferencia extends Operacao {

    private final transient Conta contaOrigem;
    private final transient Conta contaDestino;
    private final String titularContaDestino;
    private final double valor;

    private Transferencia(Conta origem, Conta destino, double valor){
        super();

        if(valor <= 0) {throw new InvalidValueException("Invalid value: " + valor);}
        if(valor > origem.getSaldo()) {throw new InvalidValueException("Insufficient balance: " + origem.getSaldo());}
        ContaRepository repository = new ContaRepository();
        if(!repository.findById(destino.getId())) {throw new InvalidAccountException("Not a valid bank account: " + destino.getId());}

        this.contaOrigem = origem;
        this.contaDestino = destino;
        this.valor = valor;
        this.titularContaDestino = destino.getCliente().getNome().toString();
    }

    public static Transferencia getInstance(Conta origem, Conta destino, double valor){
        return new Transferencia(origem, destino, valor);
    }

    public void realiza(){
        double saldoOrigem = contaOrigem.getSaldo();
        contaOrigem.setSaldo(saldoOrigem - valor); //Atualiza o saldo na conta de origem

        double saldoDestino = contaDestino.getSaldo();
        contaDestino.setSaldo(saldoDestino + valor); //Atualiza o saldo na conta de destino
    }
}
