
package com.fameden.webservice.contracts.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for famedenLoginResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="famedenLoginResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://login.contracts.webservice.fameden.com/}genericResponse">
 *       &lt;sequence>
 *         &lt;element name="genericResponse" type="{http://login.contracts.webservice.fameden.com/}genericResponse" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "famedenLoginResponse", propOrder = {
    "genericResponse",
    "userName"
})
public class FamedenLoginResponse
    extends GenericResponse
{

    protected GenericResponse genericResponse;
    protected String userName;

    /**
     * Gets the value of the genericResponse property.
     * 
     * @return
     *     possible object is
     *     {@link GenericResponse }
     *     
     */
    public GenericResponse getGenericResponse() {
        return genericResponse;
    }

    /**
     * Sets the value of the genericResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericResponse }
     *     
     */
    public void setGenericResponse(GenericResponse value) {
        this.genericResponse = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
