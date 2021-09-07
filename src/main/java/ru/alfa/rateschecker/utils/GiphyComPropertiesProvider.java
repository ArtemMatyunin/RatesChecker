package ru.alfa.rateschecker.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * @author Артём Матюнин
 * Класс-поставщик параметров api GIPHY.COM из файла настроек
 */
@Getter
@Setter
@Component
public class GiphyComPropertiesProvider {

    /**
     * api_key приложения в api GIPHY.COM
     */
    private String apiKey;
    /**
     * Тег для роста курса
     */
    private String trueTag;
    /**
     * Тег для отсутствия роста и снижения
     */
    private String falseTag;


    public GiphyComPropertiesProvider(@Value("${api.params.giphycom.api_key}") String apiKey,
                                      @Value("${api.params.giphycom.truetag}") String trueTag,
                                      @Value("${api.params.giphycom.falsetag}") String falseTag) {
        this.apiKey = apiKey;
        this.trueTag = trueTag;
        this.falseTag = falseTag;
    }
}
