package core;

//import OSBot files
import utility.Constants;
import nodes.Banking;
import nodes.Collecting;
import nodes.WalkToBank;
import nodes.WalkToCowpen;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import utility.Exchange;
import utility.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ScriptManifest(name = "CowhideFarmer", author = "xBlite", version = 0.1, info = "CowhideFarmer", logo = "CowhideFarmer")
public class CowhideFarmer extends Script {
    public String status = "";
    private List<Node> nodes = new ArrayList<>();

    //Graphics Variables
    private long timeBegan;
    private long timeRan;

    @Override
    public void onStart() {
        Constants.cowhidesGathered = 0;
        timeBegan = System.currentTimeMillis();

        Collections.addAll(nodes,
                new Banking(this),
                new Collecting(this),
                new WalkToBank(this),
                new WalkToCowpen(this)
        );
    }

    @Override
    public int onLoop() throws InterruptedException {
        for (Node n : nodes) {
            if (n.validate()) {
                status = n.status();
                if (n.execute()) {
                    return random(200, 400);
                }
            }
        }
        return random(500, 700);
    }

    @Override
    public void onPaint(Graphics2D g)
    {
        Graphics2D graphics = g;
        timeRan = System.currentTimeMillis() - this.timeBegan;
        graphics.setFont(new Font(graphics.getFont().getFontName(), graphics.getFont().getStyle(), 11));
        graphics.drawString("Status: " + status, 5, 115);
        graphics.drawString("Script Runtime: " + util.formatTime(timeRan), 5, 130);
        graphics.drawString("Hides Gathered: " + new Integer(Constants.cowhidesGathered).toString(), 5, 145);
        graphics.drawString("Approx Wealth: " + new Integer(Constants.cowhidesGathered * Exchange.getPrice(1739)).toString(),5,160);
    }
}