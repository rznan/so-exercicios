package ex02.controller;

import static java.lang.Thread.sleep;

public class Plane {
    public final int id;
    public String runwayName = "Deconhecida";

    public Plane(int id) {
        this.id = id;
    }

    public void startFlight() {
        executePhase("manobra", 300, 700 );
        executePhase("taxiar", 500, 1000);
        executePhase("decolagem", 600, 800);
        executePhase("afastamento", 300, 800);
    }

    private void executePhase(String phaseName,
                              int minPhaseTime,
                              int maxPhaseTime) {
        int duration = (int) (Math.random() * (maxPhaseTime - minPhaseTime)) + minPhaseTime;
        System.out.println("Avião: " + id + "| Pista: " + runwayName + " | Fase: " + phaseName + " | Duração: " + duration + "ms");
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
