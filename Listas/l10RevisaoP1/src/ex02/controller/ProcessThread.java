package ex02.controller;

import java.util.concurrent.Semaphore;

public class ProcessThread extends Thread {
    int id;
    String name;
    int SecRuntime;
    Semaphore executionSemaphore;

    public ProcessThread(int id, String name, Semaphore executionSemaphore) {
        this.id = id;
        this.name = name;
        this.SecRuntime = calculateRuntime();
        this.executionSemaphore = executionSemaphore;
    }

    @Override
    public void run() {
        try {
            System.out.println(this);
            sleep(SecRuntime * 100L);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            executionSemaphore.release();
        }
    }

    private int calculateRuntime() {
        return ((int) (Math.random() * 117) + 4);
    }

    @Override
    public String toString() {
        return String.format("[id: %02d | nome: %-15s | tempo execução: %03d seg.]",
                this.id,
                this.name,
                this.SecRuntime);
    }
}

