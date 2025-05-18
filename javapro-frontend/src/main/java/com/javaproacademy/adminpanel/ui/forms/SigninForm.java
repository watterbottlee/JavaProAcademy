package com.javaproacademy.adminpanel.ui.forms;

import com.javaproacademy.adminpanel.ui.FDOs.UserRecord;
import com.vaadin.flow.component.Composite;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;

public class SigninForm extends Composite<FormLayout> {

    public SigninForm(){

        Binder<UserRecord> binder = new Binder<>(UserRecord.class);
        TextField name = new TextField("Full name");

        EmailField emailField = new EmailField("Email address");
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setValue("julia.scheider@email.com");
        emailField.setErrorMessage("Enter a valid email address");
        emailField.setClearButtonVisible(true);

        PasswordField passwordField = new PasswordField("Password");
        passwordField.setLabel("Password");
        passwordField.setValue("Ex@mplePassw0rd");

        Button button = new Button("Sign in");

        //configure
        var formLayout = getContent();
        formLayout.setWidth("400px");
        formLayout.setHeight("500px");
        formLayout.add(name, emailField, passwordField, button);

        binder.forField(name)
                .asRequired()
                .withValidator(new StringLengthValidator("name must be between 1 and 100 characters", 1, 100))
                .bind(UserRecord::name, null);
        binder.forField(emailField)
                .asRequired("Email can not be empty")
                .bind(UserRecord::email, null);
        binder.forField(passwordField)
                .bind(UserRecord::password, null);
    }
}