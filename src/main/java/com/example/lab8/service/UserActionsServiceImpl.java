package com.example.lab8.service;


import com.example.lab8.entity.UserActions;
import com.example.lab8.repository.UserActionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserActionsServiceImpl implements UserActionsService {

    @Override
    public void info (String text) {
        UserActions userActions = new UserActions();
        userActions.setDescription(text);

        Date date = new Date();
        userActions.setDate_actions(date.toString());
        userActionsRepository.save(userActions);
    }

    private final UserActionsRepository userActionsRepository;

    @Autowired
    public UserActionsServiceImpl(UserActionsRepository userActionsRepository) {
        this.userActionsRepository = userActionsRepository;
    }
}
