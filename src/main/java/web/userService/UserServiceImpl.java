package web.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.modelDao.UserEntity;
import web.userDao.UserDao;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(UserEntity user) {
        userDao.add(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserEntity getById(long id) {
        return userDao.getById(id).orElse(null);
    }

    @Override
    public void update(long id, UserEntity user) {
        UserEntity userToUpdate = getById(id);
        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setSurname(user.getSurname());
            userDao.update(userToUpdate);
        }
    }

    @Override
    public void removeById(long id) {
        userDao.removeById(id);
    }
}
