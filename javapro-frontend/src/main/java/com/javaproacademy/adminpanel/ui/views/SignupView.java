package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.forms.SignupForm;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.Route;

@Route("sign-up")
public class SignupView extends Main {
    public SignupView(){
        setSizeFull();
        SignupForm signinForm = new SignupForm();

        Div div = new Div();
        div.getStyle()
                .set("display", "flex")
                .set("justify-content", "center")
                .set("align-items", "center")
                .set("height", "100vh")
                .set("width", "100vw");
        div.add(signinForm);
        add(div);
    }
}
