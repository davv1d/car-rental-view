package com.davv1d.view.admin;

import com.davv1d.logic.domain.user.User;
import com.davv1d.logic.requests.UsersRequestSender;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;

@Route(value = "users", layout = AdminView.class)
public class UserMenu extends VerticalLayout {
    private FormLayout formLayout = new FormLayout();
    private TextField username = new TextField("Username");
    private TextField email = new TextField("Email");
    private TextField role = new TextField("role");
    private Button deleteButton = new Button("delete");
    private Button getAllUsersButton = new Button("get all");
    private Button getUserByUsernameButton = new Button("get by username");
    private Binder<User> binder = new Binder<>(User.class);
    private Grid<User> userGrid = new Grid<>(User.class);

    @Autowired
    private UsersRequestSender usersRequestSender;

    public UserMenu() {
        HorizontalLayout buttonsLayout = new HorizontalLayout(deleteButton, getAllUsersButton, getUserByUsernameButton);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addClickListener(this::deleteUser);
        getAllUsersButton.addClickListener(this::getAllUsers);
        getUserByUsernameButton.addClickListener(this::getByUsername);
        binder.bindInstanceFields(this);
        formLayout.add(username, email, role, buttonsLayout);
        userGrid.setColumns("username", "email", "role");
        add(formLayout, userGrid);
        userGrid.asSingleSelect().addValueChangeListener(event -> binder.setBean(userGrid.asSingleSelect().getValue()));
    }

    private void getByUsername(ClickEvent<Button> buttonClickEvent) {
        User user = binder.getBean();
        if (!user.getUsername().isEmpty()) {
            ResponseEntity<User> userByUsername = usersRequestSender.getUserByUsername(user.getUsername());
            userGrid.setItems(Collections.singletonList(userByUsername.getBody()));
        }
    }

    private void deleteUser(ClickEvent<Button> buttonClickEvent) {
        String usernameValue = username.getValue();
        if (!usernameValue.isEmpty()) {
            usersRequestSender.deleteUserByUsername(usernameValue);
            refreshUserGrid();
        }
    }

    private void getAllUsers(ClickEvent<Button> buttonClickEvent) {
        refreshUserGrid();
    }

    private void refreshUserGrid() {
        ResponseEntity<User[]> allUsers = usersRequestSender.getAllUsers();
        userGrid.setItems(Arrays.asList(allUsers.getBody()));
    }
}
