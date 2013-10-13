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
 * @author Rav
 */
public class CommonBindingDTO implements Serializable {

    private StringProperty emailAddress = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confrimPassword = new SimpleStringProperty();

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
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
