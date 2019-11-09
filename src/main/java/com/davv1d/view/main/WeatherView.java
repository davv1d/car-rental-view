package com.davv1d.view.main;

import com.davv1d.logic.domain.weather.Weather;
import com.davv1d.logic.requests.WeatherRequestSender;
import com.davv1d.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Route(value = "weather", layout = MainView.class)
public class WeatherView extends VerticalLayout {

    private static final String WEATHER = "weather";
    private static final String FORECAST = "forecast";
    private TextField cityTextField = new TextField("City");
    private Button submitButton = new Button("Submit");
    private RadioButtonGroup<String> radioButtonGroup = new RadioButtonGroup<>();
    private Grid<Weather> weatherGrid = new Grid<>(Weather.class);


    @Autowired
    private WeatherRequestSender weatherRequestSender;

    public WeatherView() {
        radioButtonGroup.setItems(WEATHER, FORECAST);
        radioButtonGroup.setValue(WEATHER);
        weatherGrid.setColumns("date", "tempMax", "temp", "percentageCloudy", "deg", "description", "humidity", "pressure", "speed", "tempMin");
        submitButton.addClickListener(this::getConditionClick);
        add(cityTextField,radioButtonGroup, submitButton, weatherGrid);
    }

    private void getConditionClick(ClickEvent<Button> buttonClickEvent) {
        String city = cityTextField.getValue();
        if (!city.isEmpty()) {
            if (radioButtonGroup.getValue().equals(WEATHER)) {
                ResponseEntity<Weather> conditionsWeather = weatherRequestSender.getConditionWeather(city);
                refreshGrid(Arrays.asList(conditionsWeather.getBody()));
            } else {
                ResponseEntity<Weather[]> conditionsForecast = weatherRequestSender.getConditionsForecast(city);
                refreshGrid(Arrays.asList(conditionsForecast.getBody()));
            }
        }
    }

    private void refreshGrid(List<Weather> weathers) {
        weatherGrid.setItems(weathers);
    }

}
