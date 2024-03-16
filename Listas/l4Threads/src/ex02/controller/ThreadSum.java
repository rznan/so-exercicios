package ex02.controller;

public class ThreadSum extends Thread {
    private final int[][] matrix;
    private final int targetLine;

    public ThreadSum(int[][] matrix, int targetLine) {
        this.matrix = matrix;
        this.targetLine = targetLine;
    }

    @Override
    public void run() {
        sumLine();
    }

    private void sumLine() {
        int length = matrix[0].length;
        int sum = 0;
        try{
            for (int i = 0; i < length; i++) {
                sum += matrix[targetLine][i];
            }
            System.out.println("linha " + (targetLine+1) + ": " + sum);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("linha " + targetLine + ": não acessível");
        }
    }
}
