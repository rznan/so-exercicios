package ex03.controller;

import java.util.concurrent.Semaphore;

public class Triatlo {
    public final Semaphore semaforoArmas;
    public final Semaphore semaforoChegada;
    final int corridaLength;
    final int numeroDeTirosPermitidos;
    final int ciclismoLength;
    AtletaThread[] participantes = null;

    public Triatlo(int corridaLength,
                   int numeroDeArmas,
                   int numeroDeTirosPermitidos,
                   int ciclismoLength){
        this.corridaLength = corridaLength;
        this.numeroDeTirosPermitidos = numeroDeTirosPermitidos;
        this.ciclismoLength = ciclismoLength;

        this.semaforoArmas = new Semaphore(numeroDeArmas);
        this.semaforoChegada = new Semaphore(1);
    }

    public void setParticipantes(AtletaThread[] participantes) {
        this.participantes = participantes;
    }

    public void comecarTriatlo() {
        if(participantes != null) {
            if(participantes.length > 0) {
                for(AtletaThread atleta : participantes) {
                    atleta.start();
                }
            }
            else {
                System.out.println("Não existem participantes.");
            }
        }
        else {
            System.out.println("Não existem participantes.");
        }
    }

    protected void mostrarPlacares() {
        sortParticipantes();
        int count = 1;
        for(AtletaThread atleta : participantes) {
            System.out.println(
                "(" + atleta.id + ") ---> " + count++ + "° posição!: " + atleta.pontuacaoTotal + "pts"
            );
        }
    }

    private void sortParticipantes() {
        int quant = participantes.length;
        for(int i = 0; i < quant; i++) {
            for(int j = 0; j < quant-1; j++) {
                if(participantes[j].pontuacaoTotal < participantes[j+1].pontuacaoTotal) {
                    AtletaThread aux = participantes[j];
                    participantes[j] = participantes[j+1];
                    participantes[j+1] = aux;
                }
            }
        }
    }
}
