/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.util;

import ipaddressfinder.GetIPAddress;

/**
 *
 * @author Rav
 */
public class GetIP {
    
    public static String getIP(){
        GetIPAddress ipAdd = new GetIPAddress();
        return ipAdd.process();
    }
    
}
