package ex01.view;

import ex01.controller.ThreadCavaleiro;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int EXTENSAO_CORREDOR = 2000;
        final int NUMERO_CAVALEIROS = 4;
        final Semaphore SEMAFORO_TOCHA = new Semaphore(1);
        final Semaphore SEMAFORO_PEDRA = new Semaphore(1);
        final Semaphore SEMAFORO_PORTA = new Semaphore(1);
        final int VELOCIDADE_MINIMA_INICIAL = 2;
        final int VELOCIDADE_MAXIMA_INICIAL = 4;
        final int DISTANCIA_TOCHA = 500;
        final int DISTANCIA_PEDRA = 1500;

        for(int i = 0; i < NUMERO_CAVALEIROS; i++) {
            ThreadCavaleiro tc = new ThreadCavaleiro(
                    i,
                    EXTENSAO_CORREDOR,
                    VELOCIDADE_MINIMA_INICIAL,
                    VELOCIDADE_MAXIMA_INICIAL,
                    SEMAFORO_TOCHA,
                    DISTANCIA_TOCHA,
                    SEMAFORO_PEDRA,
                    DISTANCIA_PEDRA,
                    SEMAFORO_PORTA
            );
            tc.start();
        }
    }
}
