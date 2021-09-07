package ru.alfa.rateschecker.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/***
 * @author Артём Матюнин
 * Объект, вложенный в GifData
 * Имеет вложенный элемент Images
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gif {
    /**Id шифки*/
    private String id;

    /** URL страницы с гифкой*/
    private String url;

    /**Вложенный объект Images*/
    private Images images;
}
