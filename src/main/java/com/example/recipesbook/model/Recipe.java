package com.example.recipesbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredients = new LinkedList();
    private List<String> instruction = new LinkedList<>();

    public List<String> getInstruction() {
        return instruction;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
