package com.karol172.lecturesmanager.service;

import com.karol172.lecturesmanager.model.Lecture;
import com.karol172.lecturesmanager.model.Role;
import com.karol172.lecturesmanager.model.User;
import com.karol172.lecturesmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO: Hashing password
    public Long createUser (User user) {
        if (!checkIfExist(user.getLogin()) && user.getPassword().length() > 5) {
            user.setId(null);
            user.setActive(false);
            user.setRole(Role.USER);
            userRepository.save(user);
            return user.getId();
        }
        return null;
    }

    public Boolean updateUser (Long userId, User user) {
        Optional<User> entity = userRepository.findById(userId);
        if (entity.isPresent()) {
            entity.get().setName(user.getName());
            userRepository.save(entity.get());
        }
        return entity.isPresent();
    }

    public User get (Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent())
            user.get().setPassword(null);
        return user.get();
    }

    public List<User> getAll () {
        List<User> users = userRepository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }

    public void delete (Long userId) {
        if (userRepository.existsById(userId))
            userRepository.deleteById(userId);
    }

    public Boolean checkIfExist (String login) {
        return !userRepository.findByLogin(login.toLowerCase()).isEmpty();
    }

    public Boolean setActiveForUser (Long idUser, Boolean status) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent() && (user.get().getActive() ^ status)) {
            user.get().setActive(status);
            userRepository.save(user.get());
        }
        return user.isPresent();
    }

    public Boolean setRoleForUser (Long idUser, Role role) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent() && (user.get().getRole() != role)) {
            user.get().setRole(role);
            userRepository.save(user.get());
        }
        return user.isPresent();
    }

    public Set<Lecture> getListenedLecturesForUser (Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent())
            return user.get().getListenedLectures();
        return new HashSet<>();
    }

    public Set<Lecture> getConductedLecturesForUser (Long idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent())
            return user.get().getConductedLectures();
        return new HashSet<>();
    }

    //TODO: HashingPassword
    public Boolean changePasswordForUser (Long idUser, String oldPassword, String newPassword) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isPresent() && user.get().getPassword().equals(oldPassword) && newPassword.length() > 5) {
            user.get().setPassword(newPassword);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

}