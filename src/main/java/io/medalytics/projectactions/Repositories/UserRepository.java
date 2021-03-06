package io.medalytics.projectactions.Repositories;

import io.medalytics.projectactions.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByUsername(String username);

    Optional<Users> findUsersById(long id);

    boolean existsByUsername(String username);
}
