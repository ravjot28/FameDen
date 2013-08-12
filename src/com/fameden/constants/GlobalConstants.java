/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.constants;

import javafx.animation.Interpolator;

/**
 *
 * @author Ravjot
 */
public class GlobalConstants {
    
    public static final String registrationScene = "registrationScene";
    public static final String registrationSceneFXML = "RegistrationScene.fxml";
    public static final String registrationSceneTitle = "Registration";
    public static final String registrationSceneIconImageLocation = "";
    public static final Interpolator WEB_EASE = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);
    public static final String passwordDoNotMatchMessage = "Password Do Not Match";
    public static final String invalidEmailIDMessage ="Please Enter Valid Email ID";
    public static final String promptTextErrorCSSClass = "promptTextError";
}
