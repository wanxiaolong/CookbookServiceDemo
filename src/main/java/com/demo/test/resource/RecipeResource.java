package com.demo.test.resource;

import com.demo.test.dto.RecipeRequest;
import com.demo.test.dto.RecipeResponse;
import com.demo.test.service.RecipeService;
import com.demo.test.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/recipe")
public class RecipeResource {

    private RecipeService service = new RecipeService();
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipeByIngredient(@Valid RecipeRequest request) {
        LOGGER.info("<<<<Request : " + JsonUtils.toJson(request));
        List<String> params = request.getIngredients();
        RecipeResponse response = new RecipeResponse();
        List<String> list = service.findRecipeByIngredients(params);
        response.setRecipes(list);
        LOGGER.info(">>>>Response: " + JsonUtils.toJson(response));
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
