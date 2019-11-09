package com.davv1d.view.admin;

import com.davv1d.logic.domain.car.Car;
import com.davv1d.logic.requests.CarRequestSender;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Route(value = "carsMenu", layout = AdminView.class)
@SpringComponent
@Scope("prototype")
public class CarsMenu extends VerticalLayout {
    private Button getCarsButton = new Button("get all cars");
    private Button getCarsByVin = new Button("get by vin");
    private Grid<Car> carGrid = new Grid<>(Car.class);
    private HorizontalLayout buttonMenu = new HorizontalLayout();
    private CarForm carForm;

    @Autowired
    private CarRequestSender carRequestSender;

    public CarsMenu(@Autowired CarForm carForm) {
        this.carForm = carForm;
        carGrid.setColumns("brand", "model", "vinNumber", "availability");
        buttonMenu.add(getCarsButton, getCarsByVin);
        getCarsButton.addClickListener(this::getCars);
        getCarsByVin.addClickListener(this::getCarByVin);
        add(carForm, buttonMenu, carGrid);
        carGrid.asSingleSelect().addValueChangeListener(event -> carForm.setCar(carGrid.asSingleSelect().getValue()));
    }

    private void getCarByVin(ClickEvent<Button> buttonClickEvent) {
        String vinNumber = carForm.getCar().getVinNumber();
        if (!vinNumber.isEmpty()) {
            ResponseEntity<Car> carByVin = carRequestSender.getCarByVin(vinNumber);
            carGrid.setItems(Collections.singletonList(carByVin.getBody()));
        }
    }

    private void getCars(ClickEvent<Button> buttonClickEvent) {
        ResponseEntity<Car[]> cars = carRequestSender.getCars();
        carGrid.setItems(Arrays.asList(cars.getBody()));
    }
}
