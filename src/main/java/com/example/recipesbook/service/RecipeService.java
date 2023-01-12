package com.example.recipesbook.service;

import com.example.recipesbook.model.Recipe;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.Collection;

@Service
public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipeById(int id);

    Recipe editRecipeById(int id, Recipe recipe);

    Recipe removeRecipeById(int id);

    Collection<Recipe> getAllRecipes();
    Path createListOfAllRecipes();
}
