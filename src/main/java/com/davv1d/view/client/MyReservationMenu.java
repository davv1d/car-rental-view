package com.davv1d.view.client;

import com.davv1d.logic.domain.car.Car;
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
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Route(value = "my-reservation", layout = ClientView.class)
@SpringComponent
@Scope("prototype")
public class MyReservationMenu extends VerticalLayout {
    private FormLayout formLayout = new FormLayout();
    private TextField id = new TextField("id");
    private TextField username = new TextField("username");
    private TextField vinNumber = new TextField("vinNumber");
    private TextField brand = new TextField("brand");
    private TextField model = new TextField("model");
    private TextField dateOfRent = new TextField("date of rent");
    private TextField dateOfReturn = new TextField("date of return");
    private Button deleteButton = new Button("delete");
    private Binder<Rental> binder = new Binder<>(Rental.class);
    private Grid<Rental> rentalGrid = new Grid<>(Rental.class);
    private RentalRequestSender rentalRequestSender;
    private RentalMapper rentalMapper;

    public MyReservationMenu(@Autowired RentalRequestSender rentalRequestSender, @Autowired RentalMapper rentalMapper) {
        this.rentalRequestSender = rentalRequestSender;
        this.rentalMapper = rentalMapper;
        HorizontalLayout buttonsLayout = new HorizontalLayout(deleteButton);
        deleteButton.addClickListener(this::deleteRental);
        binder.bindInstanceFields(this);
        formLayout.add(id, username, vinNumber, brand, model, dateOfRent, dateOfReturn, buttonsLayout);
        rentalGrid.setColumns("id", "username", "vinNumber", "brand", "model", "dateOfRent", "dateOfReturn");
        add(formLayout, rentalGrid);
        rentalGrid.asSingleSelect().addValueChangeListener(event -> binder.setBean(rentalGrid.asSingleSelect().getValue()));
        refreshUserGrid();
    }

    private void deleteRental(ClickEvent<Button> buttonClickEvent) {
        String idValue = id.getValue();
        if (!idValue.isEmpty()) {
            rentalRequestSender.deleteRental(idValue);
            refreshUserGrid();
        }
    }

    private void refreshUserGrid() {
        ResponseEntity<RentalDto[]> loggedUserRentals = rentalRequestSender.getLoggedUserRentals();
        List<Rental> rentals = rentalMapper.mapToRentalList(Arrays.asList(loggedUserRentals.getBody()));
        rentalGrid.setItems(rentals);
    }

}
