package ex01.model;

import java.util.concurrent.Semaphore;

public class Corrida {
    final int id;
    final int extensao_corredor;
    public final Semaphore semaforoTocha;
    final int distancia_tocha;
    boolean tochaPega = false;

    public final Semaphore semaforoPedra;
    final int distancia_pedra;
    boolean pedraPega = false;

    public final int quantidadePortas;

    boolean[] portas;
    boolean[] statusPorta;
    public final Semaphore semaforoPorta;

    public Corrida(int id,
                   int extensaoCorredor,
                   Semaphore semaforoTocha,
                   int distanciaTocha,
                   Semaphore semaforoPedra,
                   int distanciaPedra,
                   Semaphore semaforoPorta,
                   int quantidadePortas) {
        this.id = id;
        extensao_corredor = extensaoCorredor;
        this.semaforoTocha = semaforoTocha;
        distancia_tocha = distanciaTocha;
        this.semaforoPedra = semaforoPedra;
        distancia_pedra = distanciaPedra;
        this.semaforoPorta = semaforoPorta;
        this.quantidadePortas = quantidadePortas;


        // inicia as portas
        portas = new boolean[quantidadePortas];
        statusPorta = new boolean[quantidadePortas];
        int portaCerta = (int) (Math.random() * quantidadePortas);

        for(int i = 0; i < quantidadePortas; i++) {
            portas[i] = i == portaCerta;
            statusPorta[i] = false;
        }
    }

    public int getDistancia_tocha() { return distancia_tocha; }
    public int getDistancia_pedra() { return distancia_pedra; }
    public int getExtensao_corredor() { return extensao_corredor; }
    public boolean isTochaDisponivel() { return !tochaPega; }
    public void setTochaPega() {
        this.tochaPega = true;
    }
    public boolean isPedraDisponivel() { return !pedraPega; }
    public void setPedraPega() {
        this.pedraPega = true;
    }

    public boolean isPortaAberta(int n) {
        if(n >= 0 && n < statusPorta.length) {
            return statusPorta[n];
        } else {
            // porta inválida
            return true;
        }
    }

    public boolean openDoor(int n) {
        if(n >= 0 && n < statusPorta.length) {
            statusPorta[n] = true;
            return portas[n];
        }
        else {
            // porta inválida
            return false;
        }
    }

}
