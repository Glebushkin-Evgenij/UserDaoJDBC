package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

        private final static UserService userService = new UserServiceImpl();

        public static void main(String[] args) {
            userService.createUsersTable();

            userService.saveUser("Джо", "Байден", (byte) 78);
            userService.saveUser("Трамп", "Дональд", (byte) 74);
            userService.saveUser("Барак", "Обама", (byte) 59);
            userService.saveUser("Джордж", "Буш", (byte) 74);


            System.out.println(userService.getAllUsers());

            userService.cleanUsersTable();

            userService.dropUsersTable();
    }
}
