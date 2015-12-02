package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.User;
import hu.innocenter.bigdata.model.UserRole;
import hu.innocenter.bigdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

@RestController
public class UserWebApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/webapi-users", method = RequestMethod.GET, produces = "application/json")
    public List<User> queryUser(Model model) {

        List<User> users = userService.findAllUsers();
        return users;

    }

    @RequestMapping(value = "/webapi-users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody User user) {

        if (user.getRole_id()!=null && user.getRole_id().length()>0) {
            UserRole userRole = new UserRole();
            userRole.setRole(user.getRole_id());
            userRole.setUser(user);;
            user.getRoles().add(userRole);
        }

        Date now = new Date();
        user.setCreated(now);
        user.setUpdated(now);
        userService.saveUser(user);
    }

    @RequestMapping(value = {"/webapi-users/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-users/{id}"}, method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id, Model model) {
        User user = userService.findById(id);
        userService.deleteUser(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-users/{id}"}, method = RequestMethod.PUT)
    public User editUser(@RequestBody User user, Model model, @PathVariable String id) {

        Date now = new Date();
        user.setUpdated(now);

        userService.updateUser(user);

        return user;
    }

}
