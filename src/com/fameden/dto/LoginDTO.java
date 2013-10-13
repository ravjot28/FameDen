/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.dto;

/**
 *
 * @author puneet
 */
public class LoginDTO {
    
    private String emailID;
    private String password;
    private String loginMode;
    private boolean isEmailIDNull;
    private boolean isPasswordNull;

    /**
     * @return the emailID
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * @param emailID the emailID to set
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the loginMode
     */
    public String getLoginMode() {
        return loginMode;
    }

    /**
     * @param loginMode the loginMode to set
     */
    public void setLoginMode(String loginMode) {
        this.loginMode = loginMode;
    }

    /**
     * @return the isEmailIDNull
     */
    public boolean isIsEmailIDNull() {
        return isEmailIDNull;
    }

    /**
     * @param isEmailIDNull the isEmailIDNull to set
     */
    public void setIsEmailIDNull(boolean isEmailIDNull) {
        this.isEmailIDNull = isEmailIDNull;
    }

    /**
     * @return the isPasswordNull
     */
    public boolean isIsPasswordNull() {
        return isPasswordNull;
    }

    /**
     * @param isPasswordNull the isPasswordNull to set
     */
    public void setIsPasswordNull(boolean isPasswordNull) {
        this.isPasswordNull = isPasswordNull;
    }
    

}
