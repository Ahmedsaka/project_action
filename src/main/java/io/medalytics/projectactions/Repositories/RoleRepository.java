package io.medalytics.projectactions.Repositories;

import io.medalytics.projectactions.Models.Role;
import io.medalytics.projectactions.Util.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRolesByType(RoleType role);
}
