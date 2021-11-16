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

    public Ingredient(long id, String amount, String ingredientName, List<IngredientInfo> ingredientInfo) {
        this.amount= amount;
        this.ingredientName = ingredientName;
        this.id = id;
        this.ingredientInfo = ingredientInfo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngredientInfo> getIngredientInfo() {
        return ingredientInfo;
    }

    public void setIngredientInfo(List<IngredientInfo> ingredientInfo) {
        this.ingredientInfo = ingredientInfo;
    }
}
