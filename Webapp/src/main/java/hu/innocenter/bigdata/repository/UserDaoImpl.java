package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.User;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<String, User> implements UserDao {
    @Override
    public User findById(String id) {
        return getByKey(id);
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public void deleteUser(User user) {

        delete(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();

    }
}