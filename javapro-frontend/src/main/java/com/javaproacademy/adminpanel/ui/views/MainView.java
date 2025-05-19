package com.javaproacademy.adminpanel.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends Main {
    public MainView(){
        setText("this is the home page");
        Button seeUsers = new Button("see users");
        Button signUp = new Button("Sign up");
        add(seeUsers);
        add(signUp);
        seeUsers.addClickListener(event ->  UI.getCurrent().navigate(UserView.class));
        signUp.addClickListener(e-> UI.getCurrent().navigate(SignupView.class));

    }
}
