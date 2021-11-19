package is.hi.hbv501g2021supportsession.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredientInfo")
public class IngredientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ingredientName;

    @OneToMany(mappedBy = "ingredientInfo",fetch = FetchType.EAGER)
    private List<Ingredient> ingredient = new ArrayList<Ingredient>();

    public IngredientInfo(String ingredientName, List<Ingredient> ingredient) {
        this.ingredientName= ingredientName;
        this.ingredient = ingredient;
    }

    public IngredientInfo() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
