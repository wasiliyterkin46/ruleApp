module msn.personal.ruleapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires static lombok;
    requires org.jgrapht.core;

    opens msn.personal.ruleapp to javafx.fxml;
    exports msn.personal.ruleapp;
}