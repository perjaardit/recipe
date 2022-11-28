package com.abnamro.recipe.web;

import com.abnamro.recipe.RecipeApplication;
import com.abnamro.recipe.domain.Ingredient;
import com.abnamro.recipe.domain.Recipe;
import com.abnamro.recipe.dto.IngredientDTO;
import com.abnamro.recipe.dto.RecipeDTO;
import com.abnamro.recipe.dto.enumeration.MeasureUnit;
import com.abnamro.recipe.mapper.RecipeMapper;
import com.abnamro.recipe.repository.IngredientRepository;
import com.abnamro.recipe.repository.RecipeRepository;
import com.abnamro.recipe.web.error.RestExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RecipeApplication.class)
public class RecipeResourceTest {

    private final static String BASE_URL = "/recipes";
    private final static String POST_URL = BASE_URL + "/add";
    private final static String PUT_URL = BASE_URL + "/update";
    private final static String SPECIFIC_RECIPE_BY_ID_URL = BASE_URL + "/{id}";


    @Autowired
    private RecipeResource recipeResource;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(recipeResource)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void test1_successfully_create_recipe() throws Exception {
        prepareIngredients();

        mockMvc.perform(MockMvcRequestBuilders.post(POST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getRecipeRequest())))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void test2_retrieve_specific_recipe_by_id() throws Exception {
        final List<Recipe> recipes = recipeRepository.findAll();

        mockMvc.perform(MockMvcRequestBuilders.get(SPECIFIC_RECIPE_BY_ID_URL, recipes.get(0).getRid()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void test2_1_retrieve_by_search_criteria() throws Exception {
//        final List<Recipe> recipes = recipeRepository.findAll();
//        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
//                .queryParam("vegetarian", Boolean.FALSE)
//                .queryParam("servings", 3)
//                .queryParam("searchKey", "cook")
//                .queryParam("includeIngredients", Arrays.asList("Ingredient 1", "Ingredient 2"))
//                .queryParam("excludeIngredients", Arrays.asList("Ingredient Second", "Ingredient First"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get(builder.build().toUriString()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(recipes.size())));
//    }

    @Test
    public void test3_retrieve_specific_recipe_by_id_not_found() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SPECIFIC_RECIPE_BY_ID_URL, 0))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test4_update_recipe_success() throws Exception {
        final List<Recipe> recipes = recipeRepository.findAll();

        mockMvc.perform(MockMvcRequestBuilders.put(PUT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(recipeMapper.map(recipes.get(0))))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test5_delete_recipe_not_found() throws Exception {
        final List<Recipe> recipes = recipeRepository.findAll();
        mockMvc.perform(MockMvcRequestBuilders.delete(SPECIFIC_RECIPE_BY_ID_URL, recipes.get(0).getRid() + 1))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test6_delete_recipe_success() throws Exception {
        final List<Recipe> recipes = recipeRepository.findAll();
        mockMvc.perform(MockMvcRequestBuilders.delete(SPECIFIC_RECIPE_BY_ID_URL, recipes.get(0).getRid()))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RecipeDTO getRecipeRequest() {
        final Set<IngredientDTO> ingredients = ingredientRepository.findAll()
                .stream()
                .map(ingredient -> IngredientDTO.builder()
                        .iid(ingredient.getIid())
                        .measure(10.8)
                        .unit(MeasureUnit.GR_UNIT)
                        .build())
                .collect(Collectors.toSet());
        return RecipeDTO.builder()
                .name("Recipe 1")
                .instructions("This is how should you cook")
                .servings(3)
                .vegetarian(Boolean.FALSE)
                .ingredients(ingredients)
                .build();
    }

    private void prepareIngredients() {
        ingredientRepository.saveAll(IntStream.range(1, 100)
                .mapToObj(value -> Ingredient.builder()
                        .createdDate(OffsetDateTime.now())
                        .healthBenefit("This are the benefits of the ingredient " + value)
                        .name("Ingredient " + value)
                        .build()).collect(Collectors.toList()));
    }
}
