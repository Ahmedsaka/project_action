package io.medalytics.projectactions.Service.IServices;

import io.medalytics.projectactions.Models.Action;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ActionIService {

    void save(Action action);

    List<Action> findAll();

    Optional<Action> findById(long id);

    boolean existsById(long id);

    void deleteActionById(long id);

    Optional<List<Action>> getActionsByProject_id(long id);
}
