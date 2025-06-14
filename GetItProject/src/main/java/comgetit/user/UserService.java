package comgetit.user;

import comgetit.role.Role;
import comgetit.role.RoleRepository;
import comgetit.role.exception.RoleNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import comgetit.user.dto.UserDTO;
import comgetit.user.dto.UsersDTO;
import comgetit.workarea.WorkArea;
import comgetit.workarea.WorkAreaRepository;
import comgetit.workarea.exception.WorkAreNotFoundException;

@Service
public class UserService implements UserDetailsService {

    private static final String ADMIN = "Admin";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WorkAreaRepository workAreaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
        WorkAreaRepository workAreaRepository,
        BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.workAreaRepository = workAreaRepository;
        this.passwordEncoder = passwordEncoder;
    }

	public User createUser(final UserDTO userDTO) {
	    WorkArea workAreaId = workAreaRepository.findById(userDTO.getWorkAreaId())
            .orElseThrow(WorkAreNotFoundException::new);
	    Role role = roleRepository.findByName(ADMIN)
            .orElseThrow(() -> new RoleNotFoundException(ADMIN));
        User user = new User(UUID.randomUUID().getMostSignificantBits(),
                             userDTO.getFirstname(), userDTO.getLastname(),
                             userDTO.getPhone(), userDTO.getBirthdate(),
                             userDTO.getAddress(), workAreaId, userDTO.getScore(),
                             userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()),
                             userDTO.getImage(), List.of(role));
        return userRepository.save(user);
    }

    public List<UsersDTO> getAllUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UsersDTO(user.getId().toString(), user.getFirstname(), 
                 user.getLastname(), user.getPhone(), user.getBirthdate(), 
                 user.getAddress(), user.getWorkArea().getId(), user.getScore(), user.getEmail(),
                 user.getImage().getBytes()))
                 .collect(Collectors.toList());
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }
}
