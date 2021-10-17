package is.hi.hbv501g2021supportsession.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private long recipeID;
    private String recipeTitle;
    private int recipeCategory;
    private String recipeMethod;
    private String recipeTime;
    private int recipeServings;
    private List<Ingredients> ingredients = new ArrayList<>();

    public Recipe() {

    }

    public Recipe(String recipeTitle, int recipeCategory, String recipeMethod, String recipeTime, int recipeServings) {
        this.recipeTitle = recipeTitle;
        this.recipeCategory = recipeCategory;
        this.recipeMethod = recipeMethod;
        this.recipeTime = recipeTime;
        this.recipeServings = recipeServings;
    }

    public long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(long recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public int getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeMethod() {
        return recipeMethod;
    }

    public void setRecipeMethod(String recipeMethod) {
        this.recipeMethod = recipeMethod;
    }

    public String getRecipeTime() {
        return recipeTime;
    }

    public void setRecipeTime(String recipeTime) {
        this.recipeTime = recipeTime;
    }

    public int getRecipeServings() {
        return recipeServings;
    }

    public void setRecipeServings(int recipeServings) {
        this.recipeServings = recipeServings;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
