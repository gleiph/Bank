package br.ufjf.dcc.model.utils;

public record Adress(String rua, int numero, String bairro, String cidade, String estado, CEP cep) {

    @Override
    public String toString() {
        return (rua + " " + numero + ", " + bairro + " - " + cidade + ", " + estado);
    }
}


