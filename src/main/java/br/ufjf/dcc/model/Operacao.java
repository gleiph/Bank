package br.ufjf.dcc.model;

import lombok.Getter;

import java.util.UUID;

@Getter
abstract class Operacao {

    private final UUID id;
    private final Conta contaOrigem;

    public Operacao(Conta origem) {
        this.id = UUID.randomUUID();
        this.contaOrigem = origem;
    }

    public abstract void realiza();
}
