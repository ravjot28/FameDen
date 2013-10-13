/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.RegistrationBindingDTO;
import com.fameden.constants.GlobalConstants;
import com.fameden.dto.RegistrationDTO;
import com.fameden.exceptions.EmptyAlternateEmailAddressException;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidAlternateEmailAddressException;
import com.fameden.exceptions.InvalidConfirmPassword;
import com.fameden.exceptions.InvalidEmailAddressException;
import com.fameden.exceptions.InvalidFullNameException;
import com.fameden.exceptions.InvalidPasswordException;
import com.fameden.exceptions.PasswordDoNotMatchException;
import com.fameden.fxml.SceneNavigator;
import com.fameden.service.RegistrationService;
import com.fameden.util.InvokeAnimation;
import com.fameden.webservice.contracts.registration.FamedenRegistrationResponse;
import java.net.URL;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Ravjot
 */
public class RegistrationSceneController implements Initializable, IScreenController {

    static Logger logger = LoggerFactory.getLogger(RegistrationSceneController.class);
    @FXML
    ImageView bg;
    @FXML
    Button twitterSingUp;
    @FXML
    TextField fullNameTextField;
    @FXML
    TextField emailAddressTextField;
    @FXML
    TextField alternateEmailAddressTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField confirmPasswordTextField;
    RegistrationBindingDTO registrationBindingDTO;
    SceneNavigator myController;
    RegistrationService service;

    public RegistrationSceneController() {
        registrationBindingDTO = new RegistrationBindingDTO();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bg.requestFocus();
        Bindings.bindBidirectional(fullNameTextField.textProperty(), registrationBindingDTO.fullNameProperty());
        Bindings.bindBidirectional(emailAddressTextField.textProperty(), registrationBindingDTO.emailAddressProperty());
        Bindings.bindBidirectional(alternateEmailAddressTextField.textProperty(), registrationBindingDTO.alternateEmailAddressProperty());
        Bindings.bindBidirectional(passwordTextField.textProperty(), registrationBindingDTO.passwordProperty());
        Bindings.bindBidirectional(confirmPasswordTextField.textProperty(), registrationBindingDTO.confrimPasswordProperty());
    }

    @Override
    public void setScreenParent(SceneNavigator screenPage) {
        myController = screenPage;
    }

    @FXML
    public void singUp() {

        try {
            service = new RegistrationService();
            RegistrationDTO registrationDTO = new RegistrationDTO();
            logger.info("fullNameTextField " + registrationBindingDTO.getFullName());
            logger.info("emailAddressTextField " + registrationBindingDTO.getEmailAddress());
            logger.info("alternateEmailAddressTextField " + registrationBindingDTO.getAlternateEmailAddress());
            logger.info("passwordTextField " + registrationBindingDTO.getPassword());
            logger.info("confirmPasswordTextField " + registrationBindingDTO.getConfrimPassword());
            registrationDTO.setFullName(registrationBindingDTO.getFullName());
            registrationDTO.setConfirmPassword(registrationBindingDTO.getConfrimPassword());
            registrationDTO.setAlternateEmailAddress(registrationBindingDTO.getAlternateEmailAddress());
            registrationDTO.setEmailAddress(registrationBindingDTO.getEmailAddress());
            registrationDTO.setPassword(registrationBindingDTO.getPassword());
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
        } catch (InvalidConfirmPassword ex) {
            logger.error(ex.getMessage(), ex);
            InvokeAnimation.attentionSeekerWobble(confirmPasswordTextField);
        } catch (InvalidAlternateEmailAddressException ex) {
            logger.error(ex.getMessage(), ex);
            alternateEmailAddressTextField.setText(null);
            alternateEmailAddressTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
            InvokeAnimation.attentionSeekerWobble(alternateEmailAddressTextField);
        } catch (EmptyAlternateEmailAddressException ex) {
            logger.error(ex.getMessage(), ex);
            InvokeAnimation.attentionSeekerWobble(alternateEmailAddressTextField);
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
        } catch (InvalidFullNameException ex) {
            logger.error(ex.getMessage(), ex);
            InvokeAnimation.attentionSeekerWobble(fullNameTextField);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    @FXML
    public void login() {
        System.out.println("Login");
        myController.setScreen(GlobalConstants.loginScene);

    }

    @FXML
    public void twitterSignUp() {
        System.out.println("Twitter Sign Up");
        myController.setScreen(GlobalConstants.twitterIntegrationScene);
    }

    @FXML
    public void closeFired() {
        System.exit(0);
    }
}
