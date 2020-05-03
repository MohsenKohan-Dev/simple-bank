package dev.mohsenkohan.simplebank.gui.views;

import dev.mohsenkohan.simplebank.accounts.BankAccount;
import dev.mohsenkohan.simplebank.gui.controllers.ShowAllAccountsController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShowAllAccountsView {

    private final Pane root;
    private final TableView<BankAccount> accountTableView = new TableView<>();

    public ShowAllAccountsView(ShowAllAccountsController controller) {
        initTableView(controller);
        root = createNodeHierarchy(controller);
    }

    public Pane getRoot() {
        return root;
    }

    private void initTableView(ShowAllAccountsController controller) {
        TableColumn<BankAccount, Integer> acctNumColumn = new TableColumn<>("Number");
        acctNumColumn.setCellValueFactory(
                param -> new SimpleObjectProperty<>(param.getValue().getAcctNum()));

        TableColumn<BankAccount, Integer> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(
                param -> new SimpleObjectProperty<>(param.getValue().getBalance()));

        TableColumn<BankAccount, String> acctTypeColumn = new TableColumn<>("Account Type");
        acctTypeColumn.setCellValueFactory(
                param -> new SimpleObjectProperty<>(param.getValue().toString().split("Account")[0]));

        TableColumn<BankAccount, String> foreignColumn = new TableColumn<>("Owner Status");
        foreignColumn.setCellValueFactory(
                param -> new SimpleObjectProperty<>(
                        param.getValue().isForeign() ? "Foreign" : "Domestic"));

        TableColumn<BankAccount, Integer> feeColumn = new TableColumn<>("Fee");
        feeColumn.setCellValueFactory(
                param -> new SimpleObjectProperty<>(param.getValue().fee()));

        accountTableView.getColumns().addAll(acctNumColumn, acctTypeColumn, balanceColumn, foreignColumn, feeColumn);
        accountTableView.setItems(controller.getObservableAccounts());
        accountTableView.setPrefSize(300, 200);
    }

    private Pane createNodeHierarchy(ShowAllAccountsController controller) {
        Label title = new Label("Manage All Accounts");
        double size = title.getFont().getSize();
        title.setFont(new Font(size * 2));
        title.setTextFill(Color.GREEN);

        Button interestButton = new Button("Add Interest");

        VBox vBox = new VBox(8);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(title, accountTableView, interestButton);

        interestButton.setOnAction(e -> controller.interestButtonPressed());

        return vBox;
    }
}
