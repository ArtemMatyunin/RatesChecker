package ru.alfa.rateschecker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.service.CheckRateService;

/***
 * @author Артём
 * Rest-Контроллер проверки курса валюты
 *
 */
@RestController
public class ReturnGifController {

    /**
     * Сервис,  кором выполняется основная логика и маппинг
     */
    private final CheckRateService checkRateService;


    public ReturnGifController(@Autowired CheckRateService checkRateService) {
        this.checkRateService = checkRateService;
    }

    /**
     * Обрабатывает GET-запросы http с параметром проверяемой вылюты
     *
     * @param symbols В качестве параметра GET-запроса
     *                принимается трехзначный строковый код валюты
     * @return ResponseEntity, содержащий ответ в виде объекта CheckRatesResponse
     * @throws IllegalArgumentException при невалидном параметре symbols
     */
    @GetMapping(value = "/gif", params = {"symbols"})
    public ResponseEntity<?> getGif(@RequestParam String symbols) {
        CheckRatesResponse gifDTO = checkRateService.getGifOnRates(symbols);
        return gifDTO != null ? ResponseEntity.ok(gifDTO) :
                new ResponseEntity<>("Не удалось получить ответ от сервера", HttpStatus.BAD_REQUEST);
    }
}
