package com.davv1d.view.admin;

import com.davv1d.logic.domain.car.Car;
import com.davv1d.logic.requests.CarRequestSender;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class CarForm extends FormLayout {
    private TextField brand = new TextField("brand");
    private TextField model = new TextField("model");
    private TextField vinNumber = new TextField("vin");
    private Checkbox availability = new Checkbox("availability");
    private Button saveButton = new Button("save");
    private Button deleteButton = new Button("delete");
    private Button changeAvailabilityButton = new Button("Change availability");
    private Binder<Car> binder = new Binder<>(Car.class);

    @Autowired
    private CarRequestSender carRequestSender;

    public CarForm() {
        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, deleteButton, changeAvailabilityButton);
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.addClickListener(this::saveCar);
        deleteButton.addClickListener(this::deleteCar);
        changeAvailabilityButton.addClickListener(this::changeAvailability);
        binder.bindInstanceFields(this);
        add(brand, model, vinNumber, availability, buttonsLayout);
    }

    private void changeAvailability(ClickEvent<Button> buttonClickEvent) {
        Car car = binder.getBean();
        carRequestSender.changeAvailabilityCar(car.getVinNumber());
    }

    private void deleteCar(ClickEvent<Button> buttonClickEvent) {
        Car car = binder.getBean();
        carRequestSender.deleteCarByVin(car.getVinNumber());
    }

    private void saveCar(ClickEvent<Button> buttonClickEvent) {
        String brandValue = brand.getValue();
        String modelValue = model.getValue();
        String vinNumberValue = vinNumber.getValue();
        Boolean availabilityValue = availability.getValue();
        if (!brandValue.isEmpty() && !modelValue.isEmpty() && !vinNumberValue.isEmpty()) {
            Car car = new Car(vinNumberValue, brandValue, modelValue, availabilityValue);
            carRequestSender.createCar(car);
        }
    }

    public void setCar(Car car) {
        binder.setBean(car);
    }
    public Car getCar() {
        return binder.getBean();
    }
}
