package is.hi.hbv501g2021supportsession.Persistence.Repositories;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientInfoRepository extends JpaRepository<IngredientInfo, Long> {
    IngredientInfo save(IngredientInfo ingredientInfo);
   // IngredientInfo add(IngredientInfo ingredientInfo);

}
