package com.example.recipesbook.controller;

import com.example.recipesbook.model.Ingredient;
import com.example.recipesbook.service.IngredientServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции и другие эндпоинты для работы с ингредиентами")
public class IngredientController {

    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента")
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if(StringUtils.isBlank(ingredient.getName())) {
            return ResponseEntity.badRequest().body("Название не может быть пустым");
        }
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }

    @PutMapping("{id}")
    @Operation(summary = "Редактирование ингредиента", description = "Введите id ингредиента и необходимые изменения в формате j-son")
    public Ingredient editIngredientById(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
        return this.ingredientService.editIngredientById(id, ingredient);
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск ингредиента по id")
    public Ingredient getIngredientById(@PathVariable("id") int id) {
        return this.ingredientService.getIngredientById(id);
    }

    @GetMapping
    @Operation(summary = "Показать весь список ингредиентов")
    public Collection<Ingredient> getAllIngredients() {
        return this.ingredientService.getAllIngredients();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить ингредиент по id")
    public Ingredient removeIngredientById(@PathVariable("id") int id) {
        return this.ingredientService.removeIngredientById(id);
    }
}
