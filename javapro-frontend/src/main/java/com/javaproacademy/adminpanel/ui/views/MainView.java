package com.javaproacademy.adminpanel.ui.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends Main {
    public MainView(){
        setText("this is the home page");
        Button button = new Button("see users");
        add(button);
        button.addClickListener(event ->  UI.getCurrent().navigate(UserView.class));
    }
}
