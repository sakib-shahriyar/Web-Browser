/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoBrowser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author intern
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField textFieldUrl;
    @FXML
    private Button buttonGo;
    @FXML
    private WebView webView;
    private WebEngine web;
    @FXML
    private Button btnReddit;
    @FXML
    private Button btnGoogle;
    @FXML
    private Button btnGmail;
    @FXML
    private Button btnYmail;
    @FXML
    private ProgressIndicator pi;
    @FXML
    private Button btnTwitter;
    @FXML
    private Button btnGag;
    @FXML
    private Button btnYoutube;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load(textFieldUrl.getText().startsWith("http://") || textFieldUrl.getText().startsWith("https://") ? textFieldUrl.getText() : "http://" + textFieldUrl.getText());
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        web = webView.getEngine();
        web.setJavaScriptEnabled(true);
        web.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textFieldUrl.setText(newValue);
            }
        });
        
        
            web.getLoadWorker().stateProperty().addListener(
            new ChangeListener<Worker.State>() {
            @Override
            public void changed (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if(newValue == Worker.State.SUCCEEDED){
                   // System.out.println("Location loaded + " + web.getLocation());
                }
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
       pi.progressProperty().bind(web.getLoadWorker().progressProperty());
       web.load("https://www.google.com");
    }

    @FXML
    private void redditButtonAction(ActionEvent event) throws IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.reddit.com");
    }

    @FXML
    private void googleButtonAction(ActionEvent event) throws IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.google.com");
    }

    @FXML
    private void gmailButtonAction(ActionEvent event) throws IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.gmail.com");
    }

    @FXML
    private void ymailButtonAction(ActionEvent event) throws IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.ymail.com");
    }

    @FXML
    private void twitterButtonAction(ActionEvent event) {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.twitter.com");
    
    }

    @FXML
    private void gagButtonAction(ActionEvent event) {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.9gag.com");
    }

    @FXML
    private void tubeButtonAction(ActionEvent event) {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        web.load("http://www.youtube.com");

    }

}