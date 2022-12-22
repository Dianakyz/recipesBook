package com.example.recipesbook.controller;

import com.example.recipesbook.model.Recipe;
import com.example.recipesbook.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @PostMapping("/editRecipeById/{id}")
    public Recipe editRecipeById(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return this.recipesService.editRecipeById(id, recipe);
    }

    @GetMapping("/getRecipeById/{id}")
    public Recipe getRecipeById(@PathVariable("id") int id) {
        return this.recipesService.getRecipeById(id);
    }

    @GetMapping("/allRecipes")
    public Collection<Recipe> getAllRecipes() {
        return this.recipesService.getAllRecipes();
    }

    @DeleteMapping("/removeRecipeById/{id}")
    public Recipe removeRecipeById(@PathVariable("id") int id) {
        return this.recipesService.removeRecipeById(id);
    }
}
