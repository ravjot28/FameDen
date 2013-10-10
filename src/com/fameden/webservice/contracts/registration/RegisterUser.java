
package com.fameden.webservice.contracts.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationRequest" type="{http://contracts.webservice.fameden.com/}famedenRegistrationRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUser", propOrder = {
    "registrationRequest"
})
public class RegisterUser {

    protected FamedenRegistrationRequest registrationRequest;

    /**
     * Gets the value of the registrationRequest property.
     * 
     * @return
     *     possible object is
     *     {@link FamedenRegistrationRequest }
     *     
     */
    public FamedenRegistrationRequest getRegistrationRequest() {
        return registrationRequest;
    }

    /**
     * Sets the value of the registrationRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link FamedenRegistrationRequest }
     *     
     */
    public void setRegistrationRequest(FamedenRegistrationRequest value) {
        this.registrationRequest = value;
    }

}
