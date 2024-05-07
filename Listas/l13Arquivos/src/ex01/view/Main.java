package ex01.view;

import datastrucures.genericList.List;
import ex01.controller.ArquivoComidaController;
import ex01.model.Food;

public class Main {
    public static void main(String[] args) {
        ArquivoComidaController controller = new ArquivoComidaController();

        List<Food> foodList = controller.readByGroup("Fruits", "C:\\TEMP\\generic_food.csv");
        int size = foodList.size();
        for(int i = 0; i<size; i++) {
            try {
                System.out.println(foodList.get(i));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

    }
}

