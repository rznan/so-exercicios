package ex01.controller;

import java.util.concurrent.Semaphore;

public class ThreadCavaleiro extends Thread {
    final int id;
    final int extensao_corredor;
    int posicaoAtual;
    int velocidade_minima;
    int velocidade_maxima;

    final Semaphore semaforoTocha;
    final int distancia_tocha;
    static boolean tochaPega = false;

    final Semaphore semaforoPedra;
    final int distancia_pedra;
    static boolean pedraPega = false;

    private static boolean[] portas;
    private static boolean[] statusPorta;
    final Semaphore semaforoPorta;

    public ThreadCavaleiro(int id, int distanciaFinal, int velocidade_minima, int velocidade_maxima, Semaphore semaforoTocha, int distancia_tocha, Semaphore semaforoPedra, int distancia_pedra, Semaphore semaforoPorta) {
        this.id = id;
        this.extensao_corredor = distanciaFinal;
        this.velocidade_minima = velocidade_minima;
        this.velocidade_maxima = velocidade_maxima;
        this.semaforoPedra = semaforoPedra;
        this.distancia_pedra = distancia_pedra;
        this.semaforoTocha = semaforoTocha;
        this.distancia_tocha = distancia_tocha;
        this.semaforoPorta = semaforoPorta;

        // inicia as portas
        int quantidadePortas = 4;
        portas = new boolean[quantidadePortas];
        statusPorta = new boolean[quantidadePortas];
        int portaCerta = (int) (Math.random() * quantidadePortas);

        for(int i = 0; i < quantidadePortas; i++) {
            portas[i] = i == portaCerta;
            statusPorta[i] = true;
        }

    }


    @Override
    public void run() {
        while (posicaoAtual < extensao_corredor) {
            int velocidadeAtual = getVelocidade();

            mover(velocidadeAtual);

            System.out.println("Cavaleiro " + id + " - vel: " + velocidadeAtual + " pos: " + posicaoAtual);

            if(!tochaPega && (posicaoAtual >= distancia_tocha)) {
                tryPegarTocha();
            }

            if(!pedraPega && (posicaoAtual >= distancia_pedra)) {
                tryPegarPedra();
            }

        }

        System.out.println("CAVALEIRO " + id + ", CHEGOU NAS PORTAS!");

        tryAbrirPorta();
    }

    private void tryAbrirPorta() {
        try {
            semaforoPorta.acquire();
            int tentativa;
            do {
                tentativa = (int) (Math.random() * 4);
            } while (!statusPorta[tentativa]);

            if(portas[tentativa]){
                System.out.println("Cavaleiro " + id + ", abriu a " + tentativa + "a porta e VIVEU!");
            } else {
                System.out.println("Cavaleiro " + id + ", abriu a " + tentativa + "a porta e MORREU!");
            }

            statusPorta[tentativa] = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoPorta.release();
        }
    }

    private void tryPegarPedra() {
        try {
            semaforoPedra.acquire();
            if(!pedraPega) {
                aumentarVelocidade();
                System.out.println("CAVALEIRO " + id + " PEGOU A PEDRA!");
                pedraPega = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoPedra.release();
        }
    }

    private void mover(int velocidadeAtual) {
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        posicaoAtual += velocidadeAtual;
    }

    private void tryPegarTocha() {
        try {
            semaforoTocha.acquire();
            if(!tochaPega) {
                aumentarVelocidade();
                System.out.println("CAVALEIRO " + id + " PEGOU A TOCHA!");
                tochaPega = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforoTocha.release();
        }
    }

    private void aumentarVelocidade() {
        velocidade_minima += 2;
        if(velocidade_minima > velocidade_maxima) {
            velocidade_maxima += 2;
        }
    }


    private int getVelocidade() {
         return (int) (Math.random() * (velocidade_maxima - velocidade_minima)) + velocidade_minima;
    }
}