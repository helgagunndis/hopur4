package is.hi.hbv501g2021supportsession.Persistence.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String userEmail;
    private String userPassword;
    @Nullable
    @Column(columnDefinition = "varchar(255) default '4'")
    private String userCategory;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPlan> mealPlanList = new ArrayList<>();

    public User() {
    }

    public User(String username, String userEmail, String userPassword, String userCategory ) {
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCategory = userCategory;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public List<MealPlan> getUserMealPlan(){ return mealPlanList;}

    public void setMealPlan(List<MealPlan> mealPlanList){
        this.mealPlanList=mealPlanList;
    }
    public void addToMealPlan(MealPlan mealPlan){
        mealPlanList.add(mealPlan);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String isUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }
    public String getUserCategory() {
        return userCategory;
    }


    public List<MealPlan> getMealPlanList() {
        return mealPlanList;
    }

    public void setMealPlanList(List<MealPlan> mealPlanList) {
        this.mealPlanList = mealPlanList;
    }
}
