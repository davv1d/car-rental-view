package com.davv1d.view.admin;

import com.davv1d.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Route("admin")
@SpringComponent
@Scope("prototype")
public class AdminView extends VerticalLayout implements RouterLayout {
    private Label titleLabel;
    private HorizontalLayout menu;
    private Button carButton;
    private Button usersButton;
    private Button rentalButton;
    private Button logoutButton;

    public AdminView(@Autowired MainView mainView) {
        titleLabel = new Label("Welcome admin");
        menu = new HorizontalLayout();
        carButton = new Button("cars");
        carButton.addClickListener(this::switchCar);
        usersButton = new Button("users");
        usersButton.addClickListener(this::switchUsers);
        rentalButton = new Button("rentals");
        rentalButton.addClickListener(this::switchRentals);
        logoutButton = new Button("logout");
        logoutButton.addClickListener(mainView::logoutClick);
        menu.add(carButton, usersButton, rentalButton, logoutButton);
        add(titleLabel, menu);
    }

    private void switchRentals(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("rentals"));
    }

    private void switchUsers(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("users"));
    }

    private void switchCar(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("carsMenu"));
    }
}
