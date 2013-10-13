/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.controller;

import com.fameden.bindingDTO.ForgotPasswordBindingDTO;
import com.fameden.constants.GlobalConstants;
import com.fameden.dto.ForgotPasswordDTO;
import com.fameden.fxml.SceneNavigator;
import com.fameden.util.CommonValidations;
import com.fameden.util.InvokeAnimation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author puneet
 */
public class ForgotPasswordSceneController implements Initializable, IScreenController {

    @FXML
    private TextField emailAddressTextField, uaerNameTextField;
    private ForgotPasswordBindingDTO forgotPasswordBindingDTO;
    private ForgotPasswordDTO forgotPasswordDTO;
    SceneNavigator myController;

    public ForgotPasswordSceneController() {
        this.forgotPasswordBindingDTO = new ForgotPasswordBindingDTO();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bindings.bindBidirectional(this.emailAddressTextField.textProperty(), this.forgotPasswordBindingDTO.emailProperty());
        Bindings.bindBidirectional(this.uaerNameTextField.textProperty(), this.forgotPasswordBindingDTO.userNameProperty());
    }
    
    @Override
    public void setScreenParent(SceneNavigator screenPage) {
        myController = screenPage;
    }

    @FXML
    public void nevermind() {
        myController.setScreen(GlobalConstants.registrationScene);

    }

    @FXML
    public void sendVerificationEmail() {
        
        if ((CommonValidations.isStringEmpty(this.forgotPasswordBindingDTO.getEmailID())) && 
                (CommonValidations.isStringEmpty(this.forgotPasswordBindingDTO.getUserName())) )
        {  
            InvokeAnimation.attentionSeekerShake(emailAddressTextField);
            InvokeAnimation.attentionSeekerShake(uaerNameTextField);
        } else{
            
            if (CommonValidations.isValidEmailAddress(this.forgotPasswordBindingDTO.getUserName()))
            {

                forgotPasswordDTO = new ForgotPasswordDTO();
                forgotPasswordDTO.setEmailID(this.forgotPasswordBindingDTO.getEmailID());
                //forgotPasswordDTO.setUserName(this.forgotPasswordBindingDTO.getUserName());
                
                //TODO Call Login Service and Receive returning object

            } else {
                InvokeAnimation.attentionSeekerWobble(emailAddressTextField);
            } 
        } 
        
    }

    @FXML
    public void closeFired() {
        System.exit(0);
    }
}
