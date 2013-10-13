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
public class RegistrationBindingDTO extends CommonBindingDTO implements Serializable {

    private StringProperty fullName = new SimpleStringProperty();
    private StringProperty alternateEmailAddress = new SimpleStringProperty();

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress.get();
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress.set(alternateEmailAddress);
    }

    public StringProperty alternateEmailAddressProperty() {
        return alternateEmailAddress;
    }
}
