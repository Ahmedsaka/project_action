package io.medalytics.projectactions.Controller;

import io.medalytics.projectactions.Exceptions.ActionsNotFoundException;
import io.medalytics.projectactions.Exceptions.ProjectNotFoundException;
import io.medalytics.projectactions.Models.Action;
import io.medalytics.projectactions.Models.Projects;
import io.medalytics.projectactions.Payloads.ProjectApiPayload;
import io.medalytics.projectactions.Repositories.ActionRepository;
import io.medalytics.projectactions.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ProjectController {

    private ProjectRepository projectRepository;
    private ActionRepository actionRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, ActionRepository actionRepository) {
        this.projectRepository = projectRepository;
        this.actionRepository = actionRepository;
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.POST)
    public ResponseEntity<String> addProject(@RequestBody ProjectApiPayload projectApiPayload) {
        Projects projects = new Projects();
        projects.setName(projectApiPayload.getName());
        projects.setDescription(projectApiPayload.getDescription());
        projects.setCompleted(projectApiPayload.isCompleted());
        projectRepository.save(projects);
        return new ResponseEntity<>("Project successfully uploaded", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Projects>> getProject() {
        return new ResponseEntity(projectRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Projects>> getProjectById(@PathVariable long id) {
        return new ResponseEntity(projectRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateProject(@RequestBody Projects projects, @PathVariable long id) {
        if (projectRepository.existsById(id)){
            projectRepository.save(projects);
        }else {
            throw new ProjectNotFoundException("No project associated with id :"+ id);
        }
        return new ResponseEntity<>("Project successfully updated", HttpStatus.OK);
    }


    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> updateProject(@PathVariable long id) {
        if (projectRepository.existsById(id)){
            projectRepository.deleteById(id);
        }else {
            throw new ProjectNotFoundException("Project could not be deleted :"+ id);
        }
        return new ResponseEntity<>("Project successfully deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{project_id}/actions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<List<Action>>> getProject(@PathVariable Long project_id) {
       List<Action> actions = actionRepository.getActionsByProject(project_id)
                .orElseThrow(()-> new ActionsNotFoundException("There no associated actions with this project"));
        return new ResponseEntity(actions, HttpStatus.OK);
    }

}
