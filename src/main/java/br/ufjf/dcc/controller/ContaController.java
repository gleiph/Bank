package br.ufjf.dcc.controller;

import br.ufjf.dcc.model.Conta;
import br.ufjf.dcc.model.Deposito;
import br.ufjf.dcc.model.Saque;
import br.ufjf.dcc.model.Transferencia;
import br.ufjf.dcc.model.exception.InvalidAccountException;
import br.ufjf.dcc.model.exception.InvalidValueException;
import br.ufjf.dcc.model.repository.ContaRepository;
import br.ufjf.dcc.view.ClienteView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

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
            } catch (InvalidValueException | NumberFormatException error) {
                tela.showErrorMessage(error.getMessage());
            }
        }
    }

    public static class RealizaDeposito implements ActionListener {

        private final ClienteView tela;

        public RealizaDeposito(ClienteView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Conta contaCliente = tela.getContaCliente();
                double valorDeposito = Double.parseDouble(tela.getTfValorDeposito().getText());
                UUID idConta = UUID.fromString(tela.getTfContaDeposito().getText());

                ContaRepository repository = new ContaRepository();

                Conta contaDestino = repository.findById(idConta);

                List<Conta> contas = repository.findAll();
                int indexOrigem = contas.indexOf(contaCliente);
                int indexDestino = contas.indexOf(contaDestino);

                Deposito novoDeposito;

                if(indexOrigem == indexDestino){
                    novoDeposito = Deposito.getInstance(contaCliente, contaCliente, valorDeposito); //Força a atualização do saldo na tela em tempo de execução
                    novoDeposito.realiza();
                }
                else{
                    novoDeposito = Deposito.getInstance(contaCliente, contaDestino, valorDeposito);
                    novoDeposito.realiza();
                }

                contaCliente.getDepositos().add(novoDeposito);
                contaDestino.getDepositos().add(novoDeposito);

                contas.set(indexDestino, contaDestino);
                contas.set(indexOrigem, contaCliente);
                repository.save(contas);

                System.out.println("Successful deposit");
                tela.showSucessMessage("Depósito realizado com sucesso.");
            } catch (InvalidValueException | IllegalArgumentException | InvalidAccountException error) {
                tela.showErrorMessage(error.getMessage());
            }
        }
    }

    public static class RealizaTransferencia implements ActionListener {

        private final ClienteView tela;

        public RealizaTransferencia(ClienteView tela) {
            this.tela = tela;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Conta contaCliente = tela.getContaCliente();
                double valorTransferencia = Double.parseDouble(tela.getTfValorTransferencia().getText());
                UUID idConta = UUID.fromString(tela.getTfContaTransferencia().getText());

                ContaRepository repository = new ContaRepository();

                Conta contaDestino = repository.findById(idConta);

                List<Conta> contas = repository.findAll();
                int indexOrigem = contas.indexOf(contaCliente);
                int indexDestino = contas.indexOf(contaDestino);

                Transferencia novaTransferencia;

                novaTransferencia = Transferencia.getInstance(contaCliente, contaDestino, valorTransferencia);
                novaTransferencia.realiza();

                contaCliente.getTransferencias().add(novaTransferencia);
                contaDestino.getTransferencias().add(novaTransferencia);

                contas.set(indexDestino, contaDestino);
                contas.set(indexOrigem, contaCliente);
                repository.save(contas);

                System.out.println("Successful transfer");
                tela.showSucessMessage("Transferência realizada com sucesso.");
            } catch (InvalidValueException | IllegalArgumentException | InvalidAccountException error) {
                tela.showErrorMessage(error.getMessage());
            }
        }
    }
}
