package ru.alfa.rateschecker.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alfa.rateschecker.apiclient.GiphyComClient;
import ru.alfa.rateschecker.model.gif.FixedHeight;
import ru.alfa.rateschecker.model.gif.Gif;
import ru.alfa.rateschecker.model.gif.GifData;
import ru.alfa.rateschecker.model.gif.Images;
import ru.alfa.rateschecker.service.GetGifOnConditionGiver;
import ru.alfa.rateschecker.utils.GiphyComPropertiesProvider;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetGifOnConditionGiverImplTest {

    @Autowired
    private GiphyComPropertiesProvider propertiesProvider;

    @Autowired
    private GetGifOnConditionGiver gifOnCondition;

    @MockBean
    private GiphyComClient giphyComClient;


    @Test
    @DisplayName("Testing getGifOnCondition with Exception")
    void getGifOnConditionWithException() {
        Boolean testCondition = null;
        assertThrows(IllegalArgumentException.class, ()-> gifOnCondition.getGifOnCondition(testCondition));
    }

    @Test
    @DisplayName("Testing getGifOnCondition With True")
    void getGifOnConditionWithTrue() {

        FixedHeight fixedHeight = new FixedHeight();
        fixedHeight.setUrl("True tag url");
        Images images = new Images(fixedHeight);
        Gif gif = new Gif();
        gif.setImages(images);
        GifData gifData = new GifData();
        gifData.setData(gif);
        Mockito.when(giphyComClient.getGif(propertiesProvider.getApiKey(), propertiesProvider.getTrueTag())).thenReturn(gifData);
        assertEquals("True tag url", gifOnCondition.getGifOnCondition(true).getData().getImages().getFixedHeight().getUrl());
    }

    @Test
    @DisplayName("Testing getGifOnCondition With False")
    void getGifOnConditionWithFalse() {

        FixedHeight fixedHeight = new FixedHeight();
        fixedHeight.setUrl("False tag url");
        Images images = new Images(fixedHeight);
        Gif gif = new Gif();
        gif.setImages(images);
        GifData gifData = new GifData();
        gifData.setData(gif);
        Mockito.when(giphyComClient.getGif(propertiesProvider.getApiKey(), propertiesProvider.getFalseTag())).thenReturn(gifData);
        assertEquals("False tag url", gifOnCondition.getGifOnCondition(false).getData().getImages().getFixedHeight().getUrl());
        assertNotEquals("True tag url", gifOnCondition.getGifOnCondition(false).getData().getImages().getFixedHeight().getUrl());
    }


}