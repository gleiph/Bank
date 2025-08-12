package br.ufjf.dcc.model.utils;

import br.ufjf.dcc.model.exception.InvalidEmailException;
import org.apache.commons.validator.routines.EmailValidator;

public class Email {

    private final String email;

    public String getEmail() {
        return email;
    }

    public static Email getInstance(String email){
        return new Email(email);
    }

    private Email(String email) {

        if(isInvalidEmail(email)) {
            throw new InvalidEmailException("Invalid e-mail: " + email);
        }
        this.email = email;
    }

    private boolean isInvalidEmail(String email) {
        return !EmailValidator.getInstance().isValid(email);
    }
}
