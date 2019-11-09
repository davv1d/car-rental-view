package com.davv1d.view.main;
import com.davv1d.logic.cookie.CookieMaker;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Route(value = "", absolute = true)
@SpringComponent
@Scope(scopeName = "prototype")
public class MainView extends VerticalLayout implements RouterLayout {
    private Label titleLabel;
    private Button weatherButton;
    private Button currencyButton;
    private Button loginButton;
    private Button logoutButton;
    private HorizontalLayout menu;

    @Autowired
    private CookieMaker cookieMaker;

    public MainView() {
        titleLabel = new Label("Welcome to main view car rental");
        weatherButton = new Button("Weather");
        currencyButton = new Button("Currency rates");
        loginButton = new Button("Login");
        logoutButton = new Button("Logout");
        menu = new HorizontalLayout();
        menu.add(weatherButton, currencyButton, loginButton, logoutButton);
        logoutButton.addClickListener(this::logoutClick);
        logoutButton.setVisible(false);
        currencyButton.addClickListener(this::switchToCurrencyRates);
        weatherButton.addClickListener(this::switchToWeatherView);
        loginButton.addClickListener(this::switchToLogin);
        add(titleLabel, menu);
    }

    private void switchToLogin(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("login"));
    }

    private void switchToWeatherView(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("weather"));
    }

    private void switchToCurrencyRates(ClickEvent<Button> buttonClickEvent) {
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate("currency"));
    }

    public void logoutClick(ClickEvent<Button> buttonClickEvent) {
        cookieMaker.removeCookie();
        loginButton.setVisible(true);
        logoutButton.setVisible(false);
        Router router = UI.getCurrent().getRouter();
        List<RouteData> routes = router.getRoutes();
//        routes.stream()
//                .filter()
        buttonClickEvent.getSource().getUI().ifPresent(ui -> ui.navigate(""));
    }

    public void setVisibleLoginButton(boolean visible) {
        loginButton.setVisible(visible);
    }


    public void setVisibleLogoutButton(boolean visible) {
        logoutButton.setVisible(visible);
    }
}
