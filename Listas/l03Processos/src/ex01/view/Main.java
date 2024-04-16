package ex01.view;

import ex01.controller.RedesController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        var redes = new RedesController();
        String[] botoes = {"ip", "ping", "finalizar"};

        int opcao;
        do {
            opcao = JOptionPane.showOptionDialog(null,
                    "Selecione uma opção:",
                    "Título da caixa de diálogo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    botoes,
                    botoes[0]
            );

            switch (opcao){
                case 0: redes.ip();
                case 1: redes.ping();
                case 2: break;
                default: System.err.println("Algo deu errado...");
            }
            System.out.println("\n\n");
        } while (opcao != 2);
    }
}
