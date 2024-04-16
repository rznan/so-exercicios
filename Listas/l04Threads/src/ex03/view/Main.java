package ex03.view;

import ex03.controller.ThreadVetor;

public class Main {
    public static void main(String[] args) {
        final int LENGTH = 1000;
        final int MIN_SIZE = 1;
        final int MAX_SIZE = 100;

        int[] arr = new int[LENGTH];
        for(int i = 0; i < LENGTH; i++) {
            arr[i] = (int) (Math.random() * (MAX_SIZE - MIN_SIZE)) + MIN_SIZE;
        }
        ThreadVetor timerFor = new ThreadVetor(1, arr);
        ThreadVetor timerForEach = new ThreadVetor(2, arr);

        timerFor.start();
        timerForEach.start();
    }
}
