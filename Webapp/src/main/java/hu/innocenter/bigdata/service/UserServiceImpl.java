package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.User;
import hu.innocenter.bigdata.repository.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;


    @Override
    public User findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void saveUser(User user) {
        dao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        dao.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        User entity = dao. findById(user.getUsername());

        if (entity != null) {
            entity.getRoles().clear();
            BeanUtils.copyProperties(user, entity, "created");
            dao.saveUser(entity);
        }

    }

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

}
