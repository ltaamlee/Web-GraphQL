package ltaam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ltaam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
