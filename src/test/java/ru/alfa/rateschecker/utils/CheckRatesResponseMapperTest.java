package ru.alfa.rateschecker.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class CheckRatesResponseMapperTest {
    @Autowired
    private CheckRatesResponseMapper checkRatesResponseMapper;


    @Test
    @DisplayName("Test method mapToCheckRatesResponse on produced CheckRatesResponse object")
    void mapToCheckRatesResponseWithOk() {
        GifData gifData = new GifData();
        Rates historicalRates = new Rates();
        Rates latestRates = new Rates();
        assertNotNull(checkRatesResponseMapper.mapToCheckRatesResponse(gifData, historicalRates, latestRates));
        assertSame(CheckRatesResponse.class,
                checkRatesResponseMapper.mapToCheckRatesResponse(gifData, historicalRates, latestRates).getClass());
    }


    @ParameterizedTest
    @MethodSource("getParameters")
    @DisplayName("Testing mapToCheckRatesResponse with incorrect input parameters")
    void mapToCheckRatesResponseWithException(ArgumentsAccessor accessor) {
        assertThrows(IllegalArgumentException.class,
                () -> checkRatesResponseMapper.mapToCheckRatesResponse(
                        (GifData) accessor.get(0),
                        (Rates) accessor.get(1),
                        (Rates) accessor.get(2))
        );
    }

    private Stream<Arguments> getParameters() {
        return Stream.of(
                Arguments.of(null, new Rates(), new Rates()),
                Arguments.of(new GifData(), null, new Rates()),
                Arguments.of(new GifData(), new Rates(), null),
                Arguments.of(null, null, null)
        );
    }


}