package nodes;

import core.Node;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import utility.Constants;
import utility.util;

public class WalkToCowpen extends Node {
    private String status;

    public WalkToCowpen(Script script) {
        super(script);
        status = "Walking To Cow pen";
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.getInventory().isEmpty()&&!Constants.COW_PEN.contains(script.myPlayer());
    }

    @Override
    public boolean execute() throws InterruptedException {
        util util = new util();

        if(Constants.LUMBRIDGE_BANK.contains(script.myPlayer())) {
            status = "Go down 3rd story stairs";
            script.getObjects().closest("Staircase").interact("Climb-down");
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        if(script.getObjects().closest("Staircase") != null)
            status = "Go down 2nd story stairs";
            if(script.getObjects().closest("Staircase").hasAction("Climb-down")) {
                script.getObjects().closest("Staircase").interact("Climb-down");
                MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
            }

        status = "Walk to gate";
        util.webWalkEvent(new Position(3252, 3267, 0), script);

        status = "Check Gate";
        if (script.objects.closest("Gate").hasAction("Open")) {
            status = "Open Gate";
            script.getDoorHandler().handleNextObstacle(new Position(3253, 3267, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        }

        status = "Walk into Cow Pen";
        util.webWalkEvent(new Position(3259 , 3265 , 0), script);

        return true;
    }
}
