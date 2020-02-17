package io.medalytics.projectactions.Service;

import io.medalytics.projectactions.Models.Projects;
import io.medalytics.projectactions.Service.IServices.ProjectIService;

import java.util.List;
import java.util.Optional;

public class ProjectPilotIServiceImpl implements ProjectIService {
    @Override
    public void save(Projects projects) {

    }

    @Override
    public List<Projects> findAll() {
        return null;
    }

    @Override
    public Optional<Projects> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(long id) {
        return false;
    }

    @Override
    public void deleteById(long id) {

    }
}
