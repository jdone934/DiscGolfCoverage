package com.doney.utility;

import java.net.MalformedURLException;
import java.net.URL;

public class YoutubeHelper {
    private URL url;
    private String videoId = null;

    public YoutubeHelper(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            this.url = null;
        }
    }

    public YoutubeHelper(URL url) {
        this.url = url;
    }

    public String getVideoId() {
        if (videoId != null) {
            return videoId;
        } else {
            setVideoId();
            return videoId;
        }
    }

    public void setVideoId() {
        String urlQuery = url.getQuery();
        int amperstandLocation = urlQuery.indexOf("&");

        if (amperstandLocation > 0) {
            videoId = urlQuery.substring(2, amperstandLocation);
        } else {
            videoId = urlQuery.substring(2);
        }
    }

    public String getEmbedUrl() {
        setVideoId();

        return "https://www.youtube.com/embed/" + videoId + "?autoplay=1";
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
