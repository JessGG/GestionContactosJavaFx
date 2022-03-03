module com.example.interfazdinamicafxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.json;
    requires java.net.http;

    opens com.example.interfazdinamicafxml to javafx.fxml;
    exports com.example.interfazdinamicafxml;

}