package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.Ingredient;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.IngredientInfoRepository;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.IngredientRepository;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import is.hi.hbv501g2021supportsession.Services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImplementation implements IngredientService {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImplementation(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }




}

