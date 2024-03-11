package web.userDao;

import web.modelDao.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void add(UserEntity user);

    List<UserEntity> getAll();

    Optional<UserEntity> getById(long id);

    void update(UserEntity user);

    void removeById(long id);


}
