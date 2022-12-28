package com.example.recipesbook.service;

import com.example.recipesbook.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int id);

    Ingredient editIngredientById(int id, Ingredient ingredient);

    Ingredient removeIngredientById(int id);

    Collection<Ingredient> getAllIngredients();
}
