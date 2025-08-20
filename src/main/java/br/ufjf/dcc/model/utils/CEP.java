package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidCEPException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEP {

    private String cep;

    public static CEP getInstance(String cep) {return new CEP(cep);}

    public CEP() {}

    private CEP(String cep) {
        if(!isValid(cep)){
            throw new InvalidCEPException("Invalid Format: " + cep);
        }
        this.cep = cep;
    }

    public String getCEP() {return this.cep;}

    private boolean isValid(String cep) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        String regex = "^\\d{5}-?\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cep);
        return matcher.matches();
    }

    @Override
    public String toString() {
        // Formata como NNNNN-NNN
        String numeros = cep.replaceAll("\\D", "");
        return numeros.substring(0, 5) + "-" + numeros.substring(5);
    }

}
