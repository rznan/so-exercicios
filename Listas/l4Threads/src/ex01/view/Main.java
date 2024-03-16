package ex01.view;

import ex01.controller.ThreadId;

public class Main {
    public static void main(String[] args) {
        final int QUANTIDADE = 5;
        for( int id = 0; id < QUANTIDADE; id++ ) {
            ThreadId thread = new ThreadId();
            thread.start();
        }
    }
}
