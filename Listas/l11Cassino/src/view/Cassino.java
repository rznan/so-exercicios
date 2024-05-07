package view;

import controller.ThreadCompetidor;
import datastructures.genericQueue.Queue;
import model.Dado;

import java.util.concurrent.Semaphore;

public class Cassino {
    static final int numeroCompetidores = 10;
    public static void main(String[] args) {
        Dado[] dados = criarDados();
        Semaphore semaforoDados = new Semaphore(1);
        Queue<ThreadCompetidor> competidores = prepararCompetidores(dados, semaforoDados);
        Queue<ThreadCompetidor> competidoresFinalizados = new Queue<>();

        rodarJogo(competidores, competidoresFinalizados);
        System.out.println("\n");
        mostrarPlacares(competidoresFinalizados);
    }

    private static void mostrarPlacares(Queue<ThreadCompetidor> competidoresFinalizados) {
        Queue<Float> premios = new Queue<>();
        premios.insert(5000f);
        premios.insert(4000f);
        premios.insert(3000f);
        int cont=0;
        while(!competidoresFinalizados.isEmpty()) {
            try {
                ThreadCompetidor t = competidoresFinalizados.remove();
                System.out.print(++cont + "° LUGAR:\t" + t);
                if(!premios.isEmpty()) {
                    System.out.printf(" <-- GANHOU: R$%.2f", premios.remove());
                }
                System.out.println();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void rodarJogo(Queue<ThreadCompetidor> competidores, Queue<ThreadCompetidor> competidoresFinalizados) {
        while(!competidores.isEmpty()) {
            try {
                ThreadCompetidor t = competidores.remove();
                t.isTurno = true;
                while(t.isTurno);
                if(t.getPontos() < 5) {
                    competidores.insert(t);
                } else {
                    competidoresFinalizados.insert(t);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Dado[] criarDados() {
        Dado[] dados = new Dado[2];
        for(int i = 0; i < 2; i++) {
            dados[i] = new Dado(i+1);
        }
        return dados;
    }

    private static Queue<ThreadCompetidor> prepararCompetidores(Dado[] dados, Semaphore s) {
        Queue<ThreadCompetidor> competidores = new Queue<>();
        for(int i = 0; i < numeroCompetidores; i++) {
            ThreadCompetidor tc = new ThreadCompetidor(i+1, dados, s);
            tc.start();
            competidores.insert(tc);
        }
        return competidores;
    }
}
