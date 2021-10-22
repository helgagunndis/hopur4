package is.hi.hbv501g2021supportsession.Services;
import is.hi.hbv501g2021supportsession.Persistence.Entities.User;

public interface UserService{
    User save(User user);
    User findByUsername(String userName);
    User login(User user);
}

