
package com.fameden.webservice.contracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fameden.webservice.contracts package. 
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

    private final static QName _RegisterUserResponse_QNAME = new QName("http://contracts.webservice.fameden.com/", "registerUserResponse");
    private final static QName _RegisterUser_QNAME = new QName("http://contracts.webservice.fameden.com/", "registerUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fameden.webservice.contracts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link FamedenRegistrationResponse }
     * 
     */
    public FamedenRegistrationResponse createFamedenRegistrationResponse() {
        return new FamedenRegistrationResponse();
    }

    /**
     * Create an instance of {@link GenericResponse }
     * 
     */
    public GenericResponse createGenericResponse() {
        return new GenericResponse();
    }

    /**
     * Create an instance of {@link FamedenRegistrationRequest }
     * 
     */
    public FamedenRegistrationRequest createFamedenRegistrationRequest() {
        return new FamedenRegistrationRequest();
    }

    /**
     * Create an instance of {@link GenericRequest }
     * 
     */
    public GenericRequest createGenericRequest() {
        return new GenericRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://contracts.webservice.fameden.com/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://contracts.webservice.fameden.com/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

}
