package ru.alfa.rateschecker.service;

import ru.alfa.rateschecker.model.rates.Rates;

/***
 * @author Артём
 * Сервис получения курсов валюты
 */
public interface OpExRatesGiver {
    /***
     * Получить текущий курс
     * @param symbols код валюты, курс которой интересует
     * @return объект, содержащий в себе запрошенный курс и другие параметры
     */
    Rates getLatestRates(String symbols);

    /***
     * Получить текущий курс за определенную дату
     * @param symbols код валюты, курс которой интересует
     * @param timestamp дата в виде timestamp.
     * @retur объект, содержащий в себе запрошенный курс и другие параметры
     */
    Rates getHistoricalRates(String symbols, long timestamp);
}
