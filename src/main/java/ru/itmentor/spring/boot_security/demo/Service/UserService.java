package ru.itmentor.spring.boot_security.demo.Service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
    List<User> findAll();

    User findByUsername(String name);
}
