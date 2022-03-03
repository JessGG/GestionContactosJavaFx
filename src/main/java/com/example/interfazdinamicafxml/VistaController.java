package com.example.interfazdinamicafxml;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class VistaController implements Initializable {
    @FXML private VistaAnidadaController vistaAnidadaController;
    @FXML private VistaGraficoController vistaGraficoController;
    @FXML private AnchorPane vistaAnidada;
    @FXML private AnchorPane vistaGrafico;
    @FXML private StackPane fondo;
    @FXML private VBox boxMenu;
    @FXML private Button buttonMenu;
    @FXML private ListView<Persona> listView;
    private ObservableList<Persona> personas;
    private boolean menuOculto;
    int contador=1;


    public VistaController() {}

    @Override
    public void initialize(URL url, ResourceBundle bundle) {

        boxMenu.setTranslateX(-120);
        vistaAnidada.setVisible(false);
        vistaGrafico.setVisible(false);
        menuOculto = true;

        personas = FXCollections.observableArrayList();
        cargarDatos();

        listView.setItems(personas);
        listView.getSelectionModel().selectedItemProperty().addListener((x, y, z) -> {
            vistaAnidadaController.cargarDatos(z);
            mostrarVistaAnidada();
        });
    }
    public void mostrarVistaAnidada(){
        vistaAnidada.setVisible(true);

    }

    ////////// SACAR LA VISTA DEL GRÁFICO Y CARGAR LOS DATOS CON LA LISTA DE PERSONAS///////

    public void vistaGrafico() {
        vistaGrafico.setVisible(true);
        vistaGraficoController.cargarPieChart(personas);
    }

    ////////// ANIMACION DEL MENU /////////////

    @FXML
    public void sacarMenu() {
        boxMenu.setTranslateX(0);
        TranslateTransition animacionMenu = new TranslateTransition(Duration.millis(100), boxMenu);
        TranslateTransition animacionBoton = new TranslateTransition(Duration.millis(100), buttonMenu);
        if (!menuOculto) {
            animacionMenu.setFromX(0);
            animacionMenu.setToX(-boxMenu.getWidth());
            animacionBoton.setFromX(boxMenu.getWidth());
            animacionBoton.setToX(0);
            menuOculto = true;
        } else {
            animacionMenu.setFromX(-boxMenu.getWidth());
            animacionMenu.setToX(0);
            animacionBoton.setFromX(0);
            animacionBoton.setToX(boxMenu.getWidth());
            menuOculto = false;
        }

        animacionMenu.play();
        animacionBoton.play();
    }


    /////// CARGAR LOS DATOS DE LA LISTA ////////


    public ObservableList<Persona> cargarDatos() {

        Runnable task = () -> {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://rickandmortyapi.com/api/character?page="+contador))
                    .build();
            HttpResponse<String> response = null;
            try {
                response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
                JSONObject respuestaJson = new JSONObject(response.body());
                JSONArray dataArray = new JSONArray(respuestaJson.getJSONArray("results"));
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject row = dataArray.getJSONObject(i);
                    personas.add(new Persona(
                            row.getString("name"),
                            row.getString("status"),
                            row.getString("image")));
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();

        return personas;
    }

    public void nextPage() {
        if (contador < 42) {
            personas.clear();
            contador++;
            cargarDatos();
        }
    }

    public void lastPage() {
        if (contador > 1) {
            personas.clear();
            --contador;
            cargarDatos();
        }
    }


    ////////// CAMBIO DE CSS   ///////////

    public void darkTheme() {
        fondo.getStylesheets().clear();
        fondo.getStylesheets().add((new File("src/main/resources/styles/style2.css")).toURI().toString());
    }

    public void lightTheme() {
        fondo.getStylesheets().clear();
        fondo.getStylesheets().add((new File("src/main/resources/styles/style1.css")).toURI().toString());
    }

    //////// BOTON SALIR DE LA APP///////

    public void exit() {
        System.exit(0);
    }


}