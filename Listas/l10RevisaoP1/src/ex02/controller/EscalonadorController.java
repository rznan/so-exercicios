package ex02.controller;

import java.util.concurrent.Semaphore;

public class EscalonadorController {
    ProcessThread[] processThreads;
    Semaphore executionSemaphore;

    public EscalonadorController(ProcessThread[] processes, Semaphore executionSemaphore) {
        this.processThreads = processes;
        this.executionSemaphore = executionSemaphore;
    }

    public void run() {
        sortByShortestJob();
        for(ProcessThread p : processThreads) {
            try {
                executionSemaphore.acquire();
                p.start();
            } catch (InterruptedException e ) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void sortByShortestJob() {
        quicksort(processThreads, 0, processThreads.length-1);
    }

    private void quicksort(ProcessThread[] processThreads, int i, int f) {
        if(i >= f) return;

        int pivot = divide(processThreads, i, f);

        quicksort(processThreads, i, pivot-1);
        quicksort(processThreads, pivot+1, f);
    }

    private int divide(ProcessThread[] processThreads, int i, int f) {
        int pivot = processThreads[i].SecRuntime, e = i+1, d = f;
        while(e <= d) {
            while(e <= d && processThreads[e].SecRuntime < pivot) {
                e++;
            }
            while(e <= d && processThreads[d].SecRuntime > pivot) {
                d--;
            }
            if(e <= d) {
                swap(processThreads, e++, d--);
            }
        }
        swap(processThreads, d, i);
        return d;
    }

    private void swap(ProcessThread[] processThreads, int e, int d) {
        ProcessThread aux = processThreads[e];
        processThreads[e] = processThreads[d];
        processThreads[d] = aux;
    }
}
