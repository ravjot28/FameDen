/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.exceptions;

/**
 *
 * @author Rav
 */
public class EmptyEmailAddressException extends Exception{
    public EmptyEmailAddressException(){
        super("Email address is empty");
    }
}
