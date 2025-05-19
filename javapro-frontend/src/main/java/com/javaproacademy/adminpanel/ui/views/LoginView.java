package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.forms.LoginForm;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends Main {
    public LoginView(){
        setWidthFull();
        LoginForm loginForm = new LoginForm();
        add(loginForm);
    }
}
