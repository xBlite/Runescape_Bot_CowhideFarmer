package utility;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public final class Constants {

    private Constants() {    }

    public final static Area LOWER_HALF = new Area(3253, 3271, 3265, 3255).setPlane(0);
    private final static Position[] COW_PEN_POSITIONS = {new Position(3253, 3255, 0), new Position(3265, 3255, 0), new Position(3265, 3297, 0), new Position(3241, 3298, 0), new Position(3241, 3273, 0), new Position(3252, 3273, 0)};
    public final static Area COW_PEN = new Area(COW_PEN_POSITIONS);
    public final static Area LUMBRIDGE_BANK = new Area(3203, 3206, 3215, 3231).setPlane(2);

    public static int cowhidesGathered;
    public static long timeSinceLastGather;

}

