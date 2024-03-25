package ex02.view;

import ex02.controller.KitchenThread;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_OF_DISHES = 5;
        final Semaphore DELIVERY_SEMAPHORE = new Semaphore(1);
        final Semaphore KITCHEN_SEMAPHORE = new Semaphore(5);

        for(int i = 0; i<NUMBER_OF_DISHES; i++) {
            KitchenThread kitchenThread = new KitchenThread(i, DELIVERY_SEMAPHORE, KITCHEN_SEMAPHORE);
            kitchenThread.start();
        }
    }
}
