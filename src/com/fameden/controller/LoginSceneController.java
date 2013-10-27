/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.ForgotPasswordBindingDTO;
import com.fameden.bindingDTO.LoginBindingDTO;
import com.fameden.constants.GlobalConstants;
import static com.fameden.controller.RegistrationSceneController.logger;
import com.fameden.dto.ForgotPasswordDTO;
import com.fameden.dto.LoginDTO;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidEmailAddressException;
import com.fameden.exceptions.InvalidPasswordException;
import com.fameden.fxml.SceneNavigator;
import com.fameden.service.LoginService;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import com.fameden.webservice.contracts.login.FamedenLoginResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author puneet
 */
public class LoginSceneController implements Initializable, IScreenController {
static Logger logger = LoggerFactory.getLogger(LoginSceneController.class);
    @FXML
    private TextField famedenUserNameTextField, passwordTextField;
    @FXML
    private TextField forgotUserNameTextField, forgotEmailTextField;
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
        try {
         
        LoginService service = new LoginService();
        loginDTO = new LoginDTO();
        logger.info("emailAddressTextField " + loginBindingDTO.getEmailID());
        logger.info("passwordTextField " + loginBindingDTO.getPassword());
        loginDTO.setEmailID(loginBindingDTO.getEmailID());
        loginDTO.setPassword(loginBindingDTO.getPassword());
        loginDTO.setLoginMode("FAMEDEN");

        FamedenLoginResponse response = (FamedenLoginResponse) service.processRequest(loginDTO);

        if (response == null) {
            logger.info("Error");
        } else if (response.getStatus().equals(GlobalConstants.SUCCESS)) {
            logger.info("Sccess");//TODO: Show next screen
        } else {
            logger.info("Error message " + response.getErrorMessage());
        }
    } catch (EmptyEmailAddressException ex) {
             logger.error(ex.getMessage(), ex);
            InvokeAnimation.attentionSeekerWobble(famedenUserNameTextField);
        } catch(InvalidEmailAddressException ex) {
            logger.error(ex.getMessage(), ex);
            famedenUserNameTextField.setText(null);
            famedenUserNameTextField.setPromptText(GlobalConstants.invalidEmailIDMessage);
            InvokeAnimation.attentionSeekerWobble(famedenUserNameTextField);
        }  catch (InvalidPasswordException ex) {
            logger.error(ex.getMessage(), ex);
            InvokeAnimation.attentionSeekerWobble(passwordTextField);
        } catch(Exception ex){
            logger.error(ex.getMessage(), ex);
        }
//                   if (!loginDTO.isIsEmailIDNull()) {
//            if (!loginDTO.isIsPasswordNull()) {
//                
//                //Call next screen    
//            } else {
//                InvokeAnimation.attentionSeekerWobble(passwordTextField);
//            }
//        } else {
//            InvokeAnimation.attentionSeekerWobble(famedenUserNameTextField);
//        }
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
                if (CommonValidations.isValidEmailAddress(forgotPasswordBindingDTO.getEmailID())) {
                    //TODO Logic to send Verification email.
                    forgotPasswordDTO.setEmailID(forgotPasswordBindingDTO.getEmailID());

                } else {
                    InvokeAnimation.attentionSeekerWobble(forgotEmailTextField);
                }

            } else if (!CommonValidations.isStringEmpty(forgotPasswordBindingDTO.getUserName())) {
                //forgotPasswordDTO.setUserName(forgotPasswordBindingDTO.getUserName());
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
