package com.javaproacademy.adminpanel.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route("home")
@RouteAlias("")
public class MainView extends VerticalLayout {
    public MainView(){
        String text = "this is the home page";
        Button logIn = new Button("Log in");
        Button signUp = new Button("Sign up");
        Button seeUsers = new Button("see users");
        add(text);
        add(logIn);
        add(signUp);
        add(seeUsers);

        logIn.addClickListener(e->UI.getCurrent().navigate(LoginView.class));
        signUp.addClickListener(e-> UI.getCurrent().navigate(SignupView.class));
        seeUsers.addClickListener(event ->  UI.getCurrent().navigate(UserView.class));

    }
}
