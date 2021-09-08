package ru.alfa.rateschecker.apiclient;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.alfa.rateschecker.model.rates.Rates;

@FeignClient(name = "OpenExchangeRatesClient", url = "${openexchangerates.url}")
public interface OpenExchangeRatesClient {

    @GetMapping(value = "${openexchangerates.latestendpoint}",
            params = "{app_id, base, symbols}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Rates getLatestRateFromOpExRates(@RequestParam(value = "app_id") String appId,
                                     @RequestParam("base") String base,
                                     @RequestParam("symbols") String symbols);



    @GetMapping(value = "${openexchangerates.historycalendpoint}/{date}.json",
            params = "{app_id, base, symbols}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Rates getHistoricalRateFromOpExRate(@PathVariable String date,
                                        @RequestParam(value = "app_id") String appId,
                                        @RequestParam("base") String base,
                                        @RequestParam("symbols") String symbols);


}
