package ex05.view;

import ex05.controller.ThreadPing;

public class Main {
    public static void main(String[] args) {
        if(!os().contains("inux")) {
            System.err.println("Esse programa suporta apenas sistemas operacionais Linux");
            return;
        }

        final int NUMBER_OF_PINGS = 10;
        String [][] servers = {
                {"www.google.com.br", "Google"},
                {"www.uol.com.br", "UOL"},
                {"www.terra.com.br", "Terra"}
        };

        for(String[] server : servers) {
            ThreadPing threadPing = new ThreadPing(server[0], server[1], NUMBER_OF_PINGS);
            threadPing.start();
        }
    }

    private static String os(){
        return System.getProperty("os.name");
    }
}
