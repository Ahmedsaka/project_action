package io.medalytics.projectactions.Repositories;

import io.medalytics.projectactions.Models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    Optional<List<Action>> getActionsByProject(Long project_id);
}
