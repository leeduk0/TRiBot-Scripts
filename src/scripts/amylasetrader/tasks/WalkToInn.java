package scripts.amylasetrader.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Login;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.util.DPathNavigator;
import scripts.amylasetrader.MainScript;
import scripts.amylasetrader.data.Areas;
import scripts.api.framework.Task;

/**
 * Created by Robin on 10/05/2016.
 */
public class WalkToInn implements Task {

    private MainScript script;

    public WalkToInn(MainScript script) {
        this.script = script;
    }

    @Override
    public boolean validate() {
        if (Login.getLoginState() == Login.STATE.INGAME) {
            return Areas.GAMES_ROOM_TOP_FLOOR.contains(Player.getPosition());
        }
        return false;
    }

    @Override
    public void execute() {
        DPathNavigator dPathNavigator = new DPathNavigator();
        if(dPathNavigator.traverse(Areas.BURTHORPE_INN.getRandomTile())) {
                Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return !Player.isMoving();
                }
            }, 12000);
        }
    }
    // player is in games room top floor area
}
