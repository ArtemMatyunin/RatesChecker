package ru.alfa.rateschecker.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.model.gif.Gif;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;
import ru.alfa.rateschecker.service.CheckRateService;
import ru.alfa.rateschecker.service.GetGifOnConditionGiver;
import ru.alfa.rateschecker.service.OpExRatesGiver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckRateServiceImplTest {

    @Autowired
    private CheckRateService checkRateService;

    @MockBean
    private GetGifOnConditionGiver getGifOnConditionGiver;

    @MockBean
    private OpExRatesGiver ratesGiver;




    private final String symbols = "USD";
    private final Rates gottenLatestRates = new Rates();
    private final Rates gottenHistoricalRates = new Rates();
    private final Gif higherGif = new Gif();
    private final GifData higherGifData = new GifData();
    private final Gif lowerGif = new Gif();
    private final GifData lowerGifData = new GifData();

    @BeforeEach
    void setUp() {
        gottenLatestRates.setBase("RUB");
        long timestamp = 1631022065L;
        gottenLatestRates.setTimestamp(timestamp);
        Map<String, Float> latestMap = new HashMap<>();
        gottenLatestRates.setRates(latestMap);
        Mockito.when(ratesGiver.getLatestRates(symbols))
                .thenReturn(gottenLatestRates);

        higherGif.setId("higher");
        higherGifData.setData(higherGif);
        Mockito.when(getGifOnConditionGiver.getGifOnCondition(true))
                .thenReturn(higherGifData);

        gottenHistoricalRates.setBase("RUB");
        gottenHistoricalRates.setTimestamp(timestamp);
        Map<String, Float> historicalMap = new HashMap<>();
        gottenHistoricalRates.setRates(historicalMap);
        Mockito.when(ratesGiver.getHistoricalRates(symbols, timestamp))
                .thenReturn(gottenHistoricalRates);

        lowerGif.setId("lower");
        lowerGifData.setData(lowerGif);
        Mockito.when(getGifOnConditionGiver.getGifOnCondition(false))
                .thenReturn(lowerGifData);
    }

    @Test
    @DisplayName("Testing getGifOnRates when latest biggest than historical ")
    void getGifOnRatesBiggest() {
        gottenLatestRates.getRates().put("USD", 0.2345F);
        gottenHistoricalRates.getRates().put("USD",0.2344F);
        CheckRatesResponse checkRatesResponse = checkRateService.getGifOnRates(symbols);
        assertEquals("higher", checkRatesResponse.getGifData().getData().getId() );
    }


    @Test
    @DisplayName("Testing getGifOnRates when latest less than historical ")
    void getGifOnRatesLess() {
        gottenLatestRates.getRates().put("USD", 0.2344F);
        gottenHistoricalRates.getRates().put("USD",0.2345F);
        CheckRatesResponse checkRatesResponse = checkRateService.getGifOnRates(symbols);
        assertEquals("lower", checkRatesResponse.getGifData().getData().getId());

    }

    @Test
    @DisplayName("Testing getGifOnRates when latest equal historical ")
    void getGifOnRatesPar() {
        gottenLatestRates.getRates().put("USD", 0.2345F);
        gottenHistoricalRates.getRates().put("USD",0.2345F);
        CheckRatesResponse checkRatesResponse = checkRateService.getGifOnRates(symbols);
        assertEquals("lower", checkRatesResponse.getGifData().getData().getId());

    }

}