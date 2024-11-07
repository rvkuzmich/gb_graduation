package kuzmich.graduation_project.security;

import kuzmich.graduation_project.model.User;
import kuzmich.graduation_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> userRoles = user.getRoles().stream()
                .map(it -> new SimpleGrantedAuthority(it.getRoleName())).toList();
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                userRoles
        );
    }
}
