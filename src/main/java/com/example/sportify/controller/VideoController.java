package com.example.sportify.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VideoController {
    @FXML
    private WebView videoWebView;

    public void initialize() {
        String videoUrl = "https://www.youtube.com/embed/zwV3h1vqU0A";

        WebEngine webEngine = videoWebView.getEngine();
        webEngine.load(videoUrl);
    }
}
