package io.medalytics.projectactions.Controller;

import io.medalytics.projectactions.Exceptions.ActionsNotFoundException;
import io.medalytics.projectactions.Exceptions.ProjectNotFoundException;
import io.medalytics.projectactions.Models.Action;
import io.medalytics.projectactions.Models.Projects;
import io.medalytics.projectactions.Payloads.ProjectApiPayload;
import io.medalytics.projectactions.Service.ActionIServiceImpl;
import io.medalytics.projectactions.Service.ProjectIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class ProjectController {

    private ActionIServiceImpl actionIService;
    private ProjectIServiceImpl projectIService;

    @Autowired
    public ProjectController(ProjectIServiceImpl projectIService, ActionIServiceImpl actionIService) {
        this.actionIService = actionIService;
        this.projectIService = projectIService;
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.POST)
    public ResponseEntity<String> addProject(@RequestBody ProjectApiPayload projectApiPayload) {
        projectIService.save(
                Projects.builder()
                        .name(projectApiPayload.getName())
                        .description(projectApiPayload.getDescription())
                        .completed(projectApiPayload.isCompleted())
                        .build()
        );
        return new ResponseEntity<>("Project successfully uploaded", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Projects>> getProject() {
        return new ResponseEntity(projectIService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Projects>> getProjectById(@PathVariable long id) {
        return new ResponseEntity(projectIService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateProject(@RequestBody Projects projects, @PathVariable long id) {
        if (projectIService.existsById(id)){
            projectIService.save(projects);
        }else {
            throw new ProjectNotFoundException("No project associated with id :"+ id);
        }
        return new ResponseEntity<>("Project successfully updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteProject(@PathVariable long id) {
        if (projectIService.existsById(id)){
            projectIService.deleteById(id);
        }else {
            throw new ProjectNotFoundException("Project could not be deleted :"+ id);
        }
        return new ResponseEntity<>("Project successfully deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{project_id}/actions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<List<Action>>> getProject(@PathVariable Long project_id) {
       List<Action> actions = actionIService.getActionsByProject_id(project_id)
                .orElseThrow(()-> new ActionsNotFoundException("There no associated actions with this project"));
        return new ResponseEntity(actions, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects/{project_id}/actions/{action_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteActionByProject(@PathVariable long project_id, @PathVariable long action_id) {
        if (projectIService.existsById(project_id)){
            if (actionIService.existsById(action_id)) actionIService.deleteActionById(action_id);
            else throw new ProjectNotFoundException("No action to delete");
        }else {
            throw new ProjectNotFoundException("Project of action could not be deleted");
        }
        return new ResponseEntity<>("Project successfully deleted", HttpStatus.OK);
    }
}
