package utility;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import java.util.concurrent.TimeUnit;
import static org.osbot.rs07.script.MethodProvider.gRandom;

public class util {


    public util() {

    }

    public void webWalkEvent(final Position position, final Script script) throws InterruptedException {

        if(script.myPosition().distance(position)!=0) {
            if (script.myPosition().distance(position) < 5)
                script.execute(new WalkingEvent(position).setMinDistanceThreshold(0));
            else
                script.getWalking().webWalk(position);
        }
    }

    public static void openTab(final Tab tab, final Script sA) throws InterruptedException {
        while (sA.getTabs().getOpen() != tab) {
            sA.getTabs().open(tab);
            MethodProvider.sleep(MethodProvider.gRandom(1000, 800));
        }
    }

    public static void AntiBan(final Script sA) throws InterruptedException {
        Tab previousTab = sA.tabs.getOpen();
        //make variable for skill currently training to hover over that skill

        switch (MethodProvider.random(1, 30)) {
            case 1:
                sA.camera.moveYaw(50 + (MethodProvider.random(1, 30)));
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 2:
                sA.camera.movePitch(100 + (MethodProvider.random(1, 70)));
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 3:
                sA.tabs.open(Tab.SKILLS);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 4:
                sA.tabs.open(Tab.ATTACK);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 5:
                sA.tabs.open(Tab.QUEST);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 6:
                sA.camera.toTop();
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 7:
                sA.camera.toBottom();
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 8:
                sA.tabs.open(Tab.CLANCHAT);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 9:
                sA.tabs.open(Tab.FRIENDS);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 10:
                sA.log("AntiBan sleep");
                MethodProvider.sleep(MethodProvider.random(5000, 12000));
                break;
        }

        MethodProvider.sleep(MethodProvider.random(700, 1300));
        openTab(previousTab, sA); //RETURNS TO THE TAB AFTER EVERY ANTIBAN INSTANCE
    }

    public static String formatTime(long ms){

        long s = ms / 1000, m = s / 60, h = m / 60;
        s %= 60; m %= 60; h %= 24;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public static void timeOut(final boolean resetTimer, final long duration, final Script script){

        long currentTime;
        long lastTime;

        if(resetTimer) {
            lastTime = 0;
        }

        //lastTime = currentTime;
        currentTime = System.currentTimeMillis();

        //if(((currentTime - lastTime)/1000) > duration) {
            //script.;

       // }
    }

}
