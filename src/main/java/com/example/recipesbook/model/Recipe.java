package com.example.recipesbook.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Recipe {

    private String name;
    private int cookingTime;
    private LinkedList<Ingredient> ingredients = new LinkedList();
    private LinkedList<String> instruction = new LinkedList<>();

    public Recipe(String name, int cookingTime, LinkedList<Ingredient> ingredients, LinkedList<String> instruction) {
        this.name = name;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTimeInMinute(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public LinkedList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(LinkedList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedList<String> getInstruction() {
        return instruction;
    }

    public void setInstruction(LinkedList<String> instruction) {
        this.instruction = instruction;
    }
}
