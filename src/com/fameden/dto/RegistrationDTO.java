/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.dto;

/**
 *
 * @author Ravjot
 */
public class RegistrationDTO {

    private String fullName;
    private String emailAddress;
    private String alternateEmailAddress;
    private String password;
    private TwitterRegistrationDTO twitterRegistrationDTO;

    public TwitterRegistrationDTO getTwitterRegistrationDTO() {
        return twitterRegistrationDTO;
    }

    public void setTwitterRegistrationDTO(TwitterRegistrationDTO twitterRegistrationDTO) {
        this.twitterRegistrationDTO = twitterRegistrationDTO;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
