package smiley;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;


public class SmileyApplication extends Application{


    public static void main(String[] args) {
        System.out.println("Displaying paint window...");
        launch(SmileyApplication.class);
    }

    public void start(Stage window){
        // create elements
        BorderPane paintLayout = new BorderPane();
        Canvas canvas = new Canvas(640, 480);
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        ColorPicker colorPalette = new ColorPicker();

        // set layout
        paintLayout.setCenter(canvas);
        paintLayout.setRight(colorPalette);

        // draw smiley face
        graphics.setFill(Color.BLACK);
        // eyes
        graphics.fillRect(0.375*canvas.getWidth(), 0.25*canvas.getHeight(), 48, 48);
        graphics.fillRect(0.625*canvas.getWidth(), 0.25*canvas.getHeight(), 48, 48);

        // mouth corners
        graphics.fillRect(0.375*canvas.getWidth() - 48, 0.625*canvas.getHeight()-48, 48, 48);
        graphics.fillRect(0.625*canvas.getWidth() + 48, 0.625*canvas.getHeight()-48, 48, 48);

        // mouth
        graphics.fillRect(0.375*canvas.getWidth(), 0.625*canvas.getHeight(), 0.25*canvas.getWidth() + 48, 48);

        

        // event handler
        canvas.setOnMouseDragged((event) -> {
            Double xLoc = event.getX();
            Double yLoc = event.getY();
            System.out.println("Location Clicked: (" + xLoc + ", " + yLoc +")");
            

            // allow user to draw elsewhere
            graphics.setFill(colorPalette.getValue());
            graphics.fillOval(xLoc, yLoc, 4, 4);
        });

        // set scene
        Scene scene = new Scene(paintLayout);
        window.setScene(scene);
        window.show();

    }

}
