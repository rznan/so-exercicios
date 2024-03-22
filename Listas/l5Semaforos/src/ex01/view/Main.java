package ex01.view;

import ex01.controller.TransactionThread;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int NumberOfThreads = 21;
        final Semaphore semaphore = new Semaphore(1);
        for(int i = 0; i < NumberOfThreads; i++) {
            TransactionThread thread = new TransactionThread(i, semaphore);
            thread.start();
        }
    }
}
