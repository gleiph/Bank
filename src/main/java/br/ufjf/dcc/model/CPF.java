package br.ufjf.dcc.model;

import java.util.regex.Pattern;

public class CPF {

    private static Pattern CPFFormat =
            Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    public boolean isInvalidCPF(String s) {
        if(!CPFFormat.matcher(s).matches())
            return false;

        return true;
    }
}
