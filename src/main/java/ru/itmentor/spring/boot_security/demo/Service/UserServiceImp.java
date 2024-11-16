package ru.itmentor.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dao.UserDao;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User save(User user) {
        userDao.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        userDao.update(user);
        return user;
    }

    @Override
    public User delete(Long id) {
        User user = userDao.findById(id); // Пробуем найти пользователя
        if (user != null) {
            userDao.delete(id);
            return user;
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Override
    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }
}
