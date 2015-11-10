package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.User;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface UserDao {
    User findById(String id);

    void saveUser(User user);

    void deleteUser(User user);

    List<User> findAllUsers();

}
