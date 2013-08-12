/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.util;

import com.fameden.animations.ShakeAnimation;
import com.fameden.animations.WobbleAnimation;
import com.fameden.constants.GlobalConstants;
import javafx.scene.Node;

/**
 *
 * @author Ravjot
 */
public class InvokeAnimation {

    public static void attentionSeekerShake(Node node) {
        ShakeAnimation shakeTransition;
        shakeTransition = new ShakeAnimation(node);
        node.getStyleClass().add(GlobalConstants.promptTextErrorCSSClass);
        shakeTransition.play();
    }

    public static void attentionSeekerWobble(Node node) {
        WobbleAnimation wobbleTransition;
        wobbleTransition = new WobbleAnimation(node);
        node.getStyleClass().add(GlobalConstants.promptTextErrorCSSClass);
        wobbleTransition.play();
    }
}
