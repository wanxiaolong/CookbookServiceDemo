package com.demo.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecipeResponse {
    @JsonProperty
    private List<String> recipes;

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }
}
