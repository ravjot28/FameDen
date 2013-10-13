/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.bindingDTO;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ravjot
 */
public class TwitterIntegrationBindingDTO extends CommonBindingDTO implements Serializable {

    private StringProperty twitterPin = new SimpleStringProperty();

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
