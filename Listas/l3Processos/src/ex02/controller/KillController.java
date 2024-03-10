package ex02.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KillController {
    private String os(){
        return System.getProperty("os.name");
    }

    private boolean isWindows(){
        return os().contains("Win");
    }

    private boolean isUnix(){
        return os().contains("nix");
    }
    private void readProcess(String[] callCmd){
        try{
            Process process = Runtime.getRuntime().exec(callCmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if ((line = reader.readLine()) != null){
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
            else{
                reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while((line = reader.readLine()) != null){
                    System.out.println(line);
                }
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Não foi possível ler o processo\n" + e.getMessage());
        }
    }

    public void listProcesses(){
        String[] cmd;
        if(isWindows()) cmd = new String[] {"TASKLIST", "/FO", "TABLE"};
        else if(isUnix()) cmd = new String[] {"ps", "-ef"};
        else {
            System.err.println("Sistema Operacional Não Suportado");
            return;
        }
        readProcess(cmd);
    }

    public void killPid(int pid){
        if(isWindows()){
            String cmd = "Taskkill /PID " + pid;
            readProcess(cmd.split(" "));
        }
        else if(isUnix()){
            String cmd = "kill -9 " + pid;
            readProcess(cmd.split(" "));
        }
        else {
            System.err.println("Sistema Operacional Não Suportado");
        }
    }

    public void killName(String name){
        if(isWindows()){
            String cmd = "Taskkill /IM " + name;
            readProcess(cmd.split(" "));
        }
        else if(isUnix()){
            String cmd = "pkill -f " + name;
            readProcess(cmd.split(" "));
        }
        else {
            System.err.println("Sistema Operacional Não Suportado");
        }
    }

}
