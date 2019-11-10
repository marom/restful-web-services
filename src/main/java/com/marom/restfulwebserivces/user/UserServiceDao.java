package com.marom.restfulwebserivces.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserServiceDao {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;
    static {
        users.add(new User(1, "Jon", new Date()));
        users.add(new User(2, "Jack", new Date()));
        users.add(new User(3, "Beatrice", new Date()));
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);

        return user;
    }

    public User findOne(int id) {

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
