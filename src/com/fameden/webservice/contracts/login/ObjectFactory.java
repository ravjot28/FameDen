
package com.fameden.webservice.contracts.login;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fameden.webservice.contracts.login package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginAndGetUserProfile_QNAME = new QName("http://login.contracts.webservice.fameden.com/", "LoginAndGetUserProfile");
    private final static QName _LoginAndGetUserProfileResponse_QNAME = new QName("http://login.contracts.webservice.fameden.com/", "LoginAndGetUserProfileResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fameden.webservice.contracts.login
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginAndGetUserProfile }
     * 
     */
    public LoginAndGetUserProfile createLoginAndGetUserProfile() {
        return new LoginAndGetUserProfile();
    }

    /**
     * Create an instance of {@link LoginAndGetUserProfileResponse }
     * 
     */
    public LoginAndGetUserProfileResponse createLoginAndGetUserProfileResponse() {
        return new LoginAndGetUserProfileResponse();
    }

    /**
     * Create an instance of {@link GenericResponse }
     * 
     */
    public GenericResponse createGenericResponse() {
        return new GenericResponse();
    }

    /**
     * Create an instance of {@link FamedenLoginRequest }
     * 
     */
    public FamedenLoginRequest createFamedenLoginRequest() {
        return new FamedenLoginRequest();
    }

    /**
     * Create an instance of {@link GenericRequest }
     * 
     */
    public GenericRequest createGenericRequest() {
        return new GenericRequest();
    }

    /**
     * Create an instance of {@link FamedenLoginResponse }
     * 
     */
    public FamedenLoginResponse createFamedenLoginResponse() {
        return new FamedenLoginResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginAndGetUserProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.contracts.webservice.fameden.com/", name = "LoginAndGetUserProfile")
    public JAXBElement<LoginAndGetUserProfile> createLoginAndGetUserProfile(LoginAndGetUserProfile value) {
        return new JAXBElement<LoginAndGetUserProfile>(_LoginAndGetUserProfile_QNAME, LoginAndGetUserProfile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginAndGetUserProfileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.contracts.webservice.fameden.com/", name = "LoginAndGetUserProfileResponse")
    public JAXBElement<LoginAndGetUserProfileResponse> createLoginAndGetUserProfileResponse(LoginAndGetUserProfileResponse value) {
        return new JAXBElement<LoginAndGetUserProfileResponse>(_LoginAndGetUserProfileResponse_QNAME, LoginAndGetUserProfileResponse.class, null, value);
    }

}
