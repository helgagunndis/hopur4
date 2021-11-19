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
    private int numberOfWeekDay;
    private int recipeCategory;

    @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL)
    private List<MPList> mpLists = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public MealPlan() {

    }

    public MealPlan(long mealPlanID, int recipeCategory) {
        this.mealPlanID = mealPlanID;
        this.recipeCategory = recipeCategory;
    }

    public long getMealPlanID() {
        return mealPlanID;
    }

    public void setMealPlanID(long mealPlanID) {
        this.mealPlanID = mealPlanID;
    }

    public int getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(int recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfWeekDay() {
        return numberOfWeekDay;
    }

    public void setNumberOfWeekDay(int numberOfWeekDay) {
        this.numberOfWeekDay = numberOfWeekDay;
    }

    public List<MPList> getMpLists() {
        return mpLists;
    }

    public void setMpLists(List<MPList> mpLists) {
        this.mpLists = mpLists;
    }
}
