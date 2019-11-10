package com.davv1d.view.client;

import com.davv1d.logic.domain.user.User;
import com.davv1d.logic.requests.UsersRequestSender;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;

@Route(value = "profile", layout = ClientView.class)
@SpringComponent
@Scope("prototype")
public class ProfileMenu extends VerticalLayout {
    private Label profileLabel = new Label("My profile");
    private TextField usernameField = new TextField("username");
    private TextField emailField = new TextField("email");
    private TextField roleField = new TextField("role");
    private Button editButton = new Button("edit");
    private Button changeEmailButton = new Button("save");
    private UsersRequestSender usersRequestSender;

    public ProfileMenu(@Autowired UsersRequestSender usersRequestSender) {
        this.usersRequestSender = usersRequestSender;
        usernameField.setReadOnly(true);
        emailField.setReadOnly(true);
        roleField.setReadOnly(true);
        editButton.addClickListener(this::enableEditions);
        changeEmailButton.addClickListener(this::changeEmailSave);
        add(profileLabel, usernameField, emailField, roleField, editButton, changeEmailButton);
        refreshUserData();
    }

    private void changeEmailSave(ClickEvent<Button> buttonClickEvent) {
        String email = emailField.getValue();
        if (!email.isEmpty()) {
            usersRequestSender.changeUserEmail(email);
            refreshUserData();
            emailField.setReadOnly(true);
        }
    }

    private void enableEditions(ClickEvent<Button> buttonClickEvent) {
        emailField.setReadOnly(false);
    }

    private void refreshUserData() {
        ResponseEntity<User> loggedUser = usersRequestSender.getLoggedUser();
        User user = loggedUser.getBody();
        if (!loggedUser.getStatusCode().isError() && user != null) {
            usernameField.setValue(user.getUsername());
            emailField.setValue(user.getEmail());
            roleField.setValue(user.getRole());
        }
    }
}
