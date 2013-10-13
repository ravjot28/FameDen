
package com.fameden.webservice.contracts.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginAndGetUserProfileResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoginAndGetUserProfileResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoginResponse" type="{http://www.fameden.com/}famedenLoginResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginAndGetUserProfileResponse", propOrder = {
    "loginResponse"
})
public class LoginAndGetUserProfileResponse {

    @XmlElement(name = "LoginResponse")
    protected FamedenLoginResponse loginResponse;

    /**
     * Gets the value of the loginResponse property.
     * 
     * @return
     *     possible object is
     *     {@link FamedenLoginResponse }
     *     
     */
    public FamedenLoginResponse getLoginResponse() {
        return loginResponse;
    }

    /**
     * Sets the value of the loginResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link FamedenLoginResponse }
     *     
     */
    public void setLoginResponse(FamedenLoginResponse value) {
        this.loginResponse = value;
    }

}
