package io.medalytics.projectactions.Service.IServices;

import io.medalytics.projectactions.Models.Projects;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface ProjectIService {
    void save(Projects projects);

    List<Projects> findAll();

    Optional<Projects> findById(long id);
    
    boolean existsById(long id);

    void deleteById(long id);
}
