package ru.alfa.rateschecker.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;
import ru.alfa.rateschecker.service.CheckRateService;
import ru.alfa.rateschecker.service.GetGifOnConditionGiver;
import ru.alfa.rateschecker.service.OpExRatesGiver;
import ru.alfa.rateschecker.utils.CheckRatesResponseMapper;


/**
 * @author Artem Matyunin
 * Релизует интерфейс CheckRatesService
 * Получает зависимости-поставщки данных и выполняет основную логику работы
 */

@Service
public class CheckRateServiceImpl implements CheckRateService {

    /**
     * Поставщик гифок
     */
    private final GetGifOnConditionGiver getGifOnConditionGiver;
    /**
     * Поставщик курсов
     */
    private final OpExRatesGiver ratesGiver;
    /**
     * Маппер объекта-ответа клиенту
     */
    private final CheckRatesResponseMapper checkRatesResponseMapper;

    @Autowired
    public CheckRateServiceImpl(GetGifOnConditionGiver getGifOnConditionGiver, OpExRatesGiver ratesGiver,
                                CheckRatesResponseMapper checkRatesResponseMapper) {

        this.getGifOnConditionGiver = getGifOnConditionGiver;
        this.ratesGiver = ratesGiver;
        this.checkRatesResponseMapper = checkRatesResponseMapper;
    }

    /***
     * Получает текущую дату из timestamp ответа от API текущего курса.
     * Запрашивает исторический курс исходя из этой даты. Дата исторического курса
     * предоставляется на уровне ratesGiver(-1 день от даты текущего курса)
     * Запрашивает гифку, исходя из результата сравнения текущего и исторического курсов.
     * Если меньше или равен, возвращает гифку по теку lower tag/
     * @param symbols строковое представление кода валюты
     * @return объект CheckRateResponse, полученный из ответов
     * сервисов-поставщиков ответов GIPHY.COM и openexchangerates.com
     */

    @Override
    public CheckRatesResponse getGifOnRates(String symbols) {

        Rates latestRates = ratesGiver.getLatestRates(symbols);
        Rates historicalRates = ratesGiver.getHistoricalRates(symbols, latestRates.getTimestamp());

        boolean compareResult = latestRates.getRates().get(symbols) > (historicalRates.getRates().get(symbols));

        GifData gifData = getGifOnConditionGiver.getGifOnCondition(compareResult);
        return checkRatesResponseMapper.mapToCheckRatesResponse(gifData, historicalRates, latestRates);
    }

}
