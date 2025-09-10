package br.ufjf.dcc;

import br.ufjf.dcc.view.AdminView;
import br.ufjf.dcc.view.LoginView;

public class Main {

    public static void main(String[] args) {

        //LoginView view = new LoginView();
        AdminView view = new AdminView();

        view.desenha();
    }
}