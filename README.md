# Recipe Application

Simple application with [Spring Boot 3](https://spring.io/projects/spring-boot) and [Apache Maven](https://maven.apache.org/),
with a dependency on [H2] database.


# Objective

Create a standalone java application which allows users to manage their favourite recipes. 
It should allow adding, updating, removing and fetching recipes. 
Additionally, users should be able to filter available recipes based on one or more of the following criteria:
1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.

For example, the API should be able to handle the following search requests:
   • All vegetarian recipes
   • Recipes that can serve 4 persons and have “potatoes” as an ingredient
   • Recipes without “salmon” as an ingredient that has “oven” in the instructions.

# Requirements

Please ensure that we have some documentation about the architectural choices and also how to run the application. 
The project is expected to be delivered as a GitHub (or any other public git hosting) repository URL.

All these requirements needs to be satisfied:
1. It must be a REST application implemented using Java (use a framework of your choice)
2. Your code should be production-ready.
3. REST API must be documented
4. Data must be persisted in a database
5. Unit tests must be present
6. Integration tests must be present

# Operations

| Operation           |                  Description                   | [RequestType]:Request URL                                                                                                                      |
|:--------------------|:----------------------------------------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------|
| Create              |              Create a new recipe               | [POST]:http://localhost:8080/api/recipes/add                                                                                                   |
| Update              |             Update existing recipe             | [PUT]:http://localhost:8080/api/recipes/update                                                                                                 |
| Fetch by code       |           Get specific recipe by id            | [GET]:http://localhost:8080/api/recipes/{id}                                                                                                   |
| Fetch with criteria | Get all recipes based on the searched criteria | [GET]:http://localhost:8080/api//recipes?vegetarian=true&servings=3&searchKey&includeIngredients=Ingredient 1&excludeIngredients=Ingredient 1  |
| Delete              |       Delete specific recipe from the db       | [DELETE]:http://localhost:8080/api/recipes/{id}                                                                                                |


## Prerequisites

What is needed to start developing and to run the application:

- JDK 17, for instance [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)
- Apache Maven 3.3+
- Lombok IDE plugin
- MapStruct IDE plugin

### Installing

After checking out the project, you may need to install the dependencies

    `mvn clean install`

## Running the app

After that we may start the application via preferred IDE or using cmd:

    `mvn spring-boot:run`

## Testing the app

You may test the rest functionality via [Postman](https://www.postman.com/) by using the collection under the root folder.