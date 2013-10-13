/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.exceptions;

/**
 *
 * @author Rav
 */
public class InvalidConfirmPassword extends Exception{
    
     public InvalidConfirmPassword(){
        super("Password is empty");
    }
    
}
