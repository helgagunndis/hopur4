package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.IngredientInfo;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.IngredientInfoRepository;
import is.hi.hbv501g2021supportsession.Services.IngredientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientInfoServiceImplementation implements IngredientInfoService {
    private IngredientInfoRepository ingredientInfoRepository;

    @Autowired
    public IngredientInfoServiceImplementation(IngredientInfoRepository ingredientInfoRepository){
        this.ingredientInfoRepository = ingredientInfoRepository;
    }

    @Override
    public IngredientInfo save(IngredientInfo ingredientInfo) {
        return ingredientInfoRepository.save(ingredientInfo);
    }

    @Override
    public List<IngredientInfo> findAll() {
        return this.ingredientInfoRepository.findAll();
    }

}
