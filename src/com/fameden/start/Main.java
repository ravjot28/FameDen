/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fameden.start;

import com.fameden.constants.GlobalConstants;
import com.fameden.fxml.SceneNavigator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ravjot
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneNavigator sceneNavigator = new SceneNavigator();
        sceneNavigator.loadScreen(GlobalConstants.registrationScene, GlobalConstants.registrationSceneFXML);
        sceneNavigator.loadScreen(GlobalConstants.twitterIntegrationScene, GlobalConstants.twitterIntegrationSceneFXML);
        Group root = new Group();
        root.getChildren().addAll(sceneNavigator);
        root.autosize();
        sceneNavigator.setScreen(GlobalConstants.registrationScene);
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(GlobalConstants.calendarCSSPath);
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle(GlobalConstants.registrationSceneTitle);
        stage.getIcons().add(new Image(GlobalConstants.registrationLoginIconImage));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
