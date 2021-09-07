package ru.alfa.rateschecker.model.rates;

import lombok.*;

import java.util.Map;

/***
 * @author Артём Матюнин
 * Модель ответа от openexchangerates.com
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Rates {
    /**
     * URL лицензии
     */
    private String license;
    /**
     * Временная метка момента получения курса
     */
    private Long timestamp;
    /**
     * Код базовой валюты, курс по которой предоставляется
     */
    private String base;
    /**
     * Список кодов и курсов валют по базовой валюте
     */
    private Map<String, Float> rates;

}
