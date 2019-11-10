package com.davv1d.view.client;

import com.davv1d.logic.domain.car.Car;
import com.davv1d.logic.domain.rental.SaveRentalDto;
import com.davv1d.logic.requests.CarRequestSender;
import com.davv1d.logic.requests.RentalRequestSender;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;

import java.time.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Route(value = "car-reservation", layout = ClientView.class)
@SpringComponent
@Scope("prototype")
public class CarReservationMenu extends VerticalLayout {
    private final RentalRequestSender rentalRequestSender;
    private FormLayout formLayout = new FormLayout();
    private DatePicker dateOfRent = new DatePicker("Date of rent");
    private DatePicker dateOfReturn = new DatePicker("Date of return");
    private TimePicker timeOfRent = new TimePicker("time of rent");
    private TimePicker timeOfReturn = new TimePicker("time of return");
    private Button findButton = new Button("find cars");
    private Button cancelButton = new Button("cancel");
    private Grid<Car> carGrid = new Grid<>(Car.class);
    private TextField brandField = new TextField("Brand");
    private TextField modelField = new TextField("Model");
    private TextField vinField = new TextField("Vin");
    private Button bookButton = new Button("Book a car");
    private CarRequestSender carRequestSender;

    public CarReservationMenu(@Autowired CarRequestSender carRequestSender, @Autowired RentalRequestSender rentalRequestSender) {
        this.carRequestSender = carRequestSender;
        this.rentalRequestSender = rentalRequestSender;
        carGrid.setColumns("brand", "model", "vinNumber");
        dateOfRent.setMin(LocalDate.now());
        dateOfRent.setValue(LocalDate.now());
        dateOfRent.addValueChangeListener(this::rentValueChange);
        dateOfReturn.setMin(LocalDate.now());
        findButton.addClickListener(this::findCars);
        cancelButton.addClickListener(this::cancelResearch);
        brandField.setReadOnly(true);
        modelField.setReadOnly(true);
        vinField.setReadOnly(true);
        HorizontalLayout selectedCarLayout = new HorizontalLayout();
        bookButton.addClickListener(this::bookCar);
        selectedCarLayout.add(brandField, modelField, vinField);
        formLayout.add(dateOfRent, timeOfRent, dateOfReturn, timeOfReturn, findButton, cancelButton);
        add(formLayout, carGrid, selectedCarLayout, bookButton);
        carGrid.asSingleSelect().addValueChangeListener(this::getValueByGrid);
    }

    private void cancelResearch(ClickEvent<Button> buttonClickEvent) {
        cleanData();
    }

    private void cleanData() {
        setReadOnlyMenu(false);
        refreshGrid(Collections.emptyList());
        brandField.setValue("");
        modelField.setValue("");
        vinField.setValue("");
    }

    private void getValueByGrid(AbstractField.ComponentValueChangeEvent<Grid<Car>, Car> event) {
        Car car = carGrid.asSingleSelect().getValue();
        if (car != null) {
            brandField.setValue(car.getBrand());
            modelField.setValue(car.getModel());
            vinField.setValue(car.getVinNumber());
        }
    }

    private void bookCar(ClickEvent<Button> buttonClickEvent) {
        if (!vinField.getValue().isEmpty()) {
            LocalDateTime dateOfRent = LocalDateTime.of(this.dateOfRent.getValue(), timeOfRent.getValue());
            LocalDateTime dateOfReturn = LocalDateTime.of(this.dateOfReturn.getValue(), timeOfReturn.getValue());
            SaveRentalDto saveRentalDto = new SaveRentalDto(vinField.getValue(), dateOfRent, dateOfReturn);
            rentalRequestSender.createRental(saveRentalDto);
            cleanData();
        }
    }

    private void findCars(ClickEvent<Button> buttonClickEvent) {
        if (dateOfRent.getValue() != null && timeOfRent.getValue() != null && dateOfReturn.getValue() != null && timeOfReturn.getValue() != null) {
            LocalDateTime dateOfRent = LocalDateTime.of(this.dateOfRent.getValue(), timeOfRent.getValue());
            LocalDateTime dateOfReturn = LocalDateTime.of(this.dateOfReturn.getValue(), timeOfReturn.getValue());
            ResponseEntity<Car[]> availabilityCars = carRequestSender.getAvailabilityCars(dateOfRent.toString(), dateOfReturn.toString());
            refreshGrid(Arrays.asList(availabilityCars.getBody()));
            setReadOnlyMenu(true);
        }
    }

    private void setReadOnlyMenu(boolean value) {
        dateOfRent.setReadOnly(value);
        dateOfReturn.setReadOnly(value);
        timeOfRent.setReadOnly(value);
        timeOfReturn.setReadOnly(value);
        findButton.setEnabled(!value);
    }

    private void refreshGrid(List<Car> cars) {
        carGrid.setItems(cars);
    }


    private void rentValueChange(AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate> event) {
            LocalDate value = event.getValue();
            dateOfReturn.setMin(value);
    }
}
