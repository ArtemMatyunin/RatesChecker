package ru.alfa.rateschecker.model.gif;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/***
 * @author Артём Матюнин
 * Корневой объект модели ответа GIPHY.COM
 * Содержит непосредственный URL гифки
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixedHeight {

    /**
     * URL конкретной гифки
     */
    private String url;

}
