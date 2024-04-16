package ex01.view;

import ex01.controller.ThreadCavaleiro;
import ex01.model.Corrida;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        // dados corrida
        final int EXTENSAO_CORREDOR = 2000;
        final Semaphore SEMAFORO_TOCHA = new Semaphore(1);
        final int DISTANCIA_TOCHA = 500;
        final Semaphore SEMAFORO_PEDRA = new Semaphore(1);
        final int DISTANCIA_PEDRA = 1500;
        final Semaphore SEMAFORO_PORTA = new Semaphore(1);

        // dados cavaleiros
        final int NUMERO_CAVALEIROS = 4;
        final int VELOCIDADE_MINIMA_INICIAL = 2;
        final int VELOCIDADE_MAXIMA_INICIAL = 4;

        Corrida corrida = new Corrida(
                1,
                EXTENSAO_CORREDOR,
                SEMAFORO_TOCHA,
                DISTANCIA_TOCHA,
                SEMAFORO_PEDRA,
                DISTANCIA_PEDRA,
                SEMAFORO_PORTA,
                4
        );

        for(int i = 0; i < NUMERO_CAVALEIROS; i++) {
            ThreadCavaleiro tc = new ThreadCavaleiro(
                    i,
                    corrida,
                    VELOCIDADE_MAXIMA_INICIAL,
                    VELOCIDADE_MINIMA_INICIAL
            );
            tc.start();
        }
    }
}
