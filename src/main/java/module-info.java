module com.example.lgi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.lgi to javafx.fxml;
    exports com.example.lgi;
}