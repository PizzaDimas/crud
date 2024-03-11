package web.userDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.modelDao.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void add(UserEntity user) {
        entityManager.persist(user);
    }

    @Override
    public List<UserEntity> getAll() {
        CriteriaQuery<UserEntity> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(UserEntity.class);
        criteriaQuery.from(UserEntity.class);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<UserEntity> getById(long id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void update(UserEntity user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void removeById(long id) {
        Optional<UserEntity> userToRemove = getById(id);
        userToRemove.ifPresent(user -> {
            entityManager.remove(user);
            entityManager.flush();
        });
    }
}
