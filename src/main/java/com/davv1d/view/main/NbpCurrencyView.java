package com.davv1d.view.main;

import com.davv1d.logic.domain.nbp.Rates;
import com.davv1d.logic.requests.NbpCurrencyRatesRequestSender;
import com.davv1d.view.main.MainView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@Route(value = "currency", layout = MainView.class)
public class NbpCurrencyView extends VerticalLayout {
    private Grid<Rates> ratesGrid;

    public NbpCurrencyView(@Autowired NbpCurrencyRatesRequestSender nbpCurrencyRatesRequestSender) {
        ratesGrid = new Grid<>(Rates.class);
        ratesGrid.setColumns("code", "ask", "currency", "bid");
        add(ratesGrid);
        getCurrencies(nbpCurrencyRatesRequestSender);
    }

    private void getCurrencies(NbpCurrencyRatesRequestSender nbpCurrencyRatesRequestSender) {
        ResponseEntity<Rates[]> currencyRates = nbpCurrencyRatesRequestSender.getCurrencyRates();
        refreshGrid(currencyRates.getBody());
    }

    private void refreshGrid(Rates[] rates) {
        ratesGrid.setItems(Arrays.asList(rates));
    }
}
