package ru.alfa.rateschecker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfa.rateschecker.apiclient.GiphyComClient;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.service.GetGifOnConditionGiver;
import ru.alfa.rateschecker.utils.GiphyComPropertiesProvider;

/**
 * @author Артём Матюнин
 * Реализация
 */
@Service
public class GetGetGifOnConditionGiverImpl implements GetGifOnConditionGiver {

    /**
     * Поставщик параметров для взаимодействия с GIPHY.COM
     */
    private final GiphyComPropertiesProvider propertiesProvider;
    /**
     * Feign-клиент, взаимодействующий с GIPHY.COM
     */
    private final GiphyComClient giphyComClient;

    @Autowired
    public GetGetGifOnConditionGiverImpl(GiphyComPropertiesProvider propertiesProvider, GiphyComClient giphyComClient) {
        this.propertiesProvider = propertiesProvider;
        this.giphyComClient = giphyComClient;
    }


    /***
     * Получает гифку, исходя из того, выполняется ли условие. Условие определяет взывающий клинент.
     * В зависимости от выполнения или не выполнения условия редоставляет гифку по тегам,
     * определенным в настройках приложения.
     * trueTag - условие выполняется
     * falseTag - условие не выполняется
     * @throws IllegalArgumentException если conditionResult null.
     * @throws IllegalStateException если ответ в от GiphyComClient пришел null.
     * @return модель гифки GifData
     */
    @Override
    public GifData getGifOnCondition(Boolean conditionResult) {
        if (conditionResult == null) {
            throw new IllegalArgumentException("Input parameter compareResult is null");
        }
        GifData gifData;
        if (conditionResult) {
            gifData = giphyComClient.getGif(propertiesProvider.getApiKey(), propertiesProvider.getTrueTag());
        } else {
            gifData = giphyComClient.getGif(propertiesProvider.getApiKey(), propertiesProvider.getFalseTag());
        }

        if (gifData == null) {
            throw new IllegalStateException("Response from giphyComClient is NULL");
        }
        return gifData;
    }
}
