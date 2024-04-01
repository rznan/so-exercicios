package ex03.view;

import ex03.controller.AtletaThread;
import ex03.controller.Triatlo;

public class Main {
    public static void main(String[] args) {
        final int NUMERO_PARTICIPANTES = 25;
        Triatlo prova = new Triatlo(
                3000,
                5,
                3,
                5000
        );

        AtletaThread[] atletas = new AtletaThread[NUMERO_PARTICIPANTES];
        for(int i = 0; i < NUMERO_PARTICIPANTES; i++) {
            atletas[i] = new AtletaThread(i+1, prova);
        }

        prova.setParticipantes(atletas);
        prova.comecarTriatlo();
    }
}
