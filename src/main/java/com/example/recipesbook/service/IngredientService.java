package com.example.recipesbook.service;

import com.example.recipesbook.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int id);

    Ingredient editIngredientById(int id, Ingredient ingredient);

    Ingredient removeIngredientById(int id);

    Collection<Ingredient> getAllIngredients();
}
