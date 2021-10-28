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

    private String IngredientName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    public IngredientInfo(String ingredientName, List<Ingredient> ingredients) {
        IngredientName = ingredientName;
        this.ingredient = ingredient;
    }

    public IngredientInfo() {
    }

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String ingredientName) {
        IngredientName = ingredientName;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}