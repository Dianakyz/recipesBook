package com.example.recipesbook.service;

import com.example.recipesbook.model.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Integer, Ingredient> ingredients = new TreeMap<>();
    private static int id = 1;
    final private FileServiceIngredient fileServiceIngredient;

    public IngredientServiceImpl(FileServiceIngredient fileServiceIngredient) {
        this.fileServiceIngredient = fileServiceIngredient;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(id++, ingredient);
        saveToFile();
        return ingredient;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        if(ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Нет таких объектов");
        }
    }

    @Override
    public Ingredient editIngredientById(int id, Ingredient ingredient) {
        Ingredient serviceIngredient = ingredients.get(id);
        if(serviceIngredient == null) {
            throw new RuntimeException("Нет таких объектов");
        }
        serviceIngredient.setName(ingredient.getName());
        serviceIngredient.setCount(ingredient.getCount());
        serviceIngredient.setMeasureUnit(ingredient.getMeasureUnit());
        saveToFile();
        return serviceIngredient;
    }

    @Override
    public Ingredient removeIngredientById(int id) {
        return ingredients.remove(id);
    }

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileServiceIngredient.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileServiceIngredient.readFromFile();
            new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
