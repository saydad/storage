package self.start.web.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import self.start.persistence.bean.AuthUser;
import self.start.persistence.bean.User;
import self.start.persistence.repository.UserRepository;
import self.start.service.DslService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * create by liuyong4 2018/5/24 下午6:09
 **/
@RestController
public class UserController {

    @Resource
    private DslService dslService;
    @Resource
    private UserRepository userRepository;

    @PostMapping(path = "/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping(path = "/login")
    private User login(@RequestBody User user) {
        return user;
    }

    @GetMapping(path = "/user")
    public List<User> findAll() {
        return dslService.findAll();
    }

    @GetMapping(path="/exists")
    public boolean exists(@RequestParam("name") String name) {
        return dslService.exists(name);
    }

    @GetMapping(path = "/auth")
    public Set<AuthUser> findAllAuthUser() {
        return dslService.findAllAuthUser();
    }
}
