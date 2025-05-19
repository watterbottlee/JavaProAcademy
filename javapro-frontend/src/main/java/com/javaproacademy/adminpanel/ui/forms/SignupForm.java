package com.javaproacademy.adminpanel.ui.forms;

import com.javaproacademy.adminpanel.ui.FDOs.User;
import com.javaproacademy.adminpanel.ui.services.UserService;
import com.vaadin.flow.component.Composite;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import javax.annotation.Nullable;
import java.util.Optional;


public class SignupForm extends Composite<FormLayout> {

    private final Binder<User> binder;
    private @Nullable User formDataObject;
    public SignupForm(){

        TextField name = new TextField("Full name");
        name.setValue("john doe");

        EmailField emailField = new EmailField("Email address");
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setValue("jogn@email.com");
        emailField.setErrorMessage("Enter a valid email address");
        emailField.setClearButtonVisible(true);

        PasswordField passwordField = new PasswordField("Password");
        passwordField.setLabel("Password");
        passwordField.setValue("Ex@12#abc");

        Button button = new Button("Sign up");
        button.addClickListener(click->{
            getFormDataObject().ifPresent(user ->{
                User savedUser =new UserService().createUser(user);
                Notification.show("created user: "+ savedUser.getName());
            });
        });


        //configure
        Span heading = new Span("Sign up");
        heading.setWidthFull();
        heading.getStyle().set("text-align", "center")
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("margin-bottom", "20px");
        var formLayout = getContent();
        formLayout.setWidth("400px");
        formLayout.setHeight("500px");
        formLayout.add(heading, name, emailField, passwordField, button);
        formLayout.getStyle().set("border", "2px solid black");

        //binding work
        binder = new Binder<>();
        binder.forField(name)
                .asRequired("name can not be empty")
                .bind(User::getName, User::setName);
        binder.forField(emailField)
                .asRequired("email can not be empty")
                .bind(User::getEmail, User::setEmail);
        binder.forField(passwordField)
                .bind(User::getPassword, User::setPassword);

    }
    //write-through mode
    //    public void setFormDataObject(@Nullable User formDataObject){
    //        binder.setBean(formDataObject);
    //    }
    public Optional<User> getFormDataObject() {
        if (binder.getBean() == null) {
            binder.setBean(new User());
        }
        if (binder.validate().isOk()) {
            return Optional.of(binder.getBean());
        } else {
            return Optional.empty();
        }
    }

}