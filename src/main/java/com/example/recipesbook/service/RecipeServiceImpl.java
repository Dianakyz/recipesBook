package com.example.recipesbook.service;

import com.example.recipesbook.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int id = 1;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipeById(int id) {
        if(recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Нет таких объектов");
        }
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        Recipe serviceRecipe = recipes.get(id);
        if(serviceRecipe == null) {
            throw new RuntimeException("Нет таких объектов");
        }
            serviceRecipe.setName(recipe.getName());
            serviceRecipe.setCookingTime(recipe.getCookingTime());
            serviceRecipe.setIngredients(recipe.getIngredients());
            serviceRecipe.setInstruction(recipe.getInstruction());
            return serviceRecipe;
        }

    @Override
    public Recipe removeRecipeById(int id) {
        return recipes.remove(id);
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }
}
