package com.example.recipesbook.service;

import com.example.recipesbook.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientService {
    private final Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static int id = 1;

    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(id++, ingredient);
        return ingredient;
    }

    public Ingredient getIngredientById(int id) {
        if(ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Нет таких объектов");
        }
    }
}
