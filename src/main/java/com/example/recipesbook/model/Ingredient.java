package com.example.recipesbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ingredient {

    private String name;
    private int count;
    private String measureUnit;
}
