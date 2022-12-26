package com.example.recipesbook.controller;

import com.example.recipesbook.model.Recipe;
import com.example.recipesbook.service.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD-операции и другие эндпоинты для работы с рецептами")
public class RecipeController {
    private final RecipeServiceImpl recipesService;

    public RecipeController(RecipeServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if(StringUtils.isBlank(recipe.getName())) {
            return ResponseEntity.badRequest().body("Название не может быть пустым");
        }
        return ResponseEntity.ok(recipesService.addRecipe(recipe));
    }

    @PutMapping("{id}")
    @Operation(summary = "Редактирование рецепта", description = "Введите id рецепта и необходимые изменения в формате j-son")
    public Recipe editRecipeById(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return this.recipesService.editRecipeById(id, recipe);
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск рецепта по id")
    public Recipe getRecipeById(@PathVariable("id") int id) {
        return this.recipesService.getRecipeById(id);
    }

    @GetMapping
    @Operation(summary = "Показать весь список рецептов")
    public Collection<Recipe> getAllRecipes() {
        return this.recipesService.getAllRecipes();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить рецепт по id")
    public Recipe removeRecipeById(@PathVariable("id") int id) {
        return this.recipesService.removeRecipeById(id);
    }
}
