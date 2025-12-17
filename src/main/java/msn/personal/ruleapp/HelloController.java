package msn.personal.ruleapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TreeTableView<String> treeTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Создаем динамические колонки
        TreeTableColumn<String, String> productCol = new TreeTableColumn<>("Продукт");
        productCol.setCellValueFactory(param -> {
            var rowData = param.getValue().getValue();
            return new SimpleStringProperty(rowData); // -1 для имени продукта
        });

        treeTable.getColumns().add(productCol);

        // 2. Добавляем колонки для фич
        for (int i = 0; i < 10; i++) { // Пример для 10 фич
            TreeTableColumn<String, String> featureCol =
                    new TreeTableColumn<>("Фича " + (i + 1));

            featureCol.setCellValueFactory(param -> {
                return new SimpleStringProperty("X");
            });

            featureCol.setCellFactory(col -> new TreeTableCell<String, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.isEmpty()) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText("X");
                        setAlignment(Pos.CENTER);
                        setStyle("-fx-font-weight: bold; -fx-text-fill: blue;");
                    }
                }
            });

            treeTable.getColumns().add(featureCol);
        }
    }
}
