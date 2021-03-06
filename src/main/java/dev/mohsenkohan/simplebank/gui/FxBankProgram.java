package dev.mohsenkohan.simplebank.gui;

import dev.mohsenkohan.simplebank.SavedBankInfo;
import dev.mohsenkohan.simplebank.gui.controllers.AccountCreationController;
import dev.mohsenkohan.simplebank.gui.controllers.AccountInfoController;
import dev.mohsenkohan.simplebank.gui.controllers.ShowAllAccountsController;
import dev.mohsenkohan.simplebank.gui.views.AccountCreationView;
import dev.mohsenkohan.simplebank.gui.views.AccountInfoView;
import dev.mohsenkohan.simplebank.gui.views.ShowAllAccountsView;
import dev.mohsenkohan.simplebank.model.Bank;
import dev.mohsenkohan.simplebank.model.accounts.BankAccount;
import dev.mohsenkohan.simplebank.model.observers.Auditor;
import dev.mohsenkohan.simplebank.model.observers.BankEvent;
import dev.mohsenkohan.simplebank.model.observers.BankObserver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

public class FxBankProgram extends Application {

    private SavedBankInfo info;
    private Map<Integer, BankAccount> accounts;
    private Bank bank;
    private Pane creationPane, infoPane, showALlPane;

    @Override
    public void init() throws Exception {
        info = new SavedBankInfo("target" + File.separator + "bank.info");
        accounts = info.getAccounts();
        bank = new Bank(accounts, info.nextAcctNum());

        BankObserver auditor = new Auditor(bank);

        bank.addObserver(BankEvent.DEPOSIT,
                (event, account, depositAmt) -> {
                    if (depositAmt > 10000000)
                        bank.makeSuspicious(account.getAcctNum());
                }
        );

        AccountCreationController accountCreationController = new AccountCreationController(bank);
        AccountCreationView accountCreationView = new AccountCreationView(accountCreationController);

        AccountInfoController accountInfoController = new AccountInfoController(bank);
        AccountInfoView accountInfoView = new AccountInfoView(accountInfoController);

        ShowAllAccountsController showAllAccountsController = new ShowAllAccountsController(bank);
        ShowAllAccountsView showAllAccountsView = new ShowAllAccountsView(showAllAccountsController);

        Border border = new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, null, null, new Insets(10)));

        creationPane = accountCreationView.getRoot();
        creationPane.setBorder(border);

        infoPane = accountInfoView.getRoot();
        infoPane.setBorder(border);

        showALlPane = showAllAccountsView.getRoot();
        showALlPane.setBorder(border);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox(new VBox(creationPane, showALlPane), infoPane);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        info.saveInfo(accounts, bank.nextAcctNum());
    }
}
