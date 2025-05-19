    package com.javaproacademy.adminpanel.ui.forms;

    import com.javaproacademy.adminpanel.ui.ApiResponseObjects.LoginResponseObj;
    import com.javaproacademy.adminpanel.ui.FDOs.LoginFdo;
    import com.javaproacademy.adminpanel.ui.FDOs.User;
    import com.javaproacademy.adminpanel.ui.services.UserService;
    import com.javaproacademy.adminpanel.ui.views.SignupView;
    import com.vaadin.flow.component.Composite;
    import com.vaadin.flow.component.UI;
    import com.vaadin.flow.component.button.Button;
    import com.vaadin.flow.component.formlayout.FormLayout;
    import com.vaadin.flow.component.html.Span;
    import com.vaadin.flow.component.notification.Notification;
    import com.vaadin.flow.component.textfield.EmailField;
    import com.vaadin.flow.component.textfield.PasswordField;
    import com.vaadin.flow.data.binder.Binder;

    import javax.annotation.Nullable;
    import java.util.Optional;

    public class LoginForm  extends Composite<FormLayout> {

        private final Binder<LoginFdo> binder;

        public LoginForm(){
            EmailField emailField = new EmailField("Email address");
            emailField.setLabel("Email address");
            emailField.getElement().setAttribute("name", "email");
            emailField.setValue("example@email.com");
            emailField.setErrorMessage("Enter a valid email address");
            emailField.setClearButtonVisible(true);

            PasswordField passwordField = new PasswordField("Password");
            passwordField.setLabel("Password");
            passwordField.setValue("Ex@mplePassw0rd");

            Button login = new Button("Log in");
            Button signup = new Button("sign up");

            Span heading = new Span("Log in");
            heading.setWidthFull();
            heading.getStyle().set("text-align", "center")
                    .set("font-size", "24px")
                    .set("font-weight", "bold")
                    .set("margin-bottom", "20px");

            var formlayout = getContent();
            formlayout.setHeight("500px");
            formlayout.setWidth("400px");
            formlayout.add(heading,emailField,passwordField,login,signup);

            //binding form input to the dto
            binder = new Binder<>();
            binder.forField(emailField)
                    .asRequired("email can not be empty")
                    .bind(LoginFdo::getEmail,LoginFdo::setEmail);
            binder.forField(passwordField)
                    .asRequired("can not be empty")
                    .bind(LoginFdo::getPassword, LoginFdo::setPassword);

            //click events
            signup.addClickListener(e-> UI.getCurrent().navigate(SignupView.class));
            login.addClickListener(e->{
                getFormDataObject().ifPresent(loginFdo ->{
                    LoginResponseObj loginResponseObj = new UserService().Login(loginFdo);
                    //check: if status is true?
                    if(loginResponseObj.isStatus()){
                        String username = loginResponseObj.getName();
                        UI.getCurrent().navigate("user/" + username);
                        Notification.show("Login Successful");
                    }else{
                        Notification.show("Bad Credentials");
                    }
                });
            });
        }
        public Optional<LoginFdo> getFormDataObject() {
            if (binder.getBean() == null) {
                binder.setBean(new LoginFdo());
            }
            if (binder.validate().isOk()) {
                return Optional.of(binder.getBean());
            } else {
                return Optional.empty();
            }
        }
    }
