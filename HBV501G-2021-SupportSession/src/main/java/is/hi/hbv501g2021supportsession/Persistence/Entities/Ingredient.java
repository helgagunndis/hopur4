package is.hi.hbv501g2021supportsession.Persistence.Entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "Ingredients")
public class Ingredient {

    private String ingredientName;
    private String amount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientInfo> ingredientInfo = new ArrayList<IngredientInfo>();

    public Ingredient(String amount, String ingredientName) {
        this.amount= amount;
        this.ingredientName = ingredientName;
    }

    public Ingredient() {
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
