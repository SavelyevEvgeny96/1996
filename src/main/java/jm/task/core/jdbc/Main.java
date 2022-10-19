package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ef", "wed", (byte) 77);
        userService.saveUser("ef", "wd", (byte) 7);
        userService.saveUser("f", "wed", (byte) 77);
        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);
        System.out.println("//////");
        userService.removeUserById(2);

        List<User> userListd = userService.getAllUsers();
        userListd.forEach(System.out::println);
//        userService.cleanUsersTable();
//
    }
}

