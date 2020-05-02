package dev.mohsenkohan.simplebank.fbi;

public class FBIView {

    private final FBIController controller;

    public FBIView(FBIController controller) {
        this.controller = controller;
    }

    public void showResult() {
        System.out.println(controller.processInfo());
    }
}
