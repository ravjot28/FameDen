/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.ForgotPasswordBindingDTO;
import com.fameden.bindingDTO.LoginBindingDTO;
import com.fameden.constants.GlobalConstants;
import com.fameden.dto.ForgotPasswordDTO;
import com.fameden.dto.LoginDTO;
import com.fameden.fxml.SceneNavigator;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author puneet
 */
public class LoginSceneController implements Initializable, IScreenController {

    @FXML
    private TextField famedenUserNameTextField, twitterUserNameTextField, passwordTextField, forgotUserNameTextField, forgotEmailTextField;
    @FXML
    private VBox forgotPasswordVBox;
    private LoginBindingDTO loginBindingDTO;
    private ForgotPasswordBindingDTO forgotPasswordBindingDTO;
    private LoginDTO loginDTO;
    private ForgotPasswordDTO forgotPasswordDTO;
    SceneNavigator myController;

    public LoginSceneController() {
        loginBindingDTO = new LoginBindingDTO();
        forgotPasswordBindingDTO = new ForgotPasswordBindingDTO();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bindings.bindBidirectional(famedenUserNameTextField.textProperty(), loginBindingDTO.emailProperty());
        Bindings.bindBidirectional(passwordTextField.textProperty(), loginBindingDTO.passwordProperty());

        Bindings.bindBidirectional(forgotUserNameTextField.textProperty(), forgotPasswordBindingDTO.userNameProperty());
        Bindings.bindBidirectional(forgotEmailTextField.textProperty(), forgotPasswordBindingDTO.emailProperty());
    }

    @FXML
    public void signUp() {
        myController.setScreen(GlobalConstants.registrationScene);

    }

    @FXML
    public void login() {
        if (!CommonValidations.isStringEmpty(loginBindingDTO.getEmailID())) {
            if (!CommonValidations.isStringEmpty(loginBindingDTO.getPassword())) {

                loginDTO = new LoginDTO();
                loginDTO.setEmailID(loginBindingDTO.getEmailID());
                loginDTO.setPassword(loginBindingDTO.getPassword());

                //TODO Call Login Service and Receive returning object

            } else {
                InvokeAnimation.attentionSeekerWobble(passwordTextField);
            }
        } else {
            InvokeAnimation.attentionSeekerWobble(famedenUserNameTextField);
        }
    }

    @FXML
    public void forgotPassword() {

        forgotPasswordVBox.setDisable(false);
        InvokeAnimation.appearByFading(forgotPasswordVBox);
        forgotPasswordVBox.setOpacity(1.0);

    }

    @Override
    public void setScreenParent(SceneNavigator screenPage) {
        myController = screenPage;
    }

    @FXML
    public void nevermind() {
        InvokeAnimation.disappearByFading(forgotPasswordVBox);
        forgotPasswordVBox.setDisable(true);
        forgotPasswordVBox.setOpacity(0.0);
    }

    @FXML
    public void sendVerificationEmail() {
        forgotPasswordDTO = new ForgotPasswordDTO();
        if (!CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getUserName()) || !CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getEmailID())) {
            if (!CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getUserName()) && !CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getEmailID())) {
                InvokeAnimation.attentionSeekerShake(forgotEmailTextField);
                InvokeAnimation.attentionSeekerShake(forgotUserNameTextField);
            } else if (!CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getEmailID())) {
                //TODO Logic to send Verification email.
//                forgotPasswordDTO = new ForgotPasswordDTO();
//                forgotPasswordDTO.setUserName(forgotPasswordBindingDTO.getUserName());
                forgotPasswordDTO.setEmailID(forgotPasswordBindingDTO.getEmailID());
            } else if (!CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getUserName())) {
                forgotPasswordDTO.setUserName(forgotPasswordBindingDTO.getUserName());
                //TODO Logic to send Verification email.
            }
        } else {
            InvokeAnimation.attentionSeekerShake(forgotEmailTextField);
            InvokeAnimation.attentionSeekerShake(forgotUserNameTextField);
        }

    }

    @FXML
    public void closeFired() {
        System.exit(0);
    }
}
