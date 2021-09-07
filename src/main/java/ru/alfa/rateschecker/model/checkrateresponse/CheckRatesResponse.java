package ru.alfa.rateschecker.model.checkrateresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;

/***
 * @author Артём Матюнин
 * Итоговый объект, поставляемый наружу клиентам
 * Собирается из объектов ответов от openexchangerates.com и
 * GIPHY.COM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckRatesResponse {
    /** Ответ от GIPHY.COM*/
    private GifData gifData;
    /** Ответ от openexchangerates.com с текущим курсом */
    private Rates historicalRates;
    /** Ответ от Openexchangerates.com с историческим курсом */
    private Rates latestRates;
}
