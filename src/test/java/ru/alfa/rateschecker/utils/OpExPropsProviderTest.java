package ru.alfa.rateschecker.utils;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OpExPropsProviderTest {


    @Autowired
    private OpExPropsProvider propsProvider;
    private long timestamp;

    @BeforeEach
    void setUp() {
        timestamp = 1631022065L;
    }

    @Test
    @DisplayName("Testing getHistoricalDate() when all ok")
    void getHistoricalDateWithOk() {

        assertEquals("2021-09-06", propsProvider.getHistoricalDate(timestamp));
    }

    @Test
    @DisplayName("Testing getHistoricalDate() with incorrect parameter timestamp")
    void getHistoricalDateWithException() {
        timestamp = 0L;
        assertThrows(IllegalArgumentException.class, () -> propsProvider.getHistoricalDate(timestamp));
    }
}