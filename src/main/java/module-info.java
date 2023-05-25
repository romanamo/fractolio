module de.romanamo.fractolio {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apfloat;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;
    requires javafx.swing;
    requires java.logging;

    opens de.romanamo.fractolio to javafx.fxml;
    exports de.romanamo.fractolio;
}