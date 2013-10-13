
package com.fameden.webservice.login;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.fameden.webservice.contracts.login.FamedenLoginRequest;
import com.fameden.webservice.contracts.login.FamedenLoginResponse;
import com.fameden.webservice.contracts.login.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LoginServiceContract", targetNamespace = "http://www.fameden.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LoginServiceContract {


    /**
     * 
     * @param loginRequest
     * @return
     *     returns com.fameden.FamedenLoginResponse
     */
    @WebMethod(operationName = "LoginAndGetUserProfile")
    @WebResult(name = "LoginResponse", targetNamespace = "")
    @RequestWrapper(localName = "LoginAndGetUserProfile", targetNamespace = "http://www.fameden.com/", className = "com.fameden.LoginAndGetUserProfile")
    @ResponseWrapper(localName = "LoginAndGetUserProfileResponse", targetNamespace = "http://www.fameden.com/", className = "com.fameden.LoginAndGetUserProfileResponse")
    @Action(input = "http://www.fameden.com/LoginServiceContract/LoginAndGetUserProfileRequest", output = "http://www.fameden.com/LoginServiceContract/LoginAndGetUserProfileResponse")
    public FamedenLoginResponse loginAndGetUserProfile(
        @WebParam(name = "LoginRequest", targetNamespace = "")
        FamedenLoginRequest loginRequest);

}
