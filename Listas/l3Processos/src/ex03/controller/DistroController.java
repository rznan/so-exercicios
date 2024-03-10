package ex03.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DistroController {
    private String os(){
        return System.getProperty("os.name");
    }

    private void readProcess(String[] cmd){
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if((line = reader.readLine()) != null){
                while (line != null){
                    System.out.println(line);
                    line = reader.readLine();
                }
            }
            else{
                reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    System.err.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void showDistro(){
        if(os().contains("nix")) {
            readProcess(new String[] {"cat", "/etc/os-release"});
        } else {
            System.err.println("O Seu sistema operacional não é do tipo Unix");
        }
    }
}
