package com.davv1d.view.client;

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

@Route(value = "client")
@SpringComponent
@Scope("prototype")
public class ClientView extends VerticalLayout implements RouterLayout {
    private Label titleLabel = new Label("Client view");
    private HorizontalLayout menu = new HorizontalLayout();
    private Button profileButton = new Button("Profile");
    private Button carReservationButton = new Button("Car reservation");
    private Button myReservationButton = new Button("My reservations");
    private Button logoutButton = new Button("Logout");

    public ClientView(@Autowired MainView mainView) {
        profileButton.addClickListener(this::switchToProfile);
        carReservationButton.addClickListener(this::switchToCarReservation);
        myReservationButton.addClickListener(this::switchToMyReservation);
        logoutButton.addClickListener(mainView::logoutClick);
        menu.add(profileButton, carReservationButton, myReservationButton, logoutButton);
        add(titleLabel, menu);
    }

    private void switchToMyReservation(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("my-reservation"));
    }

    private void switchToCarReservation(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("car-reservation"));
    }

    private void switchToProfile(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("profile"));
    }
}
