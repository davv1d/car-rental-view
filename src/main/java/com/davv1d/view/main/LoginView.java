package com.davv1d.view.main;

import com.davv1d.logic.UserDataSaver;
import com.davv1d.logic.domain.login.Login;
import com.davv1d.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Route(value = "login", layout = MainView.class)
public class LoginView extends VerticalLayout {
    private TextField usernameTestField;
    private PasswordField passwordField;
    private Button submitButton;

    @Autowired
    private UserDataSaver userDataSaver;

    @Autowired
    private MainView mainView;

    public LoginView() {
        this.usernameTestField = new TextField("username");
        this.passwordField = new PasswordField("password");
        this.submitButton = new Button("Submit");
        submitButton.addClickListener(this::loginSubmit);
        add(usernameTestField, passwordField, submitButton);
    }

    private void loginSubmit(ClickEvent<Button> buttonClickEvent) {
        Login login = new Login(usernameTestField.getValue(), passwordField.getValue());
        ResponseEntity<String> responseEntity = userDataSaver.saveUserLoginData(login);
        if (!responseEntity.getStatusCode().isError()) {
            String role = responseEntity.getBody();
            mainView.setVisibleLoginButton(false);
            mainView.setVisibleLogoutButton(true);
            if (role.contains("ADMIN")) {
                buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("admin"));
            } else {
                buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("client"));
            }
        }
    }
}
