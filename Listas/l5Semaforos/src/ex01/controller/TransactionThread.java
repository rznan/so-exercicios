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
                minCalculationTime = 200;
                maxCalculationTime = 1000;
                DBTransactionTime = 1000;
                break;
            case 2:
                numberOfOperations = 3;
                minCalculationTime = 500;
                maxCalculationTime = 1500;
                DBTransactionTime = 1500;
                break;
            default:
                numberOfOperations = 3;
                minCalculationTime = 1000;
                maxCalculationTime = 2000;
                DBTransactionTime = 1500;
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
            System.out.println("Thread " + id + " está calculando por " + (float) calculationTime / 1000 + " segundos.");
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
