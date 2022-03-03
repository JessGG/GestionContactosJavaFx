package com.example.interfazdinamicafxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VistaGraficoController implements Initializable {
    @FXML private AnchorPane vistaGrafico;
    @FXML private PieChart pieChart;
    ObservableList<PieChart.Data> datosGrafico;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /////CONTAR LAS PERSONAS MUERTAS, VIVAS Y EN ESTADO DESCONOCIDO EN LA LISTA Y CREAR ELEMENTOS DEL PIECHART//////

    public void cargarPieChart(ObservableList<Persona>lista){

        int unknownCounter =0;
        int aliveCounter=0;
        int deadCounter=0;

        // Cuenta el numero de personas con cada status
        for (Persona persona : lista) {
            switch (persona.getStatus()) {
                case "Alive":
                    aliveCounter++;
                    break;
                case "Dead":
                    deadCounter++;
                    break;
                case "unknown":
                    unknownCounter++;
            }
        }
        datosGrafico = FXCollections.observableArrayList();

        //Agrega a la lista de datos de gráfico cada estado con su contador
        datosGrafico.add(new PieChart.Data("Unknown", unknownCounter));
        datosGrafico.add(new PieChart.Data("Alive", aliveCounter));
        datosGrafico.add(new PieChart.Data("Dead", deadCounter));

        //Mete la lista de datos en la gráfica.
        pieChart.setData(datosGrafico);

        pieChart.setLegendSide(Side.TOP);
        pieChart.setLabelsVisible(false);

    }

    /////METODO PARA CERRAR LA VISTA DEL GRAFICO///////
    @FXML
    public void esconderGrafico(){
        vistaGrafico.setVisible(false);
    }
}
