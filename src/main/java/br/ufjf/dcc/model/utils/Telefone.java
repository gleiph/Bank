package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidPhoneNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefone {

    private final String numero;

    public static Telefone getInstance(String numero) {return new Telefone(numero);}

    public Telefone(String numero) {
        if(!isValid(numero)){
            throw new InvalidPhoneNumberException("Invalid phone number: " + numero);
        }
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public boolean isValid(String numero) {
        String regex = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$";  //Padrão para telefones no Brasil
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }

}