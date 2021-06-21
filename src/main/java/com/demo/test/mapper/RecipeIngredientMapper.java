package com.demo.test.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecipeIngredientMapper {
    @Select("select distinct recipe_id from recipe_ingredient where ingredient_id in (${list}) " +
            "and recipe_id not in (" +
            "    select distinct recipe_id from recipe_ingredient where ingredient_id not in (${list}) " +
            ")"
    )
    public List<Long> findByIngredientIdList(@Param("list") String idList);
}
