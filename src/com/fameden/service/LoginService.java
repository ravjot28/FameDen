/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.service;

import com.fameden.constants.LoginConstants;
import com.fameden.dto.LoginDTO;
import com.fameden.exceptions.LoginValidationException;
import com.fameden.util.CommonValidations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author puneet
 */
public class LoginService implements ICommonService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Override
    public boolean validate(Object obj) throws LoginValidationException {
        LoginDTO dto = (LoginDTO) obj;

        if (!CommonValidations.isStringEmpty(dto.getEmailID())) {
            if (!CommonValidations.isStringEmpty(dto.getPassword())) {

                return true;

            } else {
                dto.setIsPasswordNull(true);
                throw new LoginValidationException(LoginConstants.invalidPasswordMessage);
                //InvokeAnimation.attentionSeekerWobble(passwordTextField);
            }
        } else {
            dto.setIsEmailIDNull(true);
            throw new LoginValidationException(LoginConstants.invalidUserIDMessage);
            //InvokeAnimation.attentionSeekerWobble(famedenUserNameTextField);
        }
    }

    @Override
    public Object processRequest(Object obj) {
        LoginDTO dto = (LoginDTO) obj;
        boolean validation = false;
        String errorMessage = null;
        int requestId = -1;

        try {
            validation = validate(dto);
        } catch (LoginValidationException e) {
            logger.error(e.getMessage(), e);
            errorMessage = e.getMessage();
        }

        return dto;

    }

    @Override
    public Object populateModel(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
