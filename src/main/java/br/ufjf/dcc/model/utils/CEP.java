package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidCEPException;
import lombok.Getter;

import java.util.Objects;
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

    public String getCep() {return this.cep;}

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CEP cep1 = (CEP) o;
        return Objects.equals(getCep(), cep1.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCep());
    }
}
