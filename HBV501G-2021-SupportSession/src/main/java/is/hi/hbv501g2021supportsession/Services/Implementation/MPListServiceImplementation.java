package is.hi.hbv501g2021supportsession.Services.Implementation;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MPList;
import is.hi.hbv501g2021supportsession.Persistence.Repositories.MPListRepository;
import is.hi.hbv501g2021supportsession.Services.MPListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MPListServiceImplementation implements MPListService {
    private MPListRepository mpListRepository;

    @Autowired
    public MPListServiceImplementation(MPListRepository mpListRepository) {
        this.mpListRepository = mpListRepository;
    }

    @Override
    public MPList save(MPList mpList) {
        return mpListRepository.save(mpList);
    }
}
