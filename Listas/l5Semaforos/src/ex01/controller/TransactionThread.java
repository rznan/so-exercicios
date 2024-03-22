package ex01.controller;

import java.util.concurrent.Semaphore;

public class TransactionThread extends Thread {
    private final int id;
    private final Semaphore transactionSemaphore;

    public TransactionThread(int id, Semaphore transactionSemaphore) {
        this.id = id;
        this.transactionSemaphore = transactionSemaphore;
    }

    @Override
    public void run() {
        int numberOfOperations, minCalculationTime, maxCalculationTime, DBTransactionTime;

        int idDivisionRemainder = id % 3;

        switch (idDivisionRemainder) {
            case 1:
                numberOfOperations = 2;
                minCalculationTime = 20;
                maxCalculationTime = 100;
                DBTransactionTime = 100;
                break;
            case 2:
                numberOfOperations = 3;
                minCalculationTime = 50;
                maxCalculationTime = 150;
                DBTransactionTime = 150;
                break;
            default:
                numberOfOperations = 3;
                minCalculationTime = 100;
                maxCalculationTime = 200;
                DBTransactionTime = 150;
                break;
        }

        makeTransaction(numberOfOperations, minCalculationTime, maxCalculationTime, DBTransactionTime);
    }

    public void makeTransaction(int numberOfOperations, int minCalculationTime, int maxCalculationTime, int DBTransactionTime) {
        for(int i = 0; i < numberOfOperations; i++) {
            calculate(minCalculationTime, maxCalculationTime);
            try {
                transactionSemaphore.acquire();
                DBTransaction(DBTransactionTime);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                transactionSemaphore.release();
            }
        }
    }

    public void calculate(int minTime, int maxTime) {
        int calculationTime = (int) ((Math.random() * (maxTime-minTime + 1))+ minTime);
        try {
            System.out.println("Thread " + id + " está calculando por " + (float) calculationTime / 100 + " segundos.");
            sleep(calculationTime);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void DBTransaction(int time) {
        try {
            System.out.println("Thread " + id + " está realizando uma transação no BD por " + (float) time/100 + " segundos.");
            sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

}
