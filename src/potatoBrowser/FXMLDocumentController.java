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
import java.util.Vector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
    private Button buttonBack;
    @FXML
    private Button buttonFront;
    
    private static Vector<String> vec = new Vector();
    private int track;
    private int flag;
    private boolean isClicked = false;
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException, IOException {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        String str = textFieldUrl.getText();
        if (isClicked == true || vec.lastElement().equals(str)) {
           
        }else vec.addElement(str);
        
        web.load(textFieldUrl.getText().startsWith("http://") || textFieldUrl.getText().startsWith("https://") ? textFieldUrl.getText() : "http://" + textFieldUrl.getText());
        System.out.println(vec.size());
        for (int i = 0; i < vec.size(); i++) {
            System.out.println(vec.elementAt(i));
        }

        if(vec.size()>1){
           buttonBack.setDisable(false);
       }
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        flag = 0;
        isClicked = false;
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
            
       buttonFront.setDisable(true);
       buttonBack.setDisable(true);
       pi.progressProperty().bind(web.getLoadWorker().progressProperty());
       web.load("https://www.google.com");
       vec.addElement("https://www.google.com");
    }

    @FXML
    private void redditButtonAction(ActionEvent event) throws IOException {
        textFieldUrl.setText("http://www.reddit.com");
        buttonGo.fire();
    }

    @FXML
    private void googleButtonAction(ActionEvent event) throws IOException {
        textFieldUrl.setText("http://www.google.com");
        buttonGo.fire();
    }

    @FXML
    private void gmailButtonAction(ActionEvent event) throws IOException {
        textFieldUrl.setText("http://www.gmail.com");
        buttonGo.fire();
    }

    @FXML
    private void ymailButtonAction(ActionEvent event) throws IOException {
        textFieldUrl.setText("http://www.ymail.com");
        buttonGo.fire();
    }

    @FXML
    private void twitterButtonAction(ActionEvent event) {
        textFieldUrl.setText("http://www.twitter.com");
        buttonGo.fire();
    }

    @FXML
    private void gagButtonAction(ActionEvent event) {
        textFieldUrl.setText("http://www.9gag.com");
        buttonGo.fire();
    }

    @FXML
    private void tubeButtonAction(ActionEvent event) {
        textFieldUrl.setText("http://www.youtube.com");
        buttonGo.fire();
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        isClicked = true;
        buttonFront.setDisable(false);
        flag++;
        textFieldUrl.setText(vec.elementAt(vec.size()-flag-1));
        buttonGo.fire();
        track=flag;
        if(vec.elementAt(vec.size()-flag-1)== vec.firstElement()){
            buttonBack.setDisable(true);
        }
    }

    @FXML
    private void frontButtonAction(ActionEvent event) {
        pi.progressProperty().bind(web.getLoadWorker().progressProperty());
        textFieldUrl.setText(vec.elementAt(vec.size()-flag));
        if(vec.elementAt(vec.size()-flag)== vec.lastElement()){
            buttonFront.setDisable(true);
        }
        track++;
    } 
}