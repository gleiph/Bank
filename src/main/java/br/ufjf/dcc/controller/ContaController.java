package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.Conta;
import br.ufjf.dcc.model.Saque;
import br.ufjf.dcc.model.exception.InvalidValueException;
import br.ufjf.dcc.model.repository.ContaRepository;
import br.ufjf.dcc.view.ClienteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContaController {

    public static class RealizaSaque implements ActionListener {

        private final ClienteView tela;

        public RealizaSaque(ClienteView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Conta contaCliente = tela.getContaCliente();
                double valorSaque = Double.parseDouble(tela.getTfValorSaque().getText());

                ContaRepository repository = new ContaRepository();
                List<Conta> contas = repository.findAll();
                int index = contas.indexOf(contaCliente);

                Saque novoSaque = Saque.getInstance(contaCliente, valorSaque);
                novoSaque.realiza();
                contaCliente.getSaques().add(novoSaque);

                contas.set(index, contaCliente);
                repository.save(contas);

                System.out.println("Successful withdraw");
                tela.showSucessMessage("Saque realizado com sucesso.");
            }
            catch(InvalidValueException | NumberFormatException error){
                tela.showErrorMessage(error.getMessage());
            }
        }
    }
}
