module com.example.ooptransport {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ooptransport to javafx.fxml;
    exports com.example.ooptransport;
}