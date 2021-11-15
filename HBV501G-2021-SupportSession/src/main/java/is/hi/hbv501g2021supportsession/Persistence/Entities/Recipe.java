package is.hi.hbv501g2021supportsession.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recipeID;

    private String recipeTitle;
    private int recipeCategory;
    private String recipeSummary;
    private String recipeImage;
    private String recipeMethod;
    private String recipeTime;
    private int recipeServings;
    private String recipeCredit;

    @OneToMany(mappedBy = "recipe")
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    @ManyToOne
    private MealPlan mealPlan;

    public Recipe() {
    }

    public Recipe(String recipeTitle, int recipeCategory, String recipeMethod,
                  String recipeTime, int recipeServings, String recipeSummary,
                  String recipeImage, String recipeCredit, List<Ingredient> ingredients) {
        this.recipeTitle = recipeTitle;
        this.recipeCategory = recipeCategory;
        this.recipeMethod = recipeMethod;
        this.recipeTime = recipeTime;
        this.recipeServings = recipeServings;
        this.recipeSummary = recipeSummary;
        this.recipeImage = recipeImage;
        this.recipeCredit = recipeCredit;
        this.ingredients = ingredients;
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

    public String getRecipeSummary() {
        return recipeSummary;
    }

    public void setRecipeSummary(String recipeSummary) {
        this.recipeSummary = recipeSummary;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeCredit() {
        return recipeCredit;
    }

    public void setRecipeCredit(String recipeCredit) {
        this.recipeCredit = recipeCredit;
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
