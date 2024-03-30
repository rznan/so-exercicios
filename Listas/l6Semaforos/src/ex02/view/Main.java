package ex02.view;

import ex02.controller.Plane;
import ex02.controller.RunwayThread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Queue<Plane> planes = new LinkedList<>();
        final int NumberOfPlanes = 12;

        for(int i = 0; i < NumberOfPlanes; i++) {
            planes.add(new Plane(i));
        }

        Semaphore planePickerSemaphore = new Semaphore(1);
        RunwayThread southRunway = new RunwayThread(0, "Sul", planes, planePickerSemaphore);
        RunwayThread northRunway =  new RunwayThread(1, "Norte", planes, planePickerSemaphore);
        southRunway.start();
        northRunway.start();
    }
}
