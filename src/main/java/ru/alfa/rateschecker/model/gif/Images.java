package ru.alfa.rateschecker.model.gif;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/***
 * @author Артём Матюнин
 * Объект, вложенный в Images
 * Содержит сслыку на корневой объект FixedHeight ответа от GIPHY.COM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Images {

    @JsonProperty("fixed_height")
    private FixedHeight fixedHeight;
}
