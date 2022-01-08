module by.matskevich.wtimer {
    requires javafx.controls;
    requires javafx.fxml;


    opens by.matskevich.wtimer to javafx.fxml;
    exports by.matskevich.wtimer;
    exports by.matskevich.wtimer.controller;
    opens by.matskevich.wtimer.controller to javafx.fxml;
}