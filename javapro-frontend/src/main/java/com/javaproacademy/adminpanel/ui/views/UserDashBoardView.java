package com.javaproacademy.adminpanel.ui.views;

import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("user/:name")
public class UserDashBoardView extends Main implements BeforeEnterObserver {

    private static final String PARAM_USER_NAME = "name";

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String username = event.getRouteParameters().get(PARAM_USER_NAME).get();
        String welcome = "Welcome to Java Pro Academy ";
        add(welcome+username);
    }
}
