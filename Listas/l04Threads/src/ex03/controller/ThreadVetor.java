package ex03.controller;

public class ThreadVetor extends Thread {

    private final boolean traversalType;
    private final int[] arr;

    public ThreadVetor(int traversalType, int[] arr) {
       this.traversalType = traversalType % 2 == 0;
       this.arr = arr;
   }

   public void run() {
        if(traversalType) { forTraversal(); }
        else { forEachTraversal(); }

   }

    private void forEachTraversal() {
        long start, end;
        start = System.nanoTime();
        for(int i : arr) { /* do nothing */}
        end = System.nanoTime();

        double time = (double) (end - start) / 1000000;

        System.out.println("FOREACH: " + time + " ms");

    }

    private void forTraversal() {
        long start, end;
        start = System.nanoTime();
        for(int i = 0; i < arr.length; i++) { /* do nothing */ }
        end = System.nanoTime();

        double time = (double) (end - start) / 1000000;

        System.out.println("FOR: " + time + " ms");
    }

}
