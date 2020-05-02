package dev.mohsenkohan.simplebank.fbi;

public class FBIController {

    private final FBIModel model;

    public FBIController(FBIModel model) {
        this.model = model;
    }

    public String processInfo() {
        return model.processInfo();
    }
}
