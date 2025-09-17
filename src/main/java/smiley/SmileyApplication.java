package smiley;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


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
        VBox buttonOptions = new VBox();

        // buttons for VBox
        Button clearDrawingButton = new Button("Clear Canvas");
        Button drawSmileyButton = new Button("Draw Smiley");
        buttonOptions.getChildren().addAll(clearDrawingButton, drawSmileyButton);
        
        // set layout
        paintLayout.setCenter(canvas);
        paintLayout.setRight(colorPalette);
        paintLayout.setLeft(buttonOptions);

        buttonOptions.setSpacing(10);
        buttonOptions.setPadding(new Insets(10, 0, 0, 10));
        colorPalette.setPadding(new Insets(10, 10, 0, 0));

        // draw smiley face
        this.drawSmiley(canvas, graphics);
        
        // event handlers
        canvas.setOnMouseDragged((event) -> {
            // allow user to draw in canvas (center pane only)
            Double xLoc = event.getX();
            Double yLoc = event.getY();
            graphics.setFill(colorPalette.getValue());
            graphics.fillOval(xLoc, yLoc, 4, 4);
        });

        clearDrawingButton.setOnMouseClicked((event) -> {
            graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });

        drawSmileyButton.setOnMouseClicked((event) -> {
            // draw smiley face
            this.drawSmiley(canvas, graphics);
        });

        // set scene
        Scene scene = new Scene(paintLayout);
        window.setScene(scene);
        window.show();

    }

    public void drawSmiley(Canvas canvas, GraphicsContext graphics){
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
    }

}
