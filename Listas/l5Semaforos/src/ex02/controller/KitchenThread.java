package ex02.controller;

import ex02.model.Dish;

import java.util.concurrent.Semaphore;

public class KitchenThread extends Thread {
    final int id;
    final Semaphore deliverySemaphore;
    final Dish dish;
    final int preparationTime;

    public KitchenThread(int id, Semaphore deliverySemaphore){
        this.id = id;
        this.deliverySemaphore = deliverySemaphore;
        this.dish = id % 2 == 0 ? Dish.LASANHA_BOLONHESA : Dish.SOPA_DE_CEBOLA;
        this.preparationTime = (int) (Math.random() * (dish.getMaxTime() - dish.getMinTime()) + 1) + dish.getMinTime();
    }

    @Override
    public void run() {
        cook();

        try {
            deliverySemaphore.acquire();
            deliver();
        }
        catch (InterruptedException e) {
             System.err.println(e.getMessage());
        }
        finally {
            deliverySemaphore.release();
        }
    }

    public void cook() {
        int elapsedTime = 0, completionPercentage;

        System.out.println("(" + id + ") " + dish.getDescription() + ": INICIOU...");
        while(elapsedTime < preparationTime) {
            try {
                sleep(10);
                elapsedTime += 10;
                if(elapsedTime % 100 == 0) {
                    completionPercentage = (int) ((float) elapsedTime / preparationTime * 100);
                    System.out.println("(" + id + ") " + dish.getDescription() + ": " + completionPercentage + "%");
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("(" + id + ") " + dish.getDescription() + ": COMPLETO!");
    }

    public void deliver() {
        System.out.println("(" + id + ") " + dish.getDescription() + ": ENTREGANDO...");
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("(" + id + ") " + dish.getDescription() + ": ENTREGUE!");
    }
}

