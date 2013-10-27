/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.service;

import com.fameden.dto.LoginDTO;
import com.fameden.exceptions.EmptyEmailAddressException;
import com.fameden.exceptions.InvalidEmailAddressException;
import com.fameden.exceptions.InvalidPasswordException;
import com.fameden.util.CommonValidations;
import com.fameden.util.RSAEncryption;
import com.fameden.webservice.contracts.login.FamedenLoginRequest;
import com.fameden.webservice.contracts.login.FamedenLoginResponse;
import com.fameden.webservice.login.LoginService_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import twitter4j.internal.logging.LoggerFactory;

/**
 *
 * @author puneet
 */
public class LoginService implements ICommonService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Override
    public boolean validate(Object obj) throws Exception {
        LoginDTO dto = (LoginDTO) obj;

        if (CommonValidations.isValidEmailAddress(dto.getEmailID())) {
            if (!CommonValidations.isStringEmpty(dto.getPassword())) {

                return true;

            } else {
                throw new InvalidPasswordException();
            }
        } else {
            if (!CommonValidations.isStringEmpty(dto.getEmailID())) {
                throw new InvalidEmailAddressException();
            }
            throw new EmptyEmailAddressException();
        }
    }

    @Override
    public Object processRequest(Object obj) throws InvalidPasswordException, InvalidEmailAddressException,
            EmptyEmailAddressException, Exception {
        FamedenLoginResponse response = null;
        logger.info("in processRequest");
        LoginDTO dto = (LoginDTO) obj;
        try {
            validate(dto);
            dto.setPassword(RSAEncryption.encryptText(dto.getPassword()));
            FamedenLoginRequest requestModel = (FamedenLoginRequest) this.populateModel(obj);
            logger.info("in processRequest");
            LoginService_Service loginService = new LoginService_Service();
            logger.info("Hitting webservice");
            response = loginService.getLoginPort().loginAndGetUserProfile(requestModel);
        } catch (InvalidPasswordException ex) {
            throw ex;
        } catch (EmptyEmailAddressException ex) {
            throw ex;
        } catch (InvalidEmailAddressException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return response;
    }

    @Override
    public Object populateModel(Object obj) {

        LoginDTO dto = (LoginDTO) obj;
        FamedenLoginRequest requestModel = new FamedenLoginRequest();

        requestModel.setEmailAddress(dto.getEmailID());
        requestModel.setPassword(dto.getPassword());
        requestModel.setLoginMode(dto.getLoginMode());
        return requestModel;

    }
}
