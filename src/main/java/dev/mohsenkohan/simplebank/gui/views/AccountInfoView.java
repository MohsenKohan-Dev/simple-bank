package dev.mohsenkohan.simplebank.gui.views;

import dev.mohsenkohan.simplebank.gui.controllers.AccountInfoController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AccountInfoView {

    private final Pane root;
    private final TextField balanceTextField;
    private final ChoiceBox<String> foreignChoiceBox = new ChoiceBox<>();

    public AccountInfoView(AccountInfoController controller) {
        controller.setView(this);
        balanceTextField = createTextField(true);
        root = createNodeHierarchy(controller);
    }

    public Pane getRoot() {
        return root;
    }

    public void setBalance(String balance) {
        balanceTextField.setText(balance);
    }

    public void setForeign(boolean isForeign) {
        String is = isForeign ? "Foreign" : "Domestic";
        foreignChoiceBox.setValue(is);
    }

    private Pane createNodeHierarchy(AccountInfoController controller) {
        Label selectLabel = new Label("Account #");
        TextField selectTextField = createTextField(false);
        Button selectButton = new Button("Select Account");
        Node p6 = createHBox(selectLabel, selectTextField, selectButton);

        Label balanceLabel = new Label("Balance:");
        Node p7 = createHBox(balanceLabel, balanceTextField);

        Node p2 = createBorderedVBox(p6, p7);

        Label depositLabel = new Label("Amt to Deposit");
        TextField depositTextField = createTextField(false);
        Button depositButton = new Button("Deposit");
        Node p8 = createHBox(depositLabel, depositTextField, depositButton);

        Node p3 = createBorderedVBox(p8);

        Label loanLabel = new Label("Loan Amt");
        TextField loanTextField = createTextField(false);
        Button loanButton = new Button("Approve Loan");
        Node p9 = createHBox(loanLabel, loanTextField, loanButton);

        Label responseLabel = new Label("Approval Status: ");
        TextField responseTextField = createTextField(true);
        Node p10 = createHBox(responseLabel, responseTextField);

        Node p4 = createBorderedVBox(p9, p10);

        Label foreignLabel = new Label("Ownership");
        foreignChoiceBox.getItems().addAll("Foreign", "Domestic");
        Button foreignButton = new Button("Set Ownership");
        Node p11 = createHBox(foreignLabel, foreignChoiceBox, foreignButton);

        Node p5 = createBorderedVBox(p11);


        Label title = new Label("Access an Existing Account");
        double size = title.getFont().getSize();
        title.setFont(new Font(size * 2));
        title.setTextFill(Color.GREEN);

        VBox p1 = new VBox(8);
        p1.setAlignment(Pos.TOP_CENTER);
        p1.setPadding(new Insets(10));
        p1.getChildren().addAll(title, p2, p3, p4, p5);

        selectButton.setOnAction(
                event -> controller.selectButtonPressed(selectTextField.getText()));
        depositButton.setOnAction(
                event -> controller.depositButtonPressed(depositTextField.getText()));
        loanButton.setOnAction(event -> responseTextField.setText(
                controller.loanButtonPressed(loanTextField.getText())));
        foreignButton.setOnAction(
                event -> controller.foreignButtonPressed(foreignChoiceBox.getValue()));

        return p1;
    }

    private TextField createTextField(boolean disable) {
        TextField result = new TextField();
        result.setPrefWidth(100);
        result.setDisable(disable);
        return result;
    }

    private Node createHBox(Node... children) {
        HBox hb = new HBox(5);
        hb.setAlignment(Pos.CENTER_LEFT);
        hb.setPadding(new Insets(2));
        hb.getChildren().addAll(children);
        return hb;
    }

    private Node createBorderedVBox(Node... children) {
        VBox vb = new VBox(4);
        vb.setPadding(new Insets(8));
        vb.setBorder(new Border(new BorderStroke(
                Color.BROWN, BorderStrokeStyle.SOLID, null, null, new Insets(10))));
        vb.getChildren().addAll(children);
        return vb;
    }
}
