package is.hi.hbv501g2021supportsession.Persistence.Entities;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "Ingredients")
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientID;

    private String ingredientName;
    private String amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;


    public Ingredients(long ingredientID, String ingredientName, String amount) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public Ingredients() {
    }

    public long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(long ingredientID) {
        this.ingredientID = ingredientID;
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
