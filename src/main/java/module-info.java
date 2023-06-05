module com.example.ooptransport {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires gson.extras;
    requires Archive;
    requires ZipArchive;
    requires GZipArchive;
    requires JarArchive;

    opens com.example.ooptransport to javafx.fxml, com.google.gson;
    exports com.example.ooptransport;
    exports com.example.ooptransport.customcontrols;
    opens com.example.ooptransport.customcontrols to javafx.fxml;
    exports com.example.ooptransport.transport;
    opens com.example.ooptransport.transport to javafx.fxml, com.google.gson;
    exports com.example.ooptransport.transportfactory;
    opens com.example.ooptransport.transportfactory to javafx.fxml;
}