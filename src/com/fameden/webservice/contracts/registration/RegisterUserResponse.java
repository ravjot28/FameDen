
package com.fameden.webservice.contracts.registration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registerUserResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registerUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationResponse" type="{http://registration.contracts.webservice.fameden.com/}famedenRegistrationResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUserResponse", propOrder = {
    "registrationResponse"
})
public class RegisterUserResponse {

    protected FamedenRegistrationResponse registrationResponse;

    /**
     * Gets the value of the registrationResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FamedenRegistrationResponse }
     *     
     */
    public FamedenRegistrationResponse getRegistrationResponse() {
        return registrationResponse;
    }

    /**
     * Sets the value of the registrationResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FamedenRegistrationResponse }
     *     
     */
    public void setRegistrationResponse(FamedenRegistrationResponse value) {
        this.registrationResponse = value;
    }

}
