/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.bindingDTO;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ravjot
 */
public class TwitterIntegrationBindingDTO {

    private StringProperty emailAddress = new SimpleStringProperty();
    private StringProperty twitterPin = new SimpleStringProperty();

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getTwitterPin() {
        return twitterPin.get();
    }

    public void setTwitterPin(String twitterPin) {
        this.twitterPin.set(twitterPin);
    }

    public StringProperty twitterPinProperty() {
        return twitterPin;
    }
}
