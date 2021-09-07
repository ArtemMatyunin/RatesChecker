package ru.alfa.rateschecker.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

/***
 * @author Артём Матюнин
 * Класс-поставщик параметров api openexchangerates.com из файла настроек
 */
@Setter
@Getter
@Component
public class OpExPropsProvider {


    /**
     * app_id приложения в api openexchangerates.com
     */
    private String appId;

    /**
     * Базовая валюта, по которой происходит расчет курса
     */
    private String base;

    /**
     * За сколько дней назад нужен курс.
     * Данные берутся из application.properties
     */
    private int daysAgo;

    public OpExPropsProvider(@Value("${api.params.openexchangerates.app_id}") String appId,
                             @Value("${api.params.openexchangerates.base}") String base,
                             @Value("${api.params.openexchangerates.daysago}") int daysAgo) {
        this.appId = appId;
        this.base = base;
        this.daysAgo = daysAgo;
    }

    /**
     * Получает timestamp, извлекает из него дату по Гринвичу.
     * Вычитает из нее количество дней из поля daysAgo
     * Преобразует полученное значение в строку
     *
     * @return строковое представление даты, за которую нужен курс
     */
    public String getHistoricalDate(long timestamp) {
        if(timestamp<86400L){
            throw new IllegalArgumentException("Incorrect input parameter timestamp:" + timestamp);
        }
        Instant instant = Instant.ofEpochSecond(timestamp);
        LocalDate currentDate = instant.atZone(ZoneOffset.UTC).toLocalDate();
        return String.valueOf(currentDate.minusDays(daysAgo));
    }

}
