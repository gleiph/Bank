package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidPhoneNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {

    private final String number;

    public static PhoneNumber getInstance(String number) {return new PhoneNumber(number);}

    public PhoneNumber(String number) {
        if(!isValid(number)){
            throw new InvalidPhoneNumberException("Invalid phone number: " + number);
        }
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean isValid(String number) {
        String regex = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$";  //Padrão para telefones no Brasil
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}