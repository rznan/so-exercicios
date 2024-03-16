package ex02.view;

import ex02.controller.ThreadSum;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int LINHAS = 3;
        final int COLUNAS = 5;
        final int MAX_NUMBER_SIZE = 1000;
        Random rd = new Random();

        int[][] matrix = new int[LINHAS][COLUNAS];
        for(int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                matrix[i][j] = rd.nextInt() % MAX_NUMBER_SIZE;
            }
        }

        for(int line = 0; line < LINHAS; line++) {
            ThreadSum threadSum = new ThreadSum(matrix, line);
            threadSum.start();
        }
    }
}
