/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.exceptions;

/**
 *
 * @author Rav
 */
public class InvalidAlternateEmailAddressException extends Exception{
    
    public InvalidAlternateEmailAddressException(){
        super("Invalid Alternate Email Address");
    }
    
}
