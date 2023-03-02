package pp.javapp312.service;

import pp.javapp312.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUser(int id);

}
