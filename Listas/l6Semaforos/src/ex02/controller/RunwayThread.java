package ex02.controller;

import java.util.Queue;
import java.util.concurrent.Semaphore;


public class RunwayThread extends Thread {
    public final int id;
    public final String name;
    public Queue<Plane> planeThreadQueue;
    public Semaphore planePickerSemaphore;

    public RunwayThread(int id,
                        String name,
                        Queue<Plane> planeThreadQueue,
                        Semaphore planePickerSemaphore) {
        this.id = id;
        this.name = name;
        this.planeThreadQueue = planeThreadQueue;
        this.planePickerSemaphore = planePickerSemaphore;
    }

    @Override
    public void run() {
        while(!planeThreadQueue.isEmpty()) {
            Plane p = null;
            try {
                planePickerSemaphore.acquire();
                if(!planeThreadQueue.isEmpty())
                {
                    p = planeThreadQueue.poll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                planePickerSemaphore.release();
            }
            if(p != null){
                System.out.println(" ---- Avião " + p.id + " decola na pista " + name);
                p.runwayName = name;
                p.startFlight();
                System.out.println(" ---- Pista " + name + " liberada");
            }

        }
        System.out.println(" ---- Não existem mais aviões para pista " + name);
    }
}
