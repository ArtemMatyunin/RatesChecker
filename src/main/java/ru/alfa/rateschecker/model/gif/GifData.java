package ru.alfa.rateschecker.model.gif;

import lombok.*;

/***
 * @author Артём Матюнин
 * Модель ответа от GIPHY.COM
 * Содержит ссылку на вложенныый объект GIF
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GifData {
    /**Ссылка на вложенный объект GIF*/
    Gif data;
}
