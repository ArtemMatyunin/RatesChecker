package ru.alfa.rateschecker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfa.rateschecker.apiclient.OpenExchangeRatesClient;

import ru.alfa.rateschecker.model.rates.Rates;
import ru.alfa.rateschecker.service.OpExRatesGiver;
import ru.alfa.rateschecker.utils.OpExPropsProvider;

/**
 * @author Артём Матюнин
 * Реализация сервиса получение курсов валют.
 */
@Service
public class OpExRatesGiverImpl implements OpExRatesGiver {

    /**
     * Feign-клиент для взаимодействия с openexchangerates.com
     */
    private final OpenExchangeRatesClient ratesClient;
    /**
     * Поставщик настроек для взаимодействия с openexchangerates.com
     */
    private final OpExPropsProvider propsProvider;

    @Autowired
    public OpExRatesGiverImpl(OpenExchangeRatesClient ratesClient, OpExPropsProvider propsProvider) {
        this.ratesClient = ratesClient;
        this.propsProvider = propsProvider;
    }

    /***
     * Получает текущий курс валюты
     * @param symbols Валюта, курс которой проверяем
     * @return объет Rates, содержащий курс и другие данные о валюте
     */
    @Override
    public Rates getLatestRates(String symbols) {
        checkInputParameter(symbols);
        Rates latestRates = ratesClient
                .getLatestRateFromOpExRates(propsProvider.getAppId(), propsProvider.getBase(), symbols);
        checkClientResponse(latestRates, symbols);
        return latestRates;
    }

    /***
     * Получает исторический курс валюты за определенную дату. Дата рассчитывается в DateManager
     * @param symbols валюта, курс которой проверяем
     * @param timestamp Дата, от которой будет отсчитываться требуемая дата курса.
     * @return объет Rates, содержащий курс и другие данные о валюте
     */
    @Override
    public Rates getHistoricalRates(String symbols, long timestamp) {
        checkInputParameter(symbols);
        Rates historyRates = ratesClient
                .getHistoricalRateFromOpExRate(propsProvider.getHistoricalDate(timestamp),
                        propsProvider.getAppId(),
                        propsProvider.getBase(),
                        symbols);

        checkClientResponse(historyRates, symbols);
        return historyRates;
    }

    /***
     * Проверяет, является ли входящий параметр пустым, NULL или чилсло его символов не равно 3
     * @param parameter строковый параметр
     * @throws IllegalArgumentException Если хотя бы одно условие выполняется, то выбрасывает исключение
     */
    public void checkInputParameter(String parameter) {
        if (parameter == null || "".equals(parameter) || 3 != parameter.length()) {
            throw new IllegalArgumentException("Incorrect input parameter symbols: " + parameter);
        }
    }

    /***
     * Проверяет ответ от ratesClient
     * @param rates ответ
     * @param symbols код валюты, по которой происходит запрос
     * @throws IllegalStateException если ответ от клиента NULL или его поля не заполнены
     *
     */
    private void checkClientResponse(Rates rates, String symbols) {
        if (null == rates || null == rates.getTimestamp()
                || null == rates.getBase() || null == rates.getRates().get(symbols)) {
            throw new IllegalStateException("Response from ratesClient is incorrect: " + rates);
        }
    }

}
