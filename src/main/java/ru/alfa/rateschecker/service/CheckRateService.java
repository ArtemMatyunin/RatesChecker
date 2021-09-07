package ru.alfa.rateschecker.service;

import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;


/***
 * @author Артём Матюнин
 * Сервис проверки курсов валюты
 *
 */

public interface CheckRateService {
    /***
     * Возвращает результат в виде объекта, содержащего гифку
     * после сравнения symbol с базовой валютой(опрелена в настройках)
     * @param symbols код валюты
     * @return резульат проверки в виде CheckResponse-объекта
     */
    CheckRatesResponse getGifOnRates(String symbols);
}
