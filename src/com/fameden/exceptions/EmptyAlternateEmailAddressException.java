/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.exceptions;

/**
 *
 * @author Rav
 */
public class EmptyAlternateEmailAddressException extends Exception{
    
    public EmptyAlternateEmailAddressException(){
        super("Alternate Email address is empty");
    }
    
}
