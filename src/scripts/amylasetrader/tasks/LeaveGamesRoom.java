package scripts.amylasetrader.tasks;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Login;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import org.tribot.api2007.types.RSTile;
import scripts.amylasetrader.MainScript;
import scripts.api.framework.Task;


/**
 * Created by Robin on 10/05/2016.
 */
public class LeaveGamesRoom implements Task {

    private MainScript script;

    public LeaveGamesRoom(MainScript script) {
        this.script = script;
    }

    private final RSArea GAMES_ROOM_BOTTOM_FLOOR =
            new RSArea(new RSTile(2200, 4936, 0), new RSTile(2215, 4943, 0));
    private final RSArea GAMES_ROOM_MIDDLE_FLOOR =
            new RSArea(new RSTile(2204, 4933, 1), new RSTile(2209, 4938, 1));

    private RSTile currentPosition;

    @Override
    public boolean validate() {
        if (Login.getLoginState() == Login.STATE.INGAME) {
            currentPosition = Player.getPosition();
            return GAMES_ROOM_BOTTOM_FLOOR.contains(currentPosition)
                    || GAMES_ROOM_MIDDLE_FLOOR.contains(currentPosition);
        }
        return false;
    }

    @Override
    public void execute() {
        General.println("LeavesGameRoom");
        if(GAMES_ROOM_BOTTOM_FLOOR.contains(currentPosition)) {
            RSObject[] stairs = Objects.find(10, new Filter<RSObject>() {
                @Override
                public boolean accept(RSObject rsObject) {
                    RSObjectDefinition rsObjectDefinition = rsObject.getDefinition();
                    if (rsObjectDefinition != null) {
                        String name = rsObjectDefinition.getName();
                        if (name != null) {
                            return name.equals("Stairs") && rsObject.getPosition().getY() < 4940;
                        }
                    }
                    return false;
                }
            });

            if(stairs.length > 0) {
                if(Clicking.click("Climb-up", stairs[0])) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Player.getPosition().getPlane() != 0;
                        }
                    }, 5000);
                }
            }
        }

        if(GAMES_ROOM_MIDDLE_FLOOR.contains(currentPosition)) {
            RSObject[] stairs = Objects.findNearest(5, 4627);

            if(stairs.length > 0) {
                if(Clicking.click("Climb-up", stairs[0])) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100);
                            return Player.getPosition().getPlane() == 0;
                        }
                    }, 5000);
                }
            }
        }

    }
}
