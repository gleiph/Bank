package br.ufjf.dcc.model.utils;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Endereco {

    private final String rua;
    private final int numero;
    private final String bairro;
    private final String cidade;
    private final String estado;
    private final CEP cep;

    private Endereco(String rua, int numero, String bairro, String cidade, String estado, CEP cep){
        Objects.requireNonNull(rua, "Street cannot be null");
        Objects.requireNonNull(bairro, "Neighbourhood cannot be null");
        Objects.requireNonNull(cidade, "City cannot be null");
        Objects.requireNonNull(estado, "State cannot be null");
        Objects.requireNonNull(cep, "CEP cannot be null");

        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public static Endereco getInstance(String rua, int numero, String bairro, String cidade, String estado, CEP cep){
        return new Endereco(rua, numero, bairro, cidade, estado, cep);
    }

    @Override
    public String toString() {
        return (rua + " " + numero + ", " + bairro + " - " + cidade + ", " + estado);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco1 = (Endereco) o;
        return Objects.equals(getRua(), endereco1.getRua()) &&
                Objects.equals(getNumero(), endereco1.getNumero()) &&
                Objects.equals(getBairro(), endereco1.getBairro()) &&
                Objects.equals(getCidade(), endereco1.getCidade()) &&
                Objects.equals(getEstado(), endereco1.getEstado()) &&
                Objects.equals(getCep(), endereco1.getCep());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRua());
    }
}


