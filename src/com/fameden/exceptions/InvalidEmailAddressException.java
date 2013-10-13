/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.exceptions;

/**
 *
 * @author Rav
 */
public class InvalidEmailAddressException extends Exception{
    
     public InvalidEmailAddressException(){
        super("Invalid Email Address");
    }
    
}
