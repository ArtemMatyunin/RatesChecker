package ru.alfa.rateschecker.service;

import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;

/***
 * @author Артём Матюнин
 * Сервис получения гифок
 */
public interface GetGifOnConditionGiver {
    /***
     * Получает гифку по условию. Условие определяет вызывающий клиент.
     * @param compareResult условие
     * @return объект, содержащий ссылку н GIF
     */
    GifData getGifOnCondition(Boolean compareResult);
}
