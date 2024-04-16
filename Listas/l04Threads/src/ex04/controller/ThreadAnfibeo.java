package ex04.controller;
public class ThreadAnfibeo extends Thread {
    private final int id;
    private final int maxJumpLength;
    private final int raceLength;
    private int distanceLeft;
    private static int finished;


    public ThreadAnfibeo(int id, int jumpLength, int raceLength) {
        this.id = id;
        this.maxJumpLength = jumpLength;
        this.raceLength = raceLength;
        this.distanceLeft = raceLength;
    }

    @Override
    public void run() {
        while(distanceLeft > 0) {
            int jumpLength = jump();
            System.out.println(
                    "sapo " + id + " pulou " + jumpLength + " metros; percorreu " +
                    (raceLength - distanceLeft) + " metros"
            );
        }
        System.out.println(
                "sapo " + id + " chegou em " + ++finished + "° posição"
        );
    }

    private int jump(){
        int jumpLength = (int) (Math.random() * maxJumpLength);
        distanceLeft -= jumpLength;
        return jumpLength;
    }
}
