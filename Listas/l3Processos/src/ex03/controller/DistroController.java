package ex03.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DistroController {
    private String os(){
        return System.getProperty("os.name");
    }

    public void showDistro(){
        if(os().contains("inux")) {
            String[] cmd = {"cat", "/etc/os-release"};
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                if((line = reader.readLine()) != null){
                    while (line != null){
                        if(line.startsWith("NAME=")){
                            System.out.print(
                                line.substring(line.indexOf("\""), line.length()-1)
                                + " ");
                        }
                        else if (line.startsWith("VERSION=")) {
                            System.out.println(
                                    line.substring(line.indexOf("\"", line.length()-1))
                            );
                        }

                        line = reader.readLine();
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("O Seu sistema operacional não é do tipo Unix");
        }
    }
}
