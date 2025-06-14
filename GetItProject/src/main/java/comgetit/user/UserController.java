package comgetit.user;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import comgetit.user.dto.UserDTO;
import comgetit.user.dto.UsersDTO;

@RestController
public class UserController {
	
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<Long> createUser(
        @RequestBody @Valid final UserDTO userDTO
    ) {
        User user = userService.createUser(userDTO);
        return new ResponseEntity(user.getId(), HttpStatus.CREATED);
    }
    
    @GetMapping("/users")
    public List<UsersDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
