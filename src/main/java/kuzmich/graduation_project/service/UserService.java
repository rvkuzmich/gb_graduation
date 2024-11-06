package kuzmich.graduation_project.service;

import kuzmich.graduation_project.model.Role;
import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.repository.RoleRepository;
import kuzmich.graduation_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    public User addRole(UUID id, UUID roleId) {
        Optional<User> optionalUser = userRepository.findById(id);
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalUser.isEmpty() || optionalRole.isEmpty()) {
            throw new NoSuchElementException("No such user or role");
        }
        User user = optionalUser.get();
        Role role = optionalRole.get();
        Set<Role> roleList = user.getRoles();
        roleList.add(role);
        user.setRoles(roleList);
        return userRepository.save(user);
    }

}
