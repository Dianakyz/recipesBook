package com.example.recipesbook.service;

import com.example.recipesbook.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int id = 1;

    final private FileService fileService;

    public RecipeServiceImpl(FileServiceRecipeImpl fileServiceRecipe) {
        this.fileService = fileServiceRecipe;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(id++, recipe);
        saveToFile();
        return recipe;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Recipe getRecipeById(int id) {
        if(recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Нет таких объектов");
        }
    }

    @Override
    public Recipe editRecipeById(int id, Recipe recipe) {
        Recipe serviceRecipe = recipes.get(id);
        if(serviceRecipe == null) {
            throw new RuntimeException("Нет таких объектов");
        }
        serviceRecipe.setName(recipe.getName());
        serviceRecipe.setCookingTime(recipe.getCookingTime());
        serviceRecipe.setIngredients(recipe.getIngredients());
        serviceRecipe.setInstruction(recipe.getInstruction());
        saveToFile();
        return serviceRecipe;
    }

    @Override
    public Recipe removeRecipeById(int id) {
        return recipes.remove(id);
    }

    @Override
    public Collection<Recipe> getAllRecipes() {
        return recipes.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile();
            new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path createListOfAllRecipes() {
        Path path = fileService.createTempFile("listOfAllRecipes");
        for (Recipe recipe: recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
                writer.append(recipe.getName() + "\n");
                writer.append("----------------------------------------------------" + "\n");
                writer.append("Время приготовления: " + recipe.getCookingTime() + " мин."+ "\n");
                writer.append("Ингредиенты:"+ "\n");
                for (int i = 0; i < recipe.getIngredients().size(); i++) {
                    writer.append((i+1) + ") " + recipe.getIngredients().get(i).getName() +
                            " - " + recipe.getIngredients().get(i).getCount() +
                            " " + recipe.getIngredients().get(i).getMeasureUnit()+ "\n");
                }
                writer.append("Инструкция приготовления:"+ "\n");
                for (int j = 0; j < recipe.getInstruction().size(); j++) {
                    writer.append(recipe.getInstruction().get(j)+ "\n");
                }
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();   //throw new RuntimeException(e);
            }
        }
        return path;
    }
}