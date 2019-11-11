package com.davv1d.view.main;

import com.davv1d.logic.domain.login.SingUp;
import com.davv1d.logic.requests.LoginRequestSender;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "sign-up", layout = MainView.class)
public class SignUpMenu extends FormLayout {
    private TextField usernameField = new TextField("Username");
    private PasswordField passwordField = new PasswordField("Password");
    private TextField emailField = new TextField("Email");
    private Button submitButton = new Button("Submit");

    @Autowired
    private LoginRequestSender loginRequestSender;

    public SignUpMenu() {
        submitButton.addClickListener(this::registerUser);
        add(usernameField, passwordField,emailField, submitButton);
    }

    private void registerUser(ClickEvent<Button> buttonClickEvent) {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            SingUp singUp = new SingUp(username, password, email, "client");
            loginRequestSender.signUp(singUp);
        }
    }
}
