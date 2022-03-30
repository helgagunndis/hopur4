package is.hi.hbv501g2021supportsession.Persistence.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MPList")
public class MPList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listID;

    @ManyToOne
    Recipe recipe;

    @JsonIgnore
    @ManyToOne
    MealPlan mealPlan;

    public MPList() {
    }

    public MPList(Recipe recipe, MealPlan mealPlan) {
        this.recipe = recipe;
        this.mealPlan = mealPlan;
    }

    public long getListID() {
        return listID;
    }

    public void setListID(long listID) {
        this.listID = listID;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
