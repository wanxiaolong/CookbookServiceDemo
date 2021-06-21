package com.demo.test.mapper;

import com.demo.test.entity.Recipe;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecipeMapper {
    @Select("select id,name from recipe where id in (${list})")
    public List<Recipe> findByIdList(@Param("list") String idList);
}
