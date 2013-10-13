/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.service;

/**
 *
 * @author puneet
 */
public interface ICommonService {
    
    	public boolean validate(Object obj) throws Exception;
	
	public Object processRequest(Object obj);
	
	public Object populateModel(Object obj);
}
