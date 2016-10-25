package nodes;

import core.Node;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import utility.Constants;
import utility.util;

public class WalkToBank extends Node {
    private String status;

    public WalkToBank(Script script) {
        super(script);
        status = "Walking to Bank";
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean validate() throws InterruptedException {
        return !script.getInventory().isEmpty()&&!Constants.LUMBRIDGE_BANK.contains(script.myPlayer());
    }

    @Override
    public boolean execute() throws InterruptedException {
        util util = new util();

        if(Constants.COW_PEN.contains(script.myPlayer())) {
            status = "Walk to gate";
            //util.webWalkEvent(util.routeFinder, new Position(3253, 3267, 0), 1, script);
            util.webWalkEvent(new Position(3253, 3267, 0), script);

            status = "Check Gate";
            if (script.objects.closest("Gate").hasAction("Open")) {
                status = "Open Gate";
                script.getDoorHandler().handleNextObstacle(new Position(3253, 3267, 0));
                do {
                    MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
                }
                while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            }
        }


        status = "Walk to Lumbridge Castle First Level";
            //util.webWalkEvent(util.routeFinder, new Position(3206, 3209, 0), 1, script);
        util.webWalkEvent(new Position(3206, 3209, 0), script);


        status = "Go up stairs";
        script.getObjects().closest("Staircase").interact("Climb-up");
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        status = "Go up stairs";
        if(script.getObjects().closest("Staircase").hasAction("Climb-up")) {
            script.getObjects().closest("Staircase").interact("Climb-up");
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        return true;
    }
}
