package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidCPFException;

import java.util.Objects;
import java.util.regex.Pattern;

public class CPF {

    private final String cpf;

    public String getCpf() {
        return cpf;
    }

    private static final Pattern CPF_PATTERN =
            Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");


    public static CPF getInstance(String cpf){
        return new CPF(cpf);
    }


    private CPF(String cpf) {
        if(isInvalidFormat(cpf)) {
            throw new InvalidCPFException("Invalid format: " + cpf);
        }
        if(!isValidCPF(cpf)) {
            throw new InvalidCPFException("Invalid CPF: " + cpf);
        }
        this.cpf = cpf;
    }

    private boolean isValidCPF(String cpf) {
        if(isInvalidFormat(cpf))
            return false;

        return validateFirstVerifierDigit(cpf)  &&
                validateSecondVerifierDigit(cpf);
    }

    private static boolean isInvalidFormat(String cpf) {
        return !CPF_PATTERN.matcher(cpf).matches();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf1 = (CPF) o;
        return Objects.equals(getCpf(), cpf1.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCpf());
    }

    @Override
    public String toString() {
        return cpf;
    }
}
