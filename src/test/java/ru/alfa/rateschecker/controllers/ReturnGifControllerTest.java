package ru.alfa.rateschecker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.alfa.rateschecker.RatesCheckerApplication;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.service.CheckRateService;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {RatesCheckerApplication.class})
class ReturnGifControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CheckRateService checkRateService;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetGifOk() throws Exception {

        final String result = mockMvc.perform(
                        get(
                                "/gif").queryParam("symbols", "USD")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        final CheckRatesResponse checkRatesResponse = mapper.readValue(result, CheckRatesResponse.class);
        assertEquals("USD", checkRatesResponse.getLatestRates().getBase());
    }

    @Test
    public void testGetGifWithProblem() throws Exception {

        mockMvc.perform(
                        get(
                                "/gif").queryParam("symbols","USDD")
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

    }



}