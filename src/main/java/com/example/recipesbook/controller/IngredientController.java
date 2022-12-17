package com.example.recipesbook.controller;

import com.example.recipesbook.service.IngredientService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
