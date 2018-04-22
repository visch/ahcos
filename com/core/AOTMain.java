package core;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import javafx.application.Application;

public class AOTMain extends Application  {

    /*target to be display in the app*/


    public static void main(String[] args) {

        launch(args);
    }


    static Text text = new Text(); //"Begrüßungstext"

    @Override
    public void start(Stage primaryStage) {


        GlobalScreen.addNativeKeyListener(new ActionHandler());
        /*connect to global keys*/
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        /*launch program*/


        text.setText("output");

        text.setFont(new Font(20));


//		text.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			@Override
//			public void handle(KeyEvent e) {
//				text.requestFocus();
//				if (e.getText() == "a") {
//					primaryStage.initStyle(StageStyle.UTILITY);
//				}
//
//				else if (e.getText() == "b") {
//					primaryStage.initStyle(StageStyle.DECORATED);
//				}
//			}
//
//		});

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(text, 0, 0);

        inputGridPane.add(text, 0, 0);

        final Pane pane = new VBox(10);
        pane.getChildren().add(inputGridPane);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

    }



}