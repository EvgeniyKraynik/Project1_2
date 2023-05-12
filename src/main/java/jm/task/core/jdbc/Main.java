package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Evgeniy", "Kraynik", (byte)35);
        userService.saveUser("Olga", "Sidorova", (byte)24);
        userService.saveUser("Nikolay", "Petrov", (byte)32);
        userService.saveUser("Mariya", "Petrova", (byte)31);

        userService.removeUserById(3);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
