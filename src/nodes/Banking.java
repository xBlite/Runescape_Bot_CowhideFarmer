package nodes;

import core.Node;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import utility.Constants;

public class Banking extends Node {
    private String status;

    public Banking(Script script) {
        super(script);
        status = "Banking";
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean validate() throws InterruptedException {
        return !script.getInventory().isEmpty()&&Constants.LUMBRIDGE_BANK.contains(script.myPlayer());
    }

    @Override
    public boolean execute() throws InterruptedException {

        script.camera.toEntity(script.getObjects().closest("Bank booth"));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        status = "Talk to Banker";
        script.getObjects().closest("Bank booth").interact("Bank");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());
        status = "Deposit Inventory";
        script.getWidgets().get(12, 27).interact("Deposit inventory");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        status = "Close Bank";
        script.getWidgets().get( 12, 3, 11).interact("Close");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.camera.toEntity(script.getNpcs().closest("Staircase"));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        return true;
    }

}
