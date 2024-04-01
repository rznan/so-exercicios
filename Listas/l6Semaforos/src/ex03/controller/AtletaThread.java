package ex03.controller;


public class AtletaThread extends Thread{
    private final Triatlo prova;
    public static int chegadaCount = 0;
    public int pontuacaoTotal = 0;
    public final int id;

    public AtletaThread(int id, Triatlo prova) {
        this.id = id;
        this.prova = prova;
    }

    @Override
    public void run() {
        corrida();
        tiroAoAlvo();
        ciclismo();

        try {
            prova.semaforoChegada.acquire();
            pontuacaoTotal += 250 - chegadaCount++ * 10;
            if(chegadaCount+1 == prova.participantes.length) {
                prova.mostrarPlacares();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            prova.semaforoChegada.release();
        }
    }

    private void ciclismo() {
        int distanciaPercorrida = 0;
        while(distanciaPercorrida < prova.ciclismoLength) {
            System.out.println(
                    "Atleta: " + id +
                    " | Prova: CICLISMO" +
                    " | Distancia: " + distanciaPercorrida
            );
            distanciaPercorrida += (int) (Math.random() * 5) + 20;
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void tiroAoAlvo() {
        System.out.println(
                "Atleta: " + id +
                " | Prova: TIRO AO ALVO" +
                " | Status: EM FILA"
        );
        try {
            prova.semaforoArmas.acquire();

            System.out.println(
                    "Atleta: " + id +
                    " | Prova: TIRO AO ALVO" +
                    " | Status: ATIRANDO"
            );
            for(int i = 0; i < prova.numeroDeTirosPermitidos; i++) {
                try {
                    int duracao = (int) (Math.random() * 2500) + 500;
                    sleep(duracao);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int pontuacao = (int) (Math.random() * 10);
                System.out.println(
                        "Atleta: " + id +
                        " | Prova: TIRO AO ALVO" +
                        " | N° Tiro: " + (i+1) +
                        " | Pontuação: " + pontuacao
                );

                pontuacaoTotal += pontuacao;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            prova.semaforoArmas.release();
        }
    }

    private void corrida() {
        int distanciaPercorrida = 0;
        while(distanciaPercorrida < prova.corridaLength) {
            System.out.println(
                    "Atleta: " + id +
                    " | Prova: CORRIDA" +
                    " | Distancia: " + distanciaPercorrida
            );
            distanciaPercorrida += (int) (Math.random() * 10) + 30;
            try {
                sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
