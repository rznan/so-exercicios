package controller;

import model.Dado;

import java.util.concurrent.Semaphore;

public class ThreadCompetidor extends Thread {
    static int numeroJogadas;
    int id;
    Dado[] dados;
    int pontos;
    int numeroUltimaJogada;
    public Boolean isTurno;
    Semaphore semaforoDados;

    public ThreadCompetidor(int id, Dado[] dados, Semaphore semaforoDados) {
        this.id = id;
        this.dados = dados;
        this.semaforoDados = semaforoDados;
        this.isTurno = false;
    }

    @Override
    public void run() {
        while (pontos < 5) {
            try {
                semaforoDados.acquire();
                if(isTurno) {
                    numeroUltimaJogada = numeroJogadas++;
                    System.out.printf("\n\n" + this);
                    int valorJogadas = 0;
                    for(Dado d : dados) {
                        valorJogadas += d.jogar(true);
                    }
                    int pontuacaoTurno = valorJogadas == 7 || valorJogadas == 11 ? 1 : 0;
                    this.pontos += pontuacaoTurno;
                    System.out.printf("\nTotal: %02d | + %02d pts", valorJogadas, pontuacaoTurno);
                    if(pontos >= 5) {
                        System.out.print(" --> FEZ 5 PONTOS!!!");
                    }
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                semaforoDados.release();
                isTurno = false;
            }
        }
    }

    public int getPontos() {
        return pontos;
    }

    public String toString() {
        return String.format("COMPETIDOR: %02d | pts: %02d | jogada: %04d", id, pontos, (numeroUltimaJogada));
    }
}
