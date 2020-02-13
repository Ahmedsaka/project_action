package io.medalytics.projectactions.Controller;

import io.medalytics.projectactions.Repositories.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionController {

    private ActionRepository actionRepository;

    @Autowired
    public ActionController(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }
}
