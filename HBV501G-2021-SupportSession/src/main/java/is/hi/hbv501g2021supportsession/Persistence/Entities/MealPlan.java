package is.hi.hbv501g2021supportsession.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mealPlans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mealPlanID;

    private int numberOfDays;
    private int recipeCategory;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL)
    private List<Recipe> recipes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public MealPlan() {

    }

    public MealPlan(long mealPlanID, int numberOfDays, int recipeCategory) {
        this.mealPlanID = mealPlanID;
        this.numberOfDays = numberOfDays;
        this.recipeCategory = recipeCategory;
    }

    public long getMealPlanID() {
        return mealPlanID;
    }

    public void setMealPlanID(long mealPlanID) {
        this.mealPlanID = mealPlanID;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

}
