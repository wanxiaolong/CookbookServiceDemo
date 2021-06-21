package com.demo.test.service;

import com.demo.test.config.DbDataSource;
import com.demo.test.entity.Ingredient;
import com.demo.test.entity.Recipe;
import com.demo.test.mapper.IngredientMapper;
import com.demo.test.mapper.RecipeIngredientMapper;
import com.demo.test.mapper.RecipeMapper;
import com.demo.test.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    private static IngredientMapper ingredientMapper = DbDataSource.getMapper(IngredientMapper.class);
    private static RecipeMapper recipeMapper = DbDataSource.getMapper(RecipeMapper.class);
    private static RecipeIngredientMapper recipeIngredientMapper = DbDataSource.getMapper(RecipeIngredientMapper.class);

    public List<String> findRecipeByIngredients(List<String> nameList) {
        //step1. Find ingredient ids by name list
        List<Ingredient> ingredients = ingredientMapper.findByNameList(CollectionUtils.joinString(nameList));
        if (ingredients == null || ingredients.size() < nameList.size()) {
            throw new IllegalArgumentException("some ingredient in request does not exists.");
        }
        List<Long> ingredientIdList = ingredients.stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());

        //step2. Find recipe ids by ingredient id list which only uses given ingredients
        List<Long> recipeIdList = recipeIngredientMapper.findByIngredientIdList(CollectionUtils.joinLong(ingredientIdList));
        if (CollectionUtils.isEmpty(recipeIdList)) {
            LOGGER.info("no recipe found which is using given ingredients: " + Arrays.toString(nameList.toArray()));
            return Collections.emptyList();
        }

        //step3. Find recipe name list by recipe ids
        List<Recipe> recipeList = recipeMapper.findByIdList(CollectionUtils.joinLong(recipeIdList));
        //convert to recipe name list
        return recipeList.stream().map(Recipe::getName).collect(Collectors.toList());
    }
}
