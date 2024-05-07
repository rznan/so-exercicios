package ex01.controller;

import datastrucures.genericList.List;
import ex01.model.Food;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoComidaController {

    public ArquivoComidaController() {
    }

    public List<Food> readByGroup(String groupName, String path) {
        List<Food> foodList = new List<>();

        File f = new File(path);
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            while ((line = reader.readLine()) != null) {
                String[] lineItens = line.split(",");
                if (lineItens[2].equals(groupName)) {
                    try {
                        foodList.addLast(new Food(lineItens[0], lineItens[1], lineItens[2], lineItens[3]));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return foodList;
    }
}
