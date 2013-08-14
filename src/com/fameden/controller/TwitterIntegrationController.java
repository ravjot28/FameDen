/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.TwitterIntegrationBindingDTO;
import com.fameden.constants.GlobalConstants;
import com.fameden.dto.RegistrationDTO;
import com.fameden.dto.TwitterRegistrationDTO;
import com.fameden.fxml.SceneNavigator;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * FXML Controller class
 *
 * @author Ravjot
 */
public class TwitterIntegrationController implements Initializable, IScreenController {

    SceneNavigator myController;
    TwitterIntegrationBindingDTO twitterIntegrationBindingDTO;
    Twitter twitter;
    RequestToken requestToken;
    @FXML
    WebView webView;
    @FXML
    TextField pinTextField;
    @FXML
    TextField emailAddressTextField;
    @FXML
    ImageView bg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bg.requestFocus();
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                try {
                    init();
                    displayWebView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        };
        new Thread(task).start();
        webView.setVisible(false);
        webView.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Node> change) {
                Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
                for (Node scroll : deadSeaScrolls) {
                    scroll.setVisible(false);
                }
            }
        });
        twitterIntegrationBindingDTO = new TwitterIntegrationBindingDTO();
        Bindings.bindBidirectional(pinTextField.textProperty(), twitterIntegrationBindingDTO.twitterPinProperty());
        Bindings.bindBidirectional(emailAddressTextField.textProperty(), twitterIntegrationBindingDTO.emailAddressProperty());


    }

    @Override
    public void setScreenParent(SceneNavigator screenPage) {
        myController = screenPage;
    }

    @FXML
    public void pinProcess() throws TwitterException {
        if (CommonValidations.isValidEmailAddress(twitterIntegrationBindingDTO.getEmailAddress())) {
            if (!CommonValidations.isStringEmpty(twitterIntegrationBindingDTO.getTwitterPin())) {
                if (requestToken != null && twitter != null && pinTextField.getText() != null) {
                    AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pinTextField.getText());
                    twitter.setOAuthAccessToken(accessToken);
                    String token = accessToken.getToken();
                    String secretToken = accessToken.getTokenSecret();
                    long userId = twitter.verifyCredentials().getId();
                    TwitterRegistrationDTO twitterRegistrationDTO = new TwitterRegistrationDTO();
                    twitterRegistrationDTO.setToken(token);
                    twitterRegistrationDTO.setTokenSecret(secretToken);
                    twitterRegistrationDTO.setUserId(userId);

                    RegistrationDTO registrationDTO = new RegistrationDTO();
                    registrationDTO.setTwitterRegistrationDTO(twitterRegistrationDTO);
                    registrationDTO.setEmailAddress(twitterIntegrationBindingDTO.getEmailAddress());
                }
            } else {
                InvokeAnimation.attentionSeekerWobble(pinTextField);
            }
        } else {
            if (!CommonValidations.isStringEmpty(twitterIntegrationBindingDTO.getEmailAddress())) {
                emailAddressTextField.setText(null);
                emailAddressTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
            }
            InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
        }
    }

    @FXML
    public void goBack() throws TwitterException {
        myController.setScreen(GlobalConstants.registrationScene);
    }

    public void init() throws Exception {

        try {
            twitter = TwitterFactory.getSingleton();
            twitter.setOAuthConsumer(GlobalConstants.twitterAppKey, GlobalConstants.twitterSecretAppKey);
            requestToken = twitter.getOAuthRequestToken();
            System.out.println(requestToken.getAuthorizationURL());

        } catch (Exception e) {
            throw e;
        }
    }

    public void displayWebView() {
        Platform.runLater(new Runnable() {
            public void run() {
                webView.setVisible(true);
                webView.getEngine().load(requestToken.getAuthorizationURL());
            }
        });

    }
}
