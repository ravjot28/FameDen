/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.TwitterIntegrationBindingDTO;
import com.fameden.constants.GlobalConstants;
import static com.fameden.controller.RegistrationSceneController.logger;
import com.fameden.dto.RegistrationDTO;
import com.fameden.dto.TwitterRegistrationDTO;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidEmailAddressException;
import com.fameden.exceptions.InvalidPasswordException;
import com.fameden.exceptions.PasswordDoNotMatchException;
import com.fameden.fxml.SceneNavigator;
import com.fameden.service.TwitterRegistrationService;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import com.fameden.webservice.contracts.registration.FamedenRegistrationResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    static Logger logger = LoggerFactory.getLogger(TwitterIntegrationController.class);
    SceneNavigator myController;
    TwitterRegistrationService service;
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
    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField confirmPasswordTextField;
    @FXML
    ImageView pleaseWait;
    @FXML
    ImageView noInternetConnection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bg.requestFocus();
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(GlobalConstants.twitterAppKey, GlobalConstants.twitterSecretAppKey);

        loadTwitter();
        /*webView.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
         @Override
         public void onChanged(ListChangeListener.Change<? extends Node> change) {
         Set<Node> deadSeaScrolls = webView.lookupAll(".scroll-bar");
         for (Node scroll : deadSeaScrolls) {
         scroll.setVisible(false);
         }
         }
         });*/
        twitterIntegrationBindingDTO = new TwitterIntegrationBindingDTO();
        Bindings.bindBidirectional(pinTextField.textProperty(), twitterIntegrationBindingDTO.twitterPinProperty());
        Bindings.bindBidirectional(emailAddressTextField.textProperty(), twitterIntegrationBindingDTO.emailAddressProperty());
        Bindings.bindBidirectional(passwordTextField.textProperty(), twitterIntegrationBindingDTO.passwordProperty());
        Bindings.bindBidirectional(confirmPasswordTextField.textProperty(), twitterIntegrationBindingDTO.confrimPasswordProperty());

    }

    public void loadTwitter() {
        InvokeAnimation.disappearByFading(noInternetConnection);
        noInternetConnection.setVisible(false);
        pleaseWait.setVisible(true);
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                try {
                    init();
                    displayWebView();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    InvokeAnimation.appearByFading(pleaseWait);
                    pleaseWait.setVisible(false);
                    InvokeAnimation.appearByFading(noInternetConnection);
                    noInternetConnection.setVisible(true);
                }
                return null;
            }
        };
        new Thread(task).start();
        webView.setVisible(false);
    }

    @Override
    public void setScreenParent(SceneNavigator screenPage) {
        myController = screenPage;
    }

    @FXML
    public void pinProcess() throws TwitterException {
        if (!CommonValidations.isStringEmpty(twitterIntegrationBindingDTO.getTwitterPin())) {
            if (requestToken != null && twitter != null && pinTextField.getText() != null) {
                System.out.println(pinTextField.getText());
                AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pinTextField.getText());
                twitter.setOAuthAccessToken(accessToken);
                String token = accessToken.getToken();
                String secretToken = accessToken.getTokenSecret();
                System.out.println(token + "  " + secretToken);
                TwitterRegistrationDTO twitterRegistrationDTO = new TwitterRegistrationDTO();
                twitterRegistrationDTO.setToken(token);
                twitterRegistrationDTO.setTokenSecret(secretToken);

                RegistrationDTO registrationDTO = new RegistrationDTO();
                registrationDTO.setTwitterRegistrationDTO(twitterRegistrationDTO);
                registrationDTO.setEmailAddress(twitterIntegrationBindingDTO.getEmailAddress());
                registrationDTO.setConfirmPassword(twitterIntegrationBindingDTO.getConfrimPassword());
                registrationDTO.setPassword(twitterIntegrationBindingDTO.getPassword());

                try {
                    service = new TwitterRegistrationService();
                    FamedenRegistrationResponse response = (FamedenRegistrationResponse) service.processRequest(registrationDTO);
                    if (response == null) {

                        logger.info("Error");
                    } else if (response.getStatus().equals(GlobalConstants.SUCCESS)) {
                        myController.setScreen(GlobalConstants.loginScene);
                    } else {
                        logger.info(response.getErrorMessage());
                        if (response.getErrorMessage().equals(GlobalConstants.userAlreadyExisitsMessage)) {
                            emailAddressTextField.setText(null);
                            emailAddressTextField.setPromptText(GlobalConstants.userAlreadyExisitsMessage);
                            InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
                        }
                    }
                } catch (PasswordDoNotMatchException ex) {
                    logger.error(ex.getMessage(), ex);
                    confirmPasswordTextField.setText(null);
                    passwordTextField.setText(null);
                    confirmPasswordTextField.setPromptText(GlobalConstants.passwordDoNotMatchMessage);
                    passwordTextField.setPromptText(GlobalConstants.passwordDoNotMatchMessage);
                    InvokeAnimation.attentionSeekerShake(confirmPasswordTextField);
                    InvokeAnimation.attentionSeekerShake(passwordTextField);
                } catch (InvalidPasswordException ex) {
                    logger.error(ex.getMessage(), ex);
                    InvokeAnimation.attentionSeekerWobble(passwordTextField);
                } catch (InvalidEmailAddressException ex) {
                    logger.error(ex.getMessage(), ex);
                    emailAddressTextField.setText(null);
                    emailAddressTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
                    InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
                } catch (EmptyEmailAddressException ex) {
                    logger.error(ex.getMessage(), ex);
                    InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
                } catch (Exception ex) {
                    logger.error(ex.getMessage(), ex);
                }
            }
        } else {
            InvokeAnimation.attentionSeekerWobble(pinTextField);
        }

    }

    @FXML
    public void goBack() throws TwitterException {
        myController.setScreen(GlobalConstants.registrationScene);
    }

    public void init() throws Exception {

        try {
            requestToken = twitter.getOAuthRequestToken();
            System.out.println(requestToken.getAuthorizationURL());

        } catch (Exception e) {
            throw e;
        }
    }

    public void displayWebView() {
        Platform.runLater(new Runnable() {
            public void run() {

                InvokeAnimation.disappearByFading(pleaseWait);

                pleaseWait.setVisible(false);
                webView.getEngine().load(requestToken.getAuthorizationURL());
                webView.setVisible(true);
                InvokeAnimation.appearByFading(webView);
            }
        });
    }

    @FXML
    public void reloadTwitterSignUp() {
        if (twitter != null && requestToken != null) {
            if (requestToken.getAuthorizationURL() != null) {
                webView.getEngine().load(requestToken.getAuthorizationURL());
            } else {
                twitter = null;
                requestToken = null;
                loadTwitter();
            }
        } else {
            loadTwitter();
        }
    }
}
