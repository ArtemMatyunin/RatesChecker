package ru.alfa.rateschecker.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfa.rateschecker.apiclient.OpenExchangeRatesClient;
import ru.alfa.rateschecker.model.rates.Rates;
import ru.alfa.rateschecker.service.OpExRatesGiver;
import ru.alfa.rateschecker.utils.OpExPropsProvider;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpExRatesGiverImplTest {

    @Autowired
    private OpExPropsProvider propsProvider;

    @MockBean
    private OpenExchangeRatesClient openExchangeRatesClient;

    @Autowired
    private OpExRatesGiver opExRatesGiver;

    private long timestamp = 1631022065L;
    String symbols = "USD";
    private Rates rates;

    @BeforeEach
    void setUp() {
        rates = new Rates();
        rates.setBase("RUB");
        rates.setTimestamp(timestamp);
        Map<String, Float> map = new HashMap<>();
        map.put("USD", 0.2654F);
        rates.setRates(map);
    }

    @Test
    @DisplayName("Testing getLatestRates() With Ok")
    void getLatestRatesWithOk() {
        Mockito.when(openExchangeRatesClient.getLatestRateFromOpExRates(propsProvider.getAppId(),
                        propsProvider.getBase(),
                        symbols))
                .thenReturn(rates);
        assertEquals("RUB", opExRatesGiver.getLatestRates(symbols).getBase());

    }

    @Test
    @DisplayName("Testing getHistoricalRates() with Ok")
    void getHistoricalRatesWithOk() {

        Mockito.when(openExchangeRatesClient.getHistoricalRateFromOpExRate(
                        propsProvider.getHistoricalDate(timestamp),
                        propsProvider.getAppId(),
                        propsProvider.getBase(),
                        symbols))
                .thenReturn(rates);
        assertEquals("RUB", opExRatesGiver.getHistoricalRates(symbols, timestamp).getBase());
    }


    @DisplayName("Testing getHistoricalRates() with incorrect input parameters")
    @Test
    void getHistoricalRatesInputParamException() {
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getHistoricalRates("", timestamp));
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getHistoricalRates("1234", timestamp));
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getHistoricalRates(null, timestamp));
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getHistoricalRates("USD", 83999L));
    }


    @DisplayName("Testing getLatestRates() with incorrect input parameter")
    @Test
    void getLatestRatesInputParamException() {
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getLatestRates(""));
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getLatestRates("1234"));
        assertThrows(IllegalArgumentException.class, () -> opExRatesGiver.getLatestRates(null));

    }

    @Test
    @DisplayName("Testing getLatestRates() with at incorrect  ResponseException")
    void getLatestRatesInputWithAtIncorrectResponseException() {
        rates.setBase("");
        rates.setTimestamp(null);
        rates.getRates().remove("USD");
        Mockito.when(openExchangeRatesClient.getLatestRateFromOpExRates(propsProvider.getAppId(),
                        propsProvider.getBase(),
                        symbols))
                .thenReturn(rates);
        assertThrows(IllegalStateException.class, () -> opExRatesGiver.getLatestRates(symbols));
    }


}