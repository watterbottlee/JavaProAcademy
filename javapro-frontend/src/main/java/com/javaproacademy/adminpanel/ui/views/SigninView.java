package com.javaproacademy.adminpanel.ui.views;

import com.javaproacademy.adminpanel.ui.forms.SigninForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("sign-in")
public class SigninView extends VerticalLayout {
    public SigninView(){
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);  // Vertical centering
        setAlignItems(Alignment.CENTER);                   // Horizontal centering

        SigninForm signinForm = new SigninForm();
        add(signinForm);
    }

}
