package com.davv1d.view.admin;

import com.davv1d.logic.domain.rental.Rental;
import com.davv1d.logic.domain.rental.RentalDto;
import com.davv1d.logic.mapper.RentalMapper;
import com.davv1d.logic.requests.RentalRequestSender;
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
import java.util.List;

@Route(value = "rentals", layout = AdminView.class)
public class RentalMenu extends VerticalLayout {
    private FormLayout formLayout = new FormLayout();
    private TextField id = new TextField("id");
    private TextField username = new TextField("username");
    private TextField vinNumber = new TextField("vinNumber");
    private TextField brand = new TextField("brand");
    private TextField model = new TextField("model");
    private TextField dateOfRent = new TextField("date of rent");
    private TextField dateOfReturn = new TextField("date of return");
    private Button deleteButton = new Button("delete");
    private Button getAllRentalsButton = new Button("get all");
    private Button getRentalsByUsernameButton = new Button("get by username");
    private Button getRentalsByVinButton = new Button("get by vin");
    private Binder<Rental> binder = new Binder<>(Rental.class);
    private Grid<Rental> rentalGrid = new Grid<>(Rental.class);

    @Autowired
    private RentalRequestSender rentalRequestSender;

    @Autowired
    private RentalMapper rentalMapper;

    public RentalMenu() {
        HorizontalLayout buttonsLayout = new HorizontalLayout(deleteButton, getAllRentalsButton, getRentalsByUsernameButton, getRentalsByVinButton);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addClickListener(this::deleteRental);
        getAllRentalsButton.addClickListener(this::getAllRents);
        getRentalsByUsernameButton.addClickListener(this::getByUsername);
        getRentalsByVinButton.addClickListener(this::getByVin);
        binder.bindInstanceFields(this);
        formLayout.add(id, username, vinNumber, brand, model, dateOfRent, dateOfReturn, buttonsLayout);
        rentalGrid.setColumns("id", "username", "vinNumber", "brand", "model", "dateOfRent", "dateOfReturn");
        add(formLayout, rentalGrid);
        rentalGrid.asSingleSelect().addValueChangeListener(event -> binder.setBean(rentalGrid.asSingleSelect().getValue()));
    }

    private void getByVin(ClickEvent<Button> buttonClickEvent) {
        String vinNumber = this.vinNumber.getValue();
        if (!vinNumber.isEmpty()) {
            ResponseEntity<RentalDto[]> rentalByVin = rentalRequestSender.getRentalByVin(vinNumber);
            List<Rental> rentals = rentalMapper.mapToRentalList(Arrays.asList(rentalByVin.getBody()));
            rentalGrid.setItems(rentals);
        }
    }

    private void getByUsername(ClickEvent<Button> buttonClickEvent) {
        Rental rental = binder.getBean();
        if (!rental.getUsername().isEmpty()) {
            ResponseEntity<RentalDto[]> rentalByUsername = rentalRequestSender.getRentalByUsername(rental.getUsername());
            List<Rental> rentals = rentalMapper.mapToRentalList(Arrays.asList(rentalByUsername.getBody()));
            rentalGrid.setItems(rentals);
        }
    }

    private void deleteRental(ClickEvent<Button> buttonClickEvent) {
        String idValue = id.getValue();
        if (!idValue.isEmpty()) {
            rentalRequestSender.deleteRental(idValue);
            refreshUserGrid();
        }
    }

    private void getAllRents(ClickEvent<Button> buttonClickEvent) {
        refreshUserGrid();
    }

    private void refreshUserGrid() {
        ResponseEntity<RentalDto[]> allRents = rentalRequestSender.getAllRentals();
        List<Rental> rentals = rentalMapper.mapToRentalList(Arrays.asList(allRents.getBody()));
        rentalGrid.setItems(rentals);
    }
}
