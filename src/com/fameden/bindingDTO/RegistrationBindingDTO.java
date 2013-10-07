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
public class RegistrationBindingDTO implements Serializable {

    private StringProperty fullName = new SimpleStringProperty();
    private StringProperty emailAddress = new SimpleStringProperty();
    private StringProperty alternateEmailAddress = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confrimPassword = new SimpleStringProperty();

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
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

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getConfrimPassword() {
        return confrimPassword.get();
    }

    public void setConfrimPassword(String confrimPassword) {
        this.confrimPassword.set(confrimPassword);
    }

    public StringProperty confrimPasswordProperty() {
        return confrimPassword;
    }
}
