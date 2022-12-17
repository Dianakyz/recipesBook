package com.example.recipesbook.controller;

import com.example.recipesbook.model.Recipe;
import com.example.recipesbook.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipesService;

    public RecipeController(RecipeService recipesService) {
        this.recipesService = recipesService;
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipesService.addRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe getRecipeById(@PathVariable("id") int id) {
        return this.recipesService.getRecipeById(id);
    }
}
