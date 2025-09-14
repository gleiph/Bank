package br.ufjf.dcc.model;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public abstract class Operacao {

    private final UUID id;
    private final Date data;

    public Operacao() {
        this.id = UUID.randomUUID();
        this.data = new Date();
    }

    public abstract void realiza();
}
