module com.example.haaaa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jdk.compiler;

    opens com.example.haaaa to javafx.fxml;
    exports com.example.haaaa;
}