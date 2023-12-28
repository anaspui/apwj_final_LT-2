package dev.service;

import dev.domain.User;
import dev.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        user.setFullname(user.getFullname().toUpperCase());
        userRepository.create(user);
    }
    public long countUsers() {
        return userRepository.countUsers();
    }
    public User getOne(int id) {
        return userRepository.get(id);
    }
    public boolean delete(int id) {
        User user = userRepository.get(id);
        if (user != null) {
            userRepository.delete(id);
            return true;
        } else {
         return false;
        }
    }
    public boolean updateUser(User updatedUser) {
        User user = userRepository.get(updatedUser.getId());
        if (user != null) {
            userRepository.edit(updatedUser);
            return true;
        } else {
            return false;
        }
    }
    public List<User> getAll() {
        return userRepository.getAll();
    }
}
