package com.example.recipesbook.controller;

import com.example.recipesbook.model.Ingredient;
import com.example.recipesbook.model.Recipe;
import com.example.recipesbook.service.IngredientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable("id") int id) {
        return this.ingredientService.getIngredientById(id);
    }
}
