package com.demo.test.mapper;


import com.demo.test.entity.Ingredient;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IngredientMapper {
    @Select("select id,name from ingredient where name in (${list})")
    public List<Ingredient> findByNameList(@Param("list") String nameList);
}
