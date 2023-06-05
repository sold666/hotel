module com.sold.hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.security.core;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires log4j;
    requires spring.security.crypto;

    exports com.sold.hotel;
    opens com.sold.hotel to javafx.fxml;
    exports com.sold.hotel.entities;
    opens com.sold.hotel.entities to javafx.fxml;
    exports com.sold.hotel.utils;
    opens com.sold.hotel.utils to javafx.fxml;
    exports com.sold.hotel.controllers.main_controllers;
    opens com.sold.hotel.controllers.main_controllers to javafx.fxml;
    exports com.sold.hotel.controllers.edit_controllers;
    opens com.sold.hotel.controllers.edit_controllers to javafx.fxml;
    exports com.sold.hotel.controllers.tables_controllers;
    opens com.sold.hotel.controllers.tables_controllers to javafx.fxml;
    exports com.sold.hotel.controllers.add_controllers;
    opens com.sold.hotel.controllers.add_controllers to javafx.fxml;
}