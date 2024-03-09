package ex01.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedesController {

    private String os(){
        return System.getProperty("os.name");
    }

    private boolean isWindows(){
        return os().contains("Win");
    }

    private boolean isUnix(){
        return os().contains("nix");
    }

    public void ip() {
        String UnixCmd = "ip addr";
        String WinCmd = "IPCONFIG";
        String[] cmd;

        if(isUnix()) cmd = new String[] { UnixCmd };
        else if(isWindows()) cmd = new String[] { WinCmd };
        else cmd = null;

        if(!(cmd == null)) {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder outputBuilder = new StringBuilder();

                System.out.println("Configuração de IP");

                // Não consegui ver o ipv4 no linux, portanto só funciona me windows
                String line;
                // Mostra apenas adaptadores com IPv4 e o próprio IPv4
                while ((line = reader.readLine()) != null) {
                    if(line.startsWith("Adaptador")) {
                        outputBuilder.setLength(0);
                        outputBuilder.append("\n").append(line);
                    }
                    else if (line.contains("IPv4")) {
                        outputBuilder.append('\n').append(line);
                        System.out.println(outputBuilder);
                        outputBuilder.setLength(0);
                    }
                }
                reader.close();
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Sistema operacional não suportado.");
        }
    }

    public void ping() {
        String[] UnixCmd = {"ping", "-4", "-c", "10", "www.google.com.br"};
        String[] WinCmd = {"ping", "-4", "-n", "10", "www.google.com.br"};
        String averagePingResponseMs;

        try {
            if (isWindows()) {
                String pingData = getMatchingLineInProcessOutput(WinCmd, "nimo =");
                averagePingResponseMs = pingData.split(",")[2].split("=")[1].trim();
            }
            else if (isUnix()) {
                String pingData = getMatchingLineInProcessOutput(UnixCmd, "rtt");
                averagePingResponseMs = pingData.split("=")[1].split("/")[1] + " ms";
            }
            else {
                System.err.println("Sistema Operacional não suportado");
                return;
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("Não foi possível ler os dados do ping.");
            return;
        }
        System.out.println("Tempo médio de resposta é: " + averagePingResponseMs);
    }

    private String getMatchingLineInProcessOutput(String[] command, String pattern) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                if(line.contains(pattern)){
                    return line;
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return null; // padrão não encontrado no output
    }
}
