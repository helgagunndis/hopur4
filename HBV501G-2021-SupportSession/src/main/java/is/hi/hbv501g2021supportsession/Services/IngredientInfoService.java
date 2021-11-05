package is.hi.hbv501g2021supportsession.Services;

import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;

import java.util.List;


public interface IngredientInfoService {
    //IngredientInfo add(IngredientInfo ingredientInfo);
    IngredientInfo save(IngredientInfo ingredientInfo);
    List<IngredientInfo> findAll();

}
