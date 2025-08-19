package br.ufjf.dcc.model.utils;

import lombok.Getter;
import br.ufjf.dcc.model.exception.InvalidNameException;

@Getter
public class Nome {

    private final String nome;
    private final String sobrenome;

    public static Nome getInstance(String nome, String sobrenome) {return new Nome(nome, sobrenome);}

    public Nome(String nome, String sobrenome) {
        if(!isValid(nome, sobrenome)){
            throw new InvalidNameException("Invalid name: " + nome + " " + sobrenome);
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    private boolean isValid(String nome, String sobrenome)
    {
        return nome.length() > 1 && sobrenome.length() > 1;
    }

    @Override
    public String toString(){
        return (nome+" "+sobrenome);
    }
}
