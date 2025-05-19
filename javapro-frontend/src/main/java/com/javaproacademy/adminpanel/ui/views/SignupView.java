package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.forms.SignupForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("sign-in")
public class SignupView extends Main {
    public SignupView(){
        setSizeFull();
        SignupForm signinForm = new SignupForm();

        Div div = new Div();
        div.getStyle()
                .set("display", "flex")
                .set("justify-content", "center")  // center horizontally
                .set("align-items", "center")      // center vertically
                .set("height", "100vh")            // full viewport height
                .set("width", "100vw");
        div.getStyle().set("border", "2px solid black");
        div.getStyle().set("background-color", "lightgray");
        div.add(signinForm);
        add(div);
    }
}
