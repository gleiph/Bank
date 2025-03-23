package br.ufjf.dcc.model;

import java.util.regex.Pattern;

public class CPF {

    private static final Pattern CPFFormat =
            Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    public boolean isInvalidCPF(String cpf) {
        if(!isValidFormat(cpf))
            return false;

        return validateFirstIdentifierDigit(cpf)  &&
                validateSecondIdentifierDigit(cpf);
    }

    private static boolean isValidFormat(String cpf) {
        return CPFFormat.matcher(cpf).matches();
    }

    private String cleanCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean validateFirstIdentifierDigit(String cpf) {
        String cleanedCPF = cleanCPF(cpf);

        int weight = 10;
        int value = 0;
        for(int i = 0; i < cleanedCPF.length() - 2; i++) {
            value += Integer.parseInt(cleanedCPF.substring(i, i+1)) * weight--;
        }

        return value*10%11 == Integer.parseInt(cleanedCPF.substring(9, 10));
    }

    private boolean validateSecondIdentifierDigit(String cpf) {
        String cleanedCPF = cleanCPF(cpf);

        int weight = 11;
        int value = 0;
        for(int i = 0; i < cleanedCPF.length() - 1; i++) {
            value += Integer.parseInt(cleanedCPF.substring(i, i+1)) * weight--;
        }

        return value*10%11 == Integer.parseInt(cleanedCPF.substring(10, 11));
    }
}
