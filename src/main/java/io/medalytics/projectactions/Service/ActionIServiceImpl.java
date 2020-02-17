package io.medalytics.projectactions.Service;

import io.medalytics.projectactions.Models.Action;
import io.medalytics.projectactions.Repositories.ActionRepository;
import io.medalytics.projectactions.Service.IServices.ActionIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionIServiceImpl implements ActionIService {

    private ActionRepository actionRepository;

    @Autowired
    public ActionIServiceImpl(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public void save(Action action) {
        actionRepository.save(action);
    }

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Optional<Action> findById(long id) {
        return actionRepository.findById(id);
    }

    @Override
    public boolean existsById(long id) {
        return actionRepository.existsById(id);
    }

    @Override
    public void deleteActionById(long id) {
        actionRepository.deleteById(id);
    }

    @Override
    public Optional<List<Action>> getActionsByProject_id(long id) {
        return actionRepository.getActionsByProject(id);
    }
}
