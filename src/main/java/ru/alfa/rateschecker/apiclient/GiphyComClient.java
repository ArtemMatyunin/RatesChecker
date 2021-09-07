package ru.alfa.rateschecker.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alfa.rateschecker.model.gif.GifData;


@FeignClient(name = "giphy-com-api", url = "${api.params.giphycom.url}")
public interface GiphyComClient {

    @GetMapping(value = "${api.params.giphycom.endpoint}", params = {"api_key, tag"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    GifData getGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
