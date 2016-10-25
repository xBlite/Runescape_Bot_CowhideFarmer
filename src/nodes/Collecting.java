package nodes;

import core.Node;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import utility.Constants;
import utility.util;

import java.util.concurrent.TimeUnit;

import static utility.Constants.COW_PEN;
import static utility.Constants.timeSinceLastGather;


public class Collecting extends Node {
    private String status;

    public Collecting(Script script) {
        super(script);
        status = "Collecting Cowhides";
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean validate() throws InterruptedException {
        return Constants.COW_PEN.contains(script.myPlayer()) && !script.getInventory().isFull();
    }

    @Override
    public boolean execute() throws InterruptedException {
        util util = new util();

        GroundItem cowhide;
        long startTime = System.currentTimeMillis();
        long successfulGather;

        status = "Gathering Cowhide";
        while (!script.getInventory().isFull()) {

            successfulGather = script.getInventory().getAmount("Cowhide");
            script.log("successfulGather: " + script.getInventory().getAmount("Cowhide"));
            util.openTab(Tab.INVENTORY, script);
            cowhide = script.getGroundItems().closest("Cowhide");
            Constants.timeSinceLastGather = System.currentTimeMillis() - startTime;

            if((timeSinceLastGather/1000) > 120)
            {
                status = "Not enough cowhides: switching worlds";
                script.getWorlds().hopToF2PWorld();
                MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
                startTime = System.currentTimeMillis();
            }

            //check sprint
            if (!script.getSettings().isRunning() && script.getSettings().getRunEnergy() > 80) {
                status = "Turning Sprint On";
                script.getSettings().setRunning(true);
                MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
            }

            //make sure there are cowhides
            while (cowhide == null && (timeSinceLastGather / 1000) < 120) {

                util.webWalkEvent(COW_PEN.getRandomPosition(),script);
                cowhide = script.getGroundItems().closest("Cowhide");
                MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            }

            if(cowhide!=null&&cowhide.exists()) {
                cowhide.interact("Take");
                do {
                    MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
                }
                while (script.myPlayer().isMoving());
                MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

                if (script.getInventory().getAmount("Cowhide") > successfulGather) {
                    startTime = System.currentTimeMillis();
                    ++Constants.cowhidesGathered;
                }
            }
        }
        return true;
    }
}
