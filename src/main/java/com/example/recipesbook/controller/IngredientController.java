package com.example.recipesbook.controller;

import com.example.recipesbook.model.Ingredient;
import com.example.recipesbook.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addIngredient(ingredient);
    }

    @PutMapping("{id}")
    public Ingredient editIngredientById(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.editIngredientById(id, ingredient);
    }

    @GetMapping("{id}")
    public Ingredient getIngredientById(@PathVariable("id") int id) {
        return this.ingredientService.getIngredientById(id);
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @DeleteMapping("{id}")
    public Ingredient removeIngredientById(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredientById(id);
    }
}
