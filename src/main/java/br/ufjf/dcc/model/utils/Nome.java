package br.ufjf.dcc.model.utils;

import lombok.Getter;
import br.ufjf.dcc.model.exception.InvalidNameException;

import java.util.Objects;

@Getter
public class Nome {

    private final String nome;
    private final String sobrenome;

    public static Nome getInstance(String nome, String sobrenome) {return new Nome(nome, sobrenome);}

    private Nome(String nome, String sobrenome) {
        if(!isValid(nome, sobrenome)){
            throw new InvalidNameException("Invalid name: " + nome + " " + sobrenome);
        }
        Objects.requireNonNull(nome, "First name cannot be null");
        Objects.requireNonNull(sobrenome, "Second name cannot be null");

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nome nome1 = (Nome) o;
        return Objects.equals(getNome(), nome1.getNome()) && Objects.equals(getSobrenome(), nome1.getSobrenome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }
}
