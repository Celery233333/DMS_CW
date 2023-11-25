module code.dms_coursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens code.dms_coursework to javafx.fxml;
    opens code.dms_coursework.view to javafx.fxml;
    opens code.dms_coursework.controller to javafx.fxml;
    opens code.dms_coursework.model to javafx.fxml;

    exports code.dms_coursework;
    exports code.dms_coursework.view;
    exports code.dms_coursework.controller;
    exports code.dms_coursework.model;
}