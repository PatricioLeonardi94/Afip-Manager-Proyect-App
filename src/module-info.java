module sample {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires poi.ooxml;
    requires poi;

    opens com.company;
    opens com.company.java.model.Database to javafx.base;
    opens com.company.java.model.Afip to javafx.base;
    opens com.company.java.resources to javafx.fxml;

    exports com.company.java.resources to javafx.fxml;
    exports com.company.java.model.Database to javafx.fxml;
}