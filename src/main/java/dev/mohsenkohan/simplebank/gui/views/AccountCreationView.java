package dev.mohsenkohan.simplebank.gui.views;

import dev.mohsenkohan.simplebank.gui.controllers.AccountCreationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AccountCreationView {

    private final Pane root;
    private final Label title = new Label("Create a New Bank Account");

    public AccountCreationView(AccountCreationController controller) {
        controller.setView(this);
        root = createNodeHierarchy(controller);
    }

    public Pane getRoot() {
        return root;
    }

    public void setTitle(String msg) {
        title.setText(msg);
    }

    private Pane createNodeHierarchy(AccountCreationController controller) {
        Label type = new Label("Select Account Type:");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Savings", "Regular Checking", "Interest Checking");

        VBox p3 = new VBox(8);
        p3.setAlignment(Pos.CENTER);
        p3.setPadding(new Insets(10));
        p3.setBackground(new Background(new BackgroundFill(
                Color.SKYBLUE, new CornerRadii(20), new Insets(0))));
        p3.getChildren().addAll(type, choiceBox);

        CheckBox checkBox = new CheckBox("foreign owned?");
        checkBox.setTextFill(Color.GREEN);

        Button btn = new Button("CREATE ACCT");
        btn.setDisable(true);

        VBox p4 = new VBox(8);
        p4.setAlignment(Pos.CENTER);
        p4.setPadding(new Insets(10));
        p4.getChildren().addAll(checkBox, btn);

        HBox p2 = new HBox(8);
        p2.setAlignment(Pos.CENTER);
        p2.setPadding(new Insets(10));
        p2.getChildren().addAll(p3, p4);

        double size = title.getFont().getSize();
        title.setFont(new Font(size * 2));
        title.setTextFill(Color.GREEN);

        VBox p1 = new VBox(8);
        p1.setAlignment(Pos.TOP_CENTER);
        p1.setPadding(new Insets(10));
        p1.getChildren().addAll(title, p2);

        title.setOnMouseEntered(event -> title.setTextFill(Color.RED));
        title.setOnMouseExited(event -> title.setTextFill(Color.GREEN));

        p1.setOnMouseClicked(event -> {
            checkBox.setSelected(false);
            choiceBox.setValue(null);
            title.setText("Create a New Bank Account");
        });

        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            Color color = newValue ? Color.RED : Color.GREEN;
            checkBox.setTextFill(color);
        });

        btn.setOnAction(event -> controller.buttonPressed(
                choiceBox.getSelectionModel().getSelectedIndex(),
                checkBox.isSelected())
        );

        btn.disableProperty().bind(choiceBox.valueProperty().isNull());

        return p1;
    }
}
