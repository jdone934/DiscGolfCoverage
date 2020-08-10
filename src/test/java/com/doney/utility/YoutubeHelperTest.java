package com.doney.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YoutubeHelperTest {
    YoutubeHelper helper;
    YoutubeHelper helperWithTimeStamp;
    YoutubeHelper helperFromURLString;

    @BeforeEach
    void setUp() throws MalformedURLException {
        URL url = new URL("https://www.youtube.com/watch?v=lyx2v6GeFLM");
        URL urlWithTimestamp = new URL("https://www.youtube.com/watch?v=lyx2v6GeFLM&t=248s");

        helper = new YoutubeHelper(url);
        helperWithTimeStamp = new YoutubeHelper(urlWithTimestamp);
        helperFromURLString = new YoutubeHelper("https://www.youtube.com/watch?v=lyx2v6GeFLM");
    }

    @Test
    void setVideoIdSuccess() {
        String videoId = "lyx2v6GeFLM";

        helper.setVideoId();
        helperWithTimeStamp.setVideoId();
        helperFromURLString.setVideoId();

        assertEquals(videoId, helper.getVideoId());
        assertEquals(videoId, helperWithTimeStamp.getVideoId());
        assertEquals(videoId, helperFromURLString.getVideoId());
    }

    @Test
    void getEmbedUrlSuccess() {
        String expectedUrl = "https://www.youtube.com/embed/" + helper.getVideoId() + "?autoplay=1";
        String actualUrl = helper.getEmbedUrl();

        assertEquals(expectedUrl, actualUrl);
    }
}
