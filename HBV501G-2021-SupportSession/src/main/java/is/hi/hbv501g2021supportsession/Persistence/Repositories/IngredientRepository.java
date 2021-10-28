package is.hi.hbv501g2021supportsession.Persistence.Repositories;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient save(Ingredient ingredient);

    //void add(Ingredient ingredient);
}
