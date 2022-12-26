package com.example.recipesbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private LinkedList<Ingredient> ingredients = new LinkedList();
    private LinkedList<String> instruction = new LinkedList<>();
}
