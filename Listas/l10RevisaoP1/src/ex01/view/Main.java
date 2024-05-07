package ex01.view;


import datastructures.genericQueue.Queue;
import ex01.controller.ProcessThread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    private static final String processNamesPath = "Listas/l10RevisaoP1/nomesDeProcessos.txt";
    public static void main(String[] args) {
        Queue<String> namesQueue = readProcessesName(processNamesPath);
        Semaphore Executionsemaphore = new Semaphore(1);
        ProcessThread[] processes = createProcesses(namesQueue, Executionsemaphore);
        runScheduller(processes);
    }

    private static void runScheduller(ProcessThread[] processes) {
        for(ProcessThread p : processes) {
            p.start();
        }
    }

    private static ProcessThread[] createProcesses(Queue<String> namesQueue, Semaphore executionsemaphore) {
        int cont = 0;
        ProcessThread[] processThreads = new ProcessThread[namesQueue.size()];
        while(!namesQueue.isEmpty()) {
            String name;
            try {
                name = namesQueue.remove();
            } catch (Exception e) {
              name = "Not-Found";
            }
            processThreads[cont]= new ProcessThread(cont, name, executionsemaphore);
            cont++;
        }
        return processThreads;
    }

    public static Queue<String> readProcessesName(String fileName) {
        File file = new File(fileName);
        Queue<String> names = new Queue<>();
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                names.insert(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            for(int i = 1; i < 21; i++) {
                names.insert("UnNamed" + (i));
            }
        }
        return names;
    }
}
