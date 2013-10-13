/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.service;

import com.fameden.constants.RegistrationConstants;
import com.fameden.controller.TwitterIntegrationController;
import com.fameden.dto.RegistrationDTO;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidConfirmPassword;
import com.fameden.exceptions.InvalidEmailAddressException;
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
public class TwitterRegistrationService implements ICommonService {

    static Logger logger = LoggerFactory.getLogger(TwitterRegistrationService.class);

    @Override
    public boolean validate(Object obj) throws Exception {
        boolean result = false;
        RegistrationDTO registrationDTO = (RegistrationDTO) obj;
        if (CommonValidations.isValidEmailAddress(registrationDTO.getEmailAddress())) {
            if (!CommonValidations.isStringEmpty(registrationDTO.getPassword())) {
                if (!CommonValidations.isStringEmpty(registrationDTO.getConfirmPassword())) {
                    if (registrationDTO.getConfirmPassword().equals(registrationDTO.getPassword())) {
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
            if (!CommonValidations.isStringEmpty(registrationDTO.getEmailAddress())) {
                throw new InvalidEmailAddressException();
            }
            throw new EmptyEmailAddressException();
        }
        return result;
    }

    @Override
    public Object processRequest(Object obj) throws PasswordDoNotMatchException, InvalidPasswordException, InvalidEmailAddressException, EmptyEmailAddressException, Exception {
        FamedenRegistrationResponse response = null;
        try {
            logger.info("validating");
            validate(obj);

            logger.info("after validating");
            ((RegistrationDTO) obj).setPassword(RSAEncryption.encryptText(((RegistrationDTO) obj).getPassword()));
            ((RegistrationDTO) obj).getTwitterRegistrationDTO().setToken(RSAEncryption.encryptText(((RegistrationDTO) obj).getTwitterRegistrationDTO().getToken()));
            ((RegistrationDTO) obj).getTwitterRegistrationDTO().setTokenSecret(RSAEncryption.encryptText(((RegistrationDTO) obj).getTwitterRegistrationDTO().getTokenSecret()));

            logger.info("calling service");
            RegistrationService_Service rs = new RegistrationService_Service();
            response = rs.getRegistrationPort().registerUser((FamedenRegistrationRequest) populateModel(obj));
            logger.info(response.getStatus());
            logger.info(response.getErrorMessage());
            logger.info("after service");
        } catch (PasswordDoNotMatchException ex) {
            throw ex;
        } catch (InvalidPasswordException ex) {
            throw ex;
        } catch (InvalidEmailAddressException ex) {
            throw ex;
        } catch (EmptyEmailAddressException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }

        return response;
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
        request.setPrivateToken(registrationDTO.getTwitterRegistrationDTO().getTokenSecret());
        request.setPublicToken(registrationDTO.getTwitterRegistrationDTO().getToken());
        request.setRegistrationType(RegistrationConstants.twitterRegistrationType);
        request.setRequestType(RegistrationConstants.requestType);
        return request;
    }
}
