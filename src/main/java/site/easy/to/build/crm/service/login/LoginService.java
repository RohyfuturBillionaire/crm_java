package site.easy.to.build.crm.service.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.repository.UserRepository;
import site.easy.to.build.crm.entity.User;
import java.util.List;


@Service
public class LoginService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    
    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User checkLogin(String username, String password) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsername(username);
        if (!users.isEmpty()) {
            User user = users
            .get(0);
            boolean matches = passwordEncoder.matches(password, user.getPassword());
            if(matches)
            {
                return user;
            }
            // return passwordEncoder.matches(password, user.getPassword()); // Utiliser matches()
        }
        return null;
    }
    
}

