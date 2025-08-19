package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidPasswordException;
import lombok.Getter;

@Getter
public class Senha {

    private String senha;

    public static Senha getInstance(String senha) {return new Senha(senha);}

    public Senha(String senha){
        if(!isValid(senha)){
            throw new InvalidPasswordException("Invalid password: " + senha);
        }
        this.senha = senha;
    }

    private boolean isValid(String senha){
        return senha.length() >= 8;
    }
}
