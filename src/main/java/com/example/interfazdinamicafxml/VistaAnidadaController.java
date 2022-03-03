package com.example.interfazdinamicafxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.javafx.FontIcon;

public class VistaAnidadaController {
    
    @FXML private Label nombre;
    @FXML private Label status;
    @FXML private AnchorPane vistaAnidada;
    @FXML private Circle circulo;


    public void initialize(){}


    public void cargarDatos(Persona persona){

        FontIcon status_icon = new FontIcon();
        status.setGraphic(status_icon);
        status_icon.setIconSize(20);

        nombre.setText(persona.getNombre());
        status.setText(persona.getStatus());
        String foto = persona.getImage();

        Image image = new Image(foto);
        circulo.setFill(new ImagePattern(image));
        circulo.setRadius(80);


        ///////// CAMBIAR EL ICONO DE STATUS ////////

        switch (status.getText()){
            case "Dead":
                status_icon.setIconLiteral("mdi2h-heart-broken");
                break;
            case "Alive":
                status_icon.setIconLiteral("mdi2h-heart");
                break;
            case "unknown":
                status_icon.setIconLiteral("mdi2h-help-circle-outline");
                break;
        }
    }

    /////////MÉTODO PARA CERRAR LA VISTA ANIDADA /////////

    public void closeVista(){
        vistaAnidada.setVisible(false);
    }
}
