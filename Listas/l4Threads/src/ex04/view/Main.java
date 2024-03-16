package ex04.view;

import ex04.controller.ThreadAnfibeo;

public class Main {
    public static void main(String[] args) {
        final int FROG_AMOUNT = 5;
        final int MAX_JUMP_LENGTH = 7;
        final int RACE_LENGTH = 150;

        for (int i = 0; i < FROG_AMOUNT; i++){
            ThreadAnfibeo threadAnfibeo = new ThreadAnfibeo(i+1, MAX_JUMP_LENGTH, RACE_LENGTH);
            threadAnfibeo.start();
        }
    }
}
