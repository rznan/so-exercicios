package ex05.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {
    private final String serverAddress;
    private final String serverName;
    private final int pingAmount;

    public ThreadPing(String serverAddress, String serverName, int pingAmount){
        this.serverAddress = serverAddress;
        this.serverName = serverName;
        this.pingAmount = pingAmount;
    }

    @Override
    public void run() {
        try {
            double avg = processPingStatistics(getPingStatistics());
            System.out.println(serverName + ": " + avg + "ms");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private String getPingStatistics() {
        String[] pingCMD = {"ping", serverAddress, "-4", "-c", ""+pingAmount};

        try {
            Process p = Runtime.getRuntime().exec(pingCMD);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if(line.startsWith("rtt")) {
                    return line;
                }
            }
            return "";
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }

    private double processPingStatistics(String statisticsLine) throws Exception {
        if(!statisticsLine.isEmpty())
            return Integer.parseInt(statisticsLine.split("=")[1].split("/")[1]);
        else {
            throw new Exception("Não foi possível recuperar os dados do ping");
        }
    }
}
