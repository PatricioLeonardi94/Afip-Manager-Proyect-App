package com.company.java.styles;

import com.company.java.model.Database.Cliente;
import com.company.java.model.Database.Resumen;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class fxmlStyler {

    private static fxmlStyler instance = new fxmlStyler();

    public static fxmlStyler getInstance() {
        return instance;
    }

    public void centerStage(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }
}
