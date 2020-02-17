package io.medalytics.projectactions.Service;

import io.medalytics.projectactions.Models.Projects;
import io.medalytics.projectactions.Repositories.ProjectRepository;
import io.medalytics.projectactions.Service.IServices.ProjectIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectIServiceImpl implements ProjectIService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectIServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void save(Projects projects){
        projectRepository.save(projects);
    }

    @Override
    public List<Projects> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Projects> findById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        projectRepository.deleteById(id);
    }


}
