package ru.alfa.rateschecker.utils;

import org.springframework.stereotype.Component;
import ru.alfa.rateschecker.model.checkrateresponse.CheckRatesResponse;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.rates.Rates;

/***
 * @author Артём Матюнин
 * Маппер итогового объекта, который будет возвращен клиенту
 */
@Component
public class CheckRatesResponseMapper {
    /***
     * Мапит ответы от OpenExchangeRatesClient и GiphyComClient в возвращаемый клиенту объект CheckRatesResponse
     * @param gifData модель гифки GifData
     * @param latestRates Текущий курс, объект класа Rates
     * @param historicalRates Сравниваемый исторический курс, объект класса Rates
     * @return объект CheckRatesResponse, который в итоге вернется клиенту
     * @throws IllegalArgumentException если один из параметров пустой
     */

    public CheckRatesResponse mapToCheckRatesResponse(GifData gifData, Rates historicalRates, Rates latestRates) {
        if(null==gifData || null == historicalRates || null == latestRates){
            throw new IllegalArgumentException("Input parameters gifData or historicalRates or latestRates ");
        }
        return new CheckRatesResponse(gifData, historicalRates, latestRates);
    }
}
