import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantManagerFX extends Application {

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        // TAB 1: VIEW & INSERT
        VBox insertBox = new VBox(10);
        insertBox.setPadding(new Insets(15));
        TextField idF = new TextField(); idF.setPromptText("Item ID");
        TextField nameF = new TextField(); nameF.setPromptText("Item Name");
        TextField priceF = new TextField(); priceF.setPromptText("Price");
        TextField resF = new TextField(); resF.setPromptText("Restaurant ID");
        Button insBtn = new Button("Add Item");

        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);
        Button refreshBtn = new Button("View All Items");

        insBtn.setOnAction(e -> {
            try {
                DatabaseHelper.insertItem(Integer.parseInt(idF.getText()), nameF.getText(),
                        Double.parseDouble(priceF.getText()), Integer.parseInt(resF.getText()));
                showAlert("Success", "Item added successfully!");
            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        refreshBtn.setOnAction(e -> {
            try { displayArea.setText(DatabaseHelper.getAllItems()); }
            catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        insertBox.getChildren().addAll(new Label("Manage Menu"), idF, nameF, priceF, resF, insBtn, refreshBtn, displayArea);
        Tab tab1 = new Tab("Insert & View", insertBox);

        // TAB 2: UPDATE & DELETE
        VBox updateBox = new VBox(10);
        updateBox.setPadding(new Insets(15));
        TextField targetId = new TextField(); targetId.setPromptText("Enter Item ID to Update/Delete");
        TextField newPriceF = new TextField(); newPriceF.setPromptText("New Price (for update)");
        Button upBtn = new Button("Update Price");
        Button delBtn = new Button("Delete Item");

        upBtn.setOnAction(e -> {
            try {
                DatabaseHelper.updatePrice(Integer.parseInt(targetId.getText()), Double.parseDouble(newPriceF.getText()));
                showAlert("Updated", "Price changed successfully!");
            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        delBtn.setOnAction(e -> {
            try {
                DatabaseHelper.deleteItem(Integer.parseInt(targetId.getText()));
                showAlert("Deleted", "Item removed from database.");
            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        updateBox.getChildren().addAll(new Label("Update or Delete Records"), targetId, newPriceF, upBtn, delBtn);
        Tab tab2 = new Tab("Update/Delete", updateBox);

        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        stage.setScene(new Scene(tabPane, 500, 550));
        stage.setTitle("SIT FoodHub - Exp10");
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) { launch(args); }
}