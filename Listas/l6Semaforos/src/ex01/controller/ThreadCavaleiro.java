package ex01.controller;

import ex01.model.Corrida;

public class ThreadCavaleiro extends Thread {
    public final int id;
    Corrida corrida;
    int posicaoAtual;
    int velocidade_minima;
    int velocidade_maxima;

    public ThreadCavaleiro(int id, Corrida corrida, int velocidade_maxima, int velocidade_minima) {
        this.id = id;
        this.corrida = corrida;
        this.velocidade_maxima = velocidade_maxima;
        this.velocidade_minima = velocidade_minima;
        posicaoAtual = 0;
    }


    @Override
    public void run() {
        while (posicaoAtual < corrida.getExtensao_corredor()) {
            correr();
        }

        System.out.println("CAVALEIRO " + id + ", CHEGOU NAS PORTAS!");

        tryAbrirPorta();
    }

    private void correr() {
        int velocidadeAtual = getVelocidade();

        mover(velocidadeAtual);

        System.out.println("Cavaleiro " + id + " - vel: " + velocidadeAtual + " pos: " + posicaoAtual);

        if (corrida.isTochaDisponivel())
            if (posicaoAtual >= corrida.getDistancia_tocha()) {
                tryPegarTocha();
        }

        if (corrida.isPedraDisponivel())
            if (posicaoAtual >= corrida.getDistancia_pedra()) {
                tryPegarPedra();
        }
    }

    private void mover(int velocidadeAtual) {
        try {
            sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        posicaoAtual += velocidadeAtual;
    }

    private void tryAbrirPorta() {
        try {
            corrida.semaforoPorta.acquire();
            int tentativa;
            do {
                tentativa = (int) (Math.random() * corrida.quantidadePortas);
            } while (corrida.isPortaAberta(tentativa));

            if(corrida.openDoor(tentativa)){
                System.out.println("Cavaleiro " + id + ", abriu a " + (tentativa+1) + "a porta e VIVEU!");
            } else {
                System.out.println("Cavaleiro " + id + ", abriu a " + (tentativa+1) + "a porta e MORREU!");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            corrida.semaforoPorta.release();
        }
    }

    private void tryPegarPedra() {
        try {
            corrida.semaforoPedra.acquire();
            if(corrida.isPedraDisponivel()) {
                aumentarVelocidade();
                System.out.println("CAVALEIRO " + id + " PEGOU A PEDRA!");
                corrida.setPedraPega();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            corrida.semaforoPedra.release();
        }
    }

    private void tryPegarTocha() {
        try {
            corrida.semaforoTocha.acquire();
            if(corrida.isTochaDisponivel()) {
                aumentarVelocidade();
                System.out.println("CAVALEIRO " + id + " PEGOU A TOCHA!");
                corrida.setTochaPega();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            corrida.semaforoTocha.release();
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