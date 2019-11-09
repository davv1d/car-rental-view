package com.davv1d.view.client;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("client")
public class ClientView extends VerticalLayout {
    private Label titleLabel;

    public ClientView() {
        titleLabel = new Label("Welcome client");
        add(titleLabel);
    }
}
