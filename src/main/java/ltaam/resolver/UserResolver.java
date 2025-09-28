package ltaam.resolver;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ltaam.entity.User;
import ltaam.repository.UserRepository;

@Controller
public class UserResolver {

    private final UserRepository userRepo;

    public UserResolver(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @QueryMapping
    public List<User> allUsers(){
        return userRepo.findAll();
    }

    @MutationMapping
    public User createUser(@Argument String fullname, @Argument String email, @Argument String password, @Argument String phone){
        User u = new User();
        u.setFullname(fullname); u.setEmail(email); u.setPassword(password); u.setPhone(phone);
        return userRepo.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument String fullname, @Argument String email, @Argument String password, @Argument String phone){
        Optional<User> u = userRepo.findById(id);
        if(u.isPresent()){
            User user = u.get();
            user.setFullname(fullname); user.setEmail(email); user.setPassword(password); user.setPhone(phone);
            return userRepo.save(user);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id){
        if(userRepo.existsById(id)){ userRepo.deleteById(id); return true; }
        return false;
    }
}