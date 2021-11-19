package is.hi.hbv501g2021supportsession.Persistence.Repositories;

import is.hi.hbv501g2021supportsession.Persistence.Entities.MPList;
import is.hi.hbv501g2021supportsession.Persistence.Entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MPListRepository extends JpaRepository<MPList, Long> {
    MPList save(MPList mpList);

}
