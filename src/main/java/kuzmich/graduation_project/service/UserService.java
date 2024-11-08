package kuzmich.graduation_project.service;

import kuzmich.graduation_project.controller.UserRole;
import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.repository.RoleRepository;
import kuzmich.graduation_project.repository.UserRepository;
import kuzmich.graduation_project.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public void addRole(UUID id, String roleName) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("No such user");
        }
        User user = optionalUser.get();

        UserRole roleToAdd = new UserRole();
        roleToAdd.setUserId(user.getId());
        roleToAdd.setRoleName(roleName);
        userRoleRepository.save(roleToAdd);
    }

}
