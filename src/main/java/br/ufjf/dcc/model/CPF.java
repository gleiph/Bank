package br.ufjf.dcc.model;

import java.util.regex.Pattern;

public class CPF {

    private static final Pattern CPF_PATTERN =
            Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    public boolean isValidCPF(String cpf) {
        if(!isValidFormat(cpf))
            return false;

        return validateFirstVerifierDigit(cpf)  &&
                validateSecondVerifierDigit(cpf);
    }

    private static boolean isValidFormat(String cpf) {
        return CPF_PATTERN.matcher(cpf).matches();
    }

    private String cleanCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean validateFirstVerifierDigit(String cpf) {
        String cleanedCPF = cleanCPF(cpf);

        int weight = 10;//Initial weight for the first verifier digit
        int sum = 0;
        for(int i = 0; i < cleanedCPF.length() - 2; i++) {
            sum += Integer.parseInt(cleanedCPF.substring(i, i+1)) * weight--;
        }

        return sum*10%11 == firstVerifierDigit(cleanedCPF);
    }

    private static int firstVerifierDigit(String cleanedCPF) {
        return Integer.parseInt(cleanedCPF.substring(9, 10));
    }

    private boolean validateSecondVerifierDigit(String cpf) {
        String cleanedCPF = cleanCPF(cpf);

        int weight = 11;// Initial weight for the second verifier digit
        int sum = 0;
        for(int i = 0; i < cleanedCPF.length() - 1; i++) {
            sum += Integer.parseInt(cleanedCPF.substring(i, i+1)) * weight--;
        }

        return sum*10%11 == secondVerifierDigit(cleanedCPF);
    }

    private static int secondVerifierDigit(String cleanedCPF) {
        return Integer.parseInt(cleanedCPF.substring(10, 11));
    }
}
