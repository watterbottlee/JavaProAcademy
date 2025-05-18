package com.javaproacademy.adminpanel.ui.forms;

import com.javaproacademy.adminpanel.ui.FDOs.UserRecord;
import com.vaadin.flow.component.Composite;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nullable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class SigninForm extends Composite<FormLayout> {
    final private Binder<UserRecord> binder = new Binder<>(UserRecord.class);
    private UserRecord currentUserRecord;
    private final TextField name = new TextField("Full name");
    private final EmailField emailField = new EmailField("Email address");
    private final PasswordField passwordField = new PasswordField("Password");
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SigninForm(){
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setValue("julia.scheider@email.com");
        emailField.setErrorMessage("Enter a valid email address");
        emailField.setClearButtonVisible(true);

        passwordField.setLabel("Password");
        passwordField.setValue("Ex@mplePassw0rd");

        Button button = new Button("Sign in");
        button.addClickListener(event -> onSignInClicked());

        //configure
        var formLayout = getContent();
        formLayout.setWidth("400px");  // fixed width for rectangular box
        formLayout.setHeight("500px"); // fixed height to make it tall
        formLayout.add(name, emailField, passwordField, button); // Added button to the layout

        binder.forField(name)
                .asRequired()
                .withValidator(new StringLengthValidator("name must be between 1 and 100 characters", 1, 100))
                .bind(UserRecord::name, null);
        binder.forField(emailField)
                .asRequired("Email can not be empty")
                .bind(UserRecord::email, null);
        binder.forField(passwordField)
                .bind(UserRecord::password, null);

        addValueChangeListeners();
    }

    private void addValueChangeListeners() {
        name.addValueChangeListener(event -> updateUserRecord());
        emailField.addValueChangeListener(event -> updateUserRecord());
        passwordField.addValueChangeListener(event -> updateUserRecord());
    }

    private void updateUserRecord() {
        currentUserRecord = new UserRecord(
                name.getValue(),
                emailField.getValue(),
                passwordField.getValue()
        );
    }

    public void setFormDataObject(@Nullable UserRecord formDataObject) {
        if (formDataObject != null) {
            currentUserRecord = formDataObject;
            binder.readBean(formDataObject);
        } else {
            currentUserRecord = null;
            binder.readBean(null);
        }
    }

    public Optional<UserRecord> getFormDataObject() {
        try {
            binder.writeBean(currentUserRecord); // This will validate fields
            return Optional.of(currentUserRecord);
        } catch (ValidationException ex) {
            return Optional.empty();
        }
    }

    private void onSignInClicked() {
        Optional<UserRecord> userOptional = getFormDataObject();
        if (userOptional.isEmpty()) {
            Notification.show("Please fix validation errors before submitting.");
            return;
        }

        UserRecord user = userOptional.get();

        try {
            String jsonRequest = objectMapper.writeValueAsString(user);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/users"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();

            // Send async request to avoid blocking UI thread
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        // Show response JSON in UI thread
                        getUI().ifPresent(ui -> ui.access(() -> {
                            if (response.statusCode() == 200 || response.statusCode() == 201) {
                                Notification.show("Success! Response: " + response.body(), 5000, Notification.Position.MIDDLE);
                            } else {
                                Notification.show("Failed! Status: " + response.statusCode() + " Response: " + response.body(), 5000, Notification.Position.MIDDLE);
                            }
                        }));
                    })
                    .exceptionally(ex -> {
                        getUI().ifPresent(ui -> ui.access(() -> {
                            Notification.show("Error: " + ex.getMessage(), 5000, Notification.Position.MIDDLE);
                        }));
                        return null;
                    });

        } catch (Exception e) {
            Notification.show("Error preparing request: " + e.getMessage());
        }
    }
}