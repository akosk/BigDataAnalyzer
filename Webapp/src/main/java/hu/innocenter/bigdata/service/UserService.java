package hu.innocenter.bigdata.service;

import hu.innocenter.bigdata.model.User;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.06..
 * akos.kiszely@gmail.com
 */
public interface UserService {

    User findById(String id);

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    List<User> findAllUsers();

}
