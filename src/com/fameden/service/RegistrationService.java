/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.service;

import com.fameden.constants.RegistrationConstants;
import com.fameden.dto.RegistrationDTO;
import com.fameden.exceptions.EmptyAlternateEmailAddressException;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidAlternateEmailAddressException;
import com.fameden.exceptions.InvalidConfirmPassword;
import com.fameden.exceptions.InvalidEmailAddressException;
import com.fameden.exceptions.InvalidFullNameException;
import com.fameden.exceptions.InvalidPasswordException;
import com.fameden.exceptions.PasswordDoNotMatchException;
import com.fameden.util.CommonValidations;
import com.fameden.util.GetIP;
import com.fameden.util.RSAEncryption;
import com.fameden.webservice.contracts.registration.FamedenRegistrationRequest;
import com.fameden.webservice.contracts.registration.FamedenRegistrationResponse;
import com.fameden.webservice.registration.RegistrationService_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Rav
 */
public class RegistrationService implements ICommonService {

    static Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Override
    public boolean validate(Object obj) throws Exception {
        boolean result = false;
        RegistrationDTO registrationBindingDTO = (RegistrationDTO) obj;
        if (!CommonValidations.isStringEmpty(registrationBindingDTO.getFullName())) {
            if (CommonValidations.isValidEmailAddress(registrationBindingDTO.getEmailAddress())) {
                if (CommonValidations.isValidEmailAddress(registrationBindingDTO.getAlternateEmailAddress())) {
                    if (!CommonValidations.isStringEmpty(registrationBindingDTO.getPassword())) {
                        if (!CommonValidations.isStringEmpty(registrationBindingDTO.getConfirmPassword())) {
                            if (registrationBindingDTO.getPassword().equals(registrationBindingDTO.getConfirmPassword())) {
                                result = true;
                            } else {
                                throw new PasswordDoNotMatchException();
                            }
                        } else {
                            throw new InvalidConfirmPassword();
                        }
                    } else {
                        throw new InvalidPasswordException();
                    }
                } else {
                    if (!CommonValidations.isStringEmpty(registrationBindingDTO.getAlternateEmailAddress())) {
                        throw new InvalidAlternateEmailAddressException();
                    }
                    throw new EmptyAlternateEmailAddressException();
                }
            } else {
                if (!CommonValidations.isStringEmpty(registrationBindingDTO.getEmailAddress())) {
                    throw new InvalidEmailAddressException();
                }
                throw new EmptyEmailAddressException();
            }
        } else {
            throw new InvalidFullNameException();
        }
        return result;
    }

    @Override
    public Object processRequest(Object obj) throws PasswordDoNotMatchException, InvalidConfirmPassword,
            InvalidAlternateEmailAddressException, EmptyAlternateEmailAddressException, InvalidPasswordException, InvalidEmailAddressException,
            EmptyEmailAddressException, InvalidFullNameException, Exception {
        logger.info("in processRequest");
        try {
            validate(obj);
            logger.info(((RegistrationDTO) obj).getPassword());
            ((RegistrationDTO) obj).setPassword(RSAEncryption.encryptText(((RegistrationDTO) obj).getPassword()));
             logger.info("in processRequest");
             RegistrationService_Service rs = new RegistrationService_Service();
             logger.info("Hitting webservice");
             FamedenRegistrationResponse response = rs.getRegistrationPort().registerUser((FamedenRegistrationRequest) populateModel(obj));
        } catch (PasswordDoNotMatchException ex) {
            throw ex;
        } catch (InvalidConfirmPassword ex) {
            throw ex;
        } catch (InvalidAlternateEmailAddressException ex) {
            throw ex;
        } catch (EmptyAlternateEmailAddressException ex) {
            throw ex;
        } catch (InvalidPasswordException ex) {
            throw ex;
        } catch (InvalidEmailAddressException ex) {
            throw ex;
        } catch (EmptyEmailAddressException ex) {
            throw ex;
        } catch (InvalidFullNameException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    @Override
    public Object populateModel(Object obj) {
        FamedenRegistrationRequest request = new FamedenRegistrationRequest();
        RegistrationDTO registrationDTO = (RegistrationDTO) obj;
        request.setAlternateEmailAddress(registrationDTO.getAlternateEmailAddress());
        request.setCustomerIP(GetIP.getIP());
        request.setEmailAddress(registrationDTO.getEmailAddress());
        request.setFullName(registrationDTO.getFullName());
        request.setPassword(registrationDTO.getPassword());
        request.setPrivateToken(null);
        request.setPublicToken(null);
        request.setRegistrationType(RegistrationConstants.famedenRegistrationType);
        request.setRequestType(RegistrationConstants.requestType);
        return request;
    }
}
