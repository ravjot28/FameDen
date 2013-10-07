/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.RegistrationBindingDTO;
import com.fameden.constants.GlobalConstants;
import com.fameden.dto.RegistrationDTO;
import com.fameden.fxml.SceneNavigator;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import com.fameden.util.RSAEncryption;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ravjot
 */
public class RegistrationSceneController implements Initializable, IScreenController {

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
        System.out.println("Sign Up");

        if (!CommonValidations.isStringEmpty(registrationBindingDTO.getFullName())) {
            if (CommonValidations.isValidEmailAddress(registrationBindingDTO.getEmailAddress())) {
                if (CommonValidations.isValidEmailAddress(registrationBindingDTO.getAlternateEmailAddress())) {
                    if (!CommonValidations.isStringEmpty(registrationBindingDTO.getPassword())) {
                        if (!CommonValidations.isStringEmpty(registrationBindingDTO.getConfrimPassword())) {
                            if (registrationBindingDTO.getPassword().equals(registrationBindingDTO.getConfrimPassword())) {
                                System.out.println("fullNameTextField " + registrationBindingDTO.getFullName());
                                System.out.println("emailAddressTextField " + registrationBindingDTO.getEmailAddress());
                                System.out.println("alternateEmailAddressTextField " + registrationBindingDTO.getAlternateEmailAddress());
                                System.out.println("passwordTextField " + registrationBindingDTO.getPassword());
                                System.out.println("confirmPasswordTextField " + registrationBindingDTO.getConfrimPassword());
                                RegistrationDTO registrationDTO = new RegistrationDTO();
                                registrationDTO.setFullName(registrationBindingDTO.getFullName());
                                registrationDTO.setAlternateEmailAddress(registrationBindingDTO.getAlternateEmailAddress());
                                registrationDTO.setEmailAddress(registrationBindingDTO.getEmailAddress());
                                registrationDTO.setPassword(RSAEncryption.encrypt(registrationBindingDTO.getPassword()));
                            } else {
                                confirmPasswordTextField.setText(null);
                                passwordTextField.setText(null);
                                confirmPasswordTextField.setPromptText(GlobalConstants.passwordDoNotMatchMessage);
                                passwordTextField.setPromptText(GlobalConstants.passwordDoNotMatchMessage);
                                InvokeAnimation.attentionSeekerShake(confirmPasswordTextField);
                                InvokeAnimation.attentionSeekerShake(passwordTextField);
                            }
                        } else {
                            InvokeAnimation.attentionSeekerWobble(confirmPasswordTextField);
                        }
                    } else {
                        InvokeAnimation.attentionSeekerWobble(passwordTextField);
                    }
                } else {
                    if (!CommonValidations.isStringEmpty(registrationBindingDTO.getAlternateEmailAddress())) {
                    alternateEmailAddressTextField.setText(null);
                    alternateEmailAddressTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
                }
                    InvokeAnimation.attentionSeekerWobble(alternateEmailAddressTextField);
                }
            } else {
                if (!CommonValidations.isStringEmpty(registrationBindingDTO.getEmailAddress())) {
                    emailAddressTextField.setText(null);
                    emailAddressTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
                }
                InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
            }
        } else {
            InvokeAnimation.attentionSeekerWobble(fullNameTextField);
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
