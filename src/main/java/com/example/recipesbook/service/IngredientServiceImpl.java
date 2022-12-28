package com.example.recipesbook.service;

import com.example.recipesbook.model.Ingredient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static int id = 1;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(id++, ingredient);
        return ingredient;
    }

    @PostConstruct
    private void init() {
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if(ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Нет таких объектов");
        }
    }

    @Override
    public Ingredient editIngredientById(int id, Ingredient ingredient) {
        Ingredient serviceIngredient = ingredients.get(id);
        if(serviceIngredient == null) {
            throw new RuntimeException("Нет таких объектов");
        }
        serviceIngredient.setName(ingredient.getName());
        serviceIngredient.setCount(ingredient.getCount());
        serviceIngredient.setMeasureUnit(ingredient.getMeasureUnit());
        return serviceIngredient;
    }

    @Override
    public Ingredient removeIngredientById(int id) {
        return ingredients.remove(id);
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }
}
