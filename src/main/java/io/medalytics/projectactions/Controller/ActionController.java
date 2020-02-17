package io.medalytics.projectactions.Controller;

import io.medalytics.projectactions.Models.Action;
import io.medalytics.projectactions.Payloads.ActionApiPayload;
import io.medalytics.projectactions.Service.ActionIServiceImpl;
import io.medalytics.projectactions.Service.ProjectIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ActionController {

    private ActionIServiceImpl actionIService;
    private ProjectIServiceImpl projectIService;

    @Autowired
    public ActionController(ActionIServiceImpl actionIService, ProjectIServiceImpl projectIService) {
        this.actionIService = actionIService;
        this.projectIService = projectIService;
    }

//    @RequestMapping(value = "/api/projects", method = RequestMethod.POST)
//    public ResponseEntity<String> addProject(@RequestBody ActionApiPayload actionApiPayload) {
//        actionIService.save(
//                Action.builder()
//                        .project(projectIService.findById(actionApiPayload.getProject_id()))
//                        .description(actionApiPayload.getDescription())
//                        .note(actionApiPayload.getNote())
//                        .build()
//        );
//        return new ResponseEntity<>("Action successfully performed", HttpStatus.OK);
//    }

    @RequestMapping(value = "/api/actions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Action>> findAll() {
        return new ResponseEntity(actionIService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/action/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Optional<Action>> findById(@PathVariable long id) {
        return new ResponseEntity(actionIService.findById(id), HttpStatus.OK);
    }


}
